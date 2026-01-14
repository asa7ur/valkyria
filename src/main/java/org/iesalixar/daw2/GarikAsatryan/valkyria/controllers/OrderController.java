package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final PaymentService paymentService;
    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(Authentication authentication) {
        List<OrderResponseDTO> orders = orderService.getOrdersByUserDTO(authentication.getName());
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody OrderRequestDTO request, Authentication authentication) throws Exception {
        User user = userService.getUserByEmail(authentication.getName());
        Order order = orderService.executeOrder(request, user);
        String stripeUrl = paymentService.createStripeSession(order);
        return ResponseEntity.ok(Map.of("url", stripeUrl));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id, Authentication authentication) throws Exception {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new AppException("msg.error.orderNotFound"));

        if (!order.getUser().getEmail().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        byte[] pdfBytes = pdfGeneratorService.generateOrderPdf(order);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Valkyria_Pedido_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}