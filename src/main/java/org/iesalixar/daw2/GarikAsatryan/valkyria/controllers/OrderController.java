package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderResponseDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.User;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.OrderService;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.PaymentService;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.PdfGeneratorService;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final PaymentService paymentService;
    private final PdfGeneratorService pdfGeneratorService;

    /**
     * Devuelve el historial de pedidos del usuario autenticado.
     */
    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(Authentication authentication) {
        // authentication.getName() devuelve el email del usuario logueado (desde el JWT)
        List<OrderResponseDTO> orders = orderService.getOrdersByUser(authentication.getName());
        return ResponseEntity.ok(orders);
    }

    /**
     * Proceso de Checkout:
     * 1. Crea el pedido en estado PENDING y descuenta stock.
     * 2. Crea la sesión en Stripe.
     * 3. Devuelve la URL de Stripe para que Angular redireccione al usuario.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> checkout(
            @Valid @RequestBody OrderRequestDTO request,
            Authentication authentication) throws Exception {

        User user = null;
        // Si hay autenticación, buscamos al usuario. Si no, se queda como null.
        if (authentication != null && authentication.isAuthenticated()) {
            user = userService.getUserByEmailEntity(authentication.getName());
        }

        // Ejecutamos la lógica de creación de pedido y stock
        Order order = orderService.executeOrder(request, user);

        // Generamos la pasarela de pago
        String stripeUrl = paymentService.createStripeSession(order);

        // Devolvemos la URL para que el frontend haga: window.location.href = res.url;
        return ResponseEntity.ok(Map.of("url", stripeUrl));
    }

    /**
     * Descarga del PDF de credenciales.
     * Solo permite la descarga si el pedido pertenece al usuario autenticado.
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id, Authentication authentication) throws Exception {
        Order order = orderService.getOrderEntityById(id);

        // Seguridad: Comprobar que el pedido es del usuario que lo solicita
        if (!order.getUser().getEmail().equals(authentication.getName())) {
            throw new AppException("msg.error.unauthorized-access");
        }

        // Generamos los bytes del PDF
        byte[] pdfBytes = pdfGeneratorService.generateOrderPdf(order);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Valkyria_Pedido_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}