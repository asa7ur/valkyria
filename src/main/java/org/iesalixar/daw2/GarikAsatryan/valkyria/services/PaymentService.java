package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final OrderService orderService;
    private final PdfGeneratorService pdfGeneratorService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @Value("${stripe.secret.key}")
    private String secretKey;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @Value("${app.url}")
    private String appUrl;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    /**
     * Crea una sesión de pago en Stripe.
     * Convierte el precio a céntimos (movePointRight(2)).
     */
    public String createStripeSession(Order order) throws Exception {
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setClientReferenceId(order.getId().toString())
                // URLs a las que volverá el usuario desde Stripe
                .setSuccessUrl(appUrl + "/purchase/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(appUrl + "/purchase/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("eur")
                                                .setUnitAmount(order.getTotalPrice().movePointRight(2).longValue())
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Pedido Valkyria #" + order.getId())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        Session session = Session.create(params);
        return session.getUrl();
    }

    /**
     * Procesa el evento de Webhook enviado por Stripe cuando el pago se completa.
     */
    @Transactional
    public void processWebhookEvent(String payload, String sigHeader) throws Exception {
        Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);

        if ("checkout.session.completed".equals(event.getType())) {
            String orderIdStr = extractOrderId(event);

            if (orderIdStr != null) {
                Long orderId = Long.parseLong(orderIdStr);

                // 1. Confirmar pago en DB (Esto es lo más importante)
                Order order = orderService.confirmPayment(orderId);
                logger.info("Pago confirmado para el pedido #{}", orderId);

                // 2. Procesos secundarios protegidos (Soft Fail)
                // Si esto falla, el pedido sigue como PAID, lo cual es correcto.
                enviarDocumentacion(order);
            }
        }
    }

    /**
     * Intenta extraer el ID del pedido de la sesión de Stripe.
     */
    private String extractOrderId(Event event) {
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();

        return dataObjectDeserializer.getObject()
                .map(stripeObject -> ((Session) stripeObject).getClientReferenceId())
                .orElseGet(() -> {
                    try {
                        JsonNode node = objectMapper.readTree(dataObjectDeserializer.getRawJson());
                        return node.has("client_reference_id") ? node.get("client_reference_id").asText() : null;
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

    /**
     * Lógica de envío de PDF y Email encapsulada para no romper la transacción principal.
     */
    private void enviarDocumentacion(Order order) {
        try {
            byte[] pdfBytes = pdfGeneratorService.generateOrderPdf(order);
            emailService.sendOrderConfirmationEmail(order, pdfBytes);
            logger.info("Documentación enviada por email al usuario: {}", order.getUser().getEmail());
        } catch (Exception e) {
            logger.error("Error al enviar la documentación del pedido #{}: {}", order.getId(), e.getMessage());
            // No relanzamos la excepción para no hacer rollback del pago.
        }
    }
}