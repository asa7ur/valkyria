package org.iesalixar.daw2.GarikAsatryan.valkyrfest.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.OrderRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final MessageSource messageSource;

    @GetMapping("/checkout")
    public String showOrderForm(Model model) {
        logger.info("Showing checkout form");
        model.addAttribute("orderRequest", new OrderRequestDTO());
        return "order-form";
    }

    @PostMapping("/checkout")
    public String processOrder(
            @Valid @ModelAttribute("orderRequest") OrderRequestDTO orderRequest,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        logger.info("Starting order process for user ID: {}", orderRequest.getUserId());

        // Checking for validation errors
        if (result.hasErrors()) {
            logger.warn("Detected {} validation errors in the order request", result.getAllErrors().size());
            return "order-form";
        }

        orderService.createOrder(orderRequest);

        logger.info("Order processed and saved succesfully in the database.");
        redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("msg.order.success", null, LocaleContextHolder.getLocale()));

        return "redirect:/orders/success";
    }

    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        model.addAttribute("activePage", "orders");
        return "order-success";
    }
}
