package org.iesalixar.daw2.GarikAsatryan.valkyrfest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        // Podrías pasar atributos si quisieras mostrar algo dinámico
        return "index";
    }
}