package org.iesalixar.daw2.GarikAsatryan.valkyrfest.controllers.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.services.ArtistService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/festival/artists")
@RequiredArgsConstructor
public class ArtistAdminController {
    private final ArtistService artistService;
    private final MessageSource messageSource;

    /**
     * Lists all artists in the database
     */
    @GetMapping
    public String listArtists(Model model) {
        List<Artist> artists = artistService.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("activePage", artists);
        return "admin/artists/list";
    }

    /**
     * Shows the form to edit an existing artist
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        artistService.getArtistById(id).ifPresent(artist -> model.addAttribute("artist", artist));
        model.addAttribute("activePage", "artists");
        return "admin/artists/form";
    }

    /**
     * Saves or updates an artist
     */
    @PostMapping("/save")
    public String saveArtist(
            @Valid @ModelAttribute("artist") Artist artist,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("activePage", "artists");
            return "admin/artists/form";
        }

        artistService.saveArtist(artist);

        redirectAttributes.addFlashAttribute("successMessage",
                messageSource.getMessage("msg.admin.artist.save.success", null, LocaleContextHolder.getLocale()));

        return "redirect:/admin/festival/artists";
    }

    /**
     * Deletes an artist by its ID
     */
    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        artistService.deleteArtist(id);

        redirectAttributes.addFlashAttribute(messageSource.getMessage("msg.admin.artist.delete.success", null, LocaleContextHolder.getLocale()));

        return "redirect:/admin/festival/artists";
    }
}































