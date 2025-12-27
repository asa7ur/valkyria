package org.iesalixar.daw2.GarikAsatryan.valkyrfest.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.ArtistService;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.PerformanceService;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.StageService;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final ArtistService artistService;
    private final StageService stageService;
    private final PerformanceService performanceService;
    private final UserService userService;

    /**
     * Shows the main Dashboard view with statistical counters.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Obtains the total counts for the dashboard “cards.”
        model.addAttribute("artistCount", artistService.getAllArtists().size());
        model.addAttribute("stageCount", stageService.getAllStages().size());
        model.addAttribute("performanceCount", performanceService.getAllPerformances().size());
        model.addAttribute("userCount", userService.getAllUsers().size());

        // Attribute to mark the active link in the side menu.
        model.addAttribute("activePage", "dashboard");

        return "admin/dashboard";
    }
}
