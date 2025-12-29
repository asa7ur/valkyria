package org.iesalixar.daw2.GarikAsatryan.valkyrfest.controllers;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.PurchaseRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.CampingTypeService;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.PurchaseService;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.TicketTypeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final TicketTypeService ticketTypeService;
    private final CampingTypeService campingTypeService;

    @GetMapping
    public String showPurchaseForm(Model model) {
        // Preparamos el objeto DTO vacío para el formulario
        model.addAttribute("purchaseRequest", new PurchaseRequestDTO());

        // Cargamos los tipos de entrada y camping de la DB para los select de la web
        model.addAttribute("ticketTypes", ticketTypeService.getAllTicketTypes());
        model.addAttribute("campingTypes", campingTypeService.getAllCampingTypes());

        return "purchase/form";
    }

    @PostMapping
    public String processPurchase(@ModelAttribute PurchaseRequestDTO purchaseRequest,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {
        try {
            // Sacamos el email del usuario autenticado
            String email = authentication.getName();

            // Ejecutamos la lógica de compra
            Order order = purchaseService.executePurchase(purchaseRequest, email);

            // Si todo va bien, mensaje de éxito
            redirectAttributes.addFlashAttribute("successMessage", "¡Compra realizada con éxito! ID Pedido: " + order.getId());
            return "redirect:/";

        } catch (Exception e) {
            // Si algo falla (ej. sin stock), el GlobalExceptionHandler capturará la AppException
            throw e;
        }
    }
}
















