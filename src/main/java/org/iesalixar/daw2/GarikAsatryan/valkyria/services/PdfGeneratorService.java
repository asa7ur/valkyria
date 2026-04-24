package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Camping;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorService.class);

    private final QrCodeService qrCodeService;
    private final TemplateEngine templateEngine;

    public byte[] generateOrderPdf(Order order) throws Exception {
        logger.info("Generando PDF profesional para pedido #{}", order.getId());

        // 1. Preparar datos adicionales (Nombre y QRs en Base64)
        String customerName = (order.getUser() != null)
                ? order.getUser().getFirstName() + " " + order.getUser().getLastName()
                : "Invitado (" + order.getGuestEmail() + ")";

        // Mapa para guardar los QR convertidos a Base64
        // Usamos una clave única para identificar si es ticket (T) o camping (C)
        Map<String, String> qrMap = new HashMap<>();

        for (Ticket t : order.getTickets()) {
            byte[] qrBytes = qrCodeService.generateQrCodeImage(t.getQrCode());
            qrMap.put("T" + t.getId(), Base64.getEncoder().encodeToString(qrBytes));
        }

        for (Camping c : order.getCampings()) {
            byte[] qrBytes = qrCodeService.generateQrCodeImage(c.getQrCode());
            qrMap.put("C" + c.getId(), Base64.getEncoder().encodeToString(qrBytes));
        }

        // 2. Crear el contexto de Thymeleaf
        Context context = new Context();
        context.setVariable("order", order);
        context.setVariable("customerName", customerName);
        context.setVariable("qrMap", qrMap);

        // 3. Procesar el HTML
        String htmlContent = templateEngine.process("pdf-order-tickets", context);

        // 4. Renderizar PDF usando Flying Saucer
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();

            // Flying Saucer requiere que el HTML sea XML bien formado
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            logger.info("✓ PDF generado correctamente con Thymeleaf");
            return outputStream.toByteArray();
        } catch (Exception e) {
            logger.error("Error al renderizar el PDF: {}", e.getMessage());
            throw new RuntimeException("Error en la generación visual del PDF", e);
        }
    }
}