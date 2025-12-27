package org.iesalixar.daw2.GarikAsatryan.valkyrfest.controllers.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.Performance;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.PerformanceService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/festival/performances")
@RequiredArgsConstructor
public class PerformanceAdminController {
    private final PerformanceService performanceService;
    private final MessageSource messageSource;

    /**
     * Lists all performances in the database
     */
    @GetMapping
    public String listPerformances(Model model) {
        List<Performance> performances = performanceService.getAllPerformances();
        model.addAttribute("performances", performances);
        model.addAttribute("activePage", performances);
        return "admin/performances/list";
    }

    /**
     * Shows the form to edit an existing performance
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        performanceService.getPerformanceById(id).ifPresent(performance -> model.addAttribute("performance", performance));
        model.addAttribute("activePage", "performances");
        return "admin/performances/form";
    }

    /**
     * Saves or updates an performance
     */
    @PostMapping("/save")
    public String savePerformance(
            @Valid @ModelAttribute("performance") Performance performance,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("activePage", "performances");
            return "admin/performances/form";
        }

        performanceService.savePerformance(performance);

        redirectAttributes.addFlashAttribute("successMessage",
                messageSource.getMessage("msg.admin.performance.save.success", null, LocaleContextHolder.getLocale()));

        return "redirect:/admin/festival/performances";
    }

    /**
     * Deletes an performance by its ID
     */
    @GetMapping("/delete/{id}")
    public String deletePerformance(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        performanceService.deletePerformance(id);

        redirectAttributes.addFlashAttribute(messageSource.getMessage("msg.admin.performance.delete.success", null, LocaleContextHolder.getLocale()));

        return "redirect:/admin/festival/performances";
    }
}
