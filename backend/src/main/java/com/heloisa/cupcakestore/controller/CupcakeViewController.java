package com.heloisa.cupcakestore.controller;

import com.heloisa.cupcakestore.service.CupcakeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CupcakeViewController {

    private final CupcakeService service;

    public CupcakeViewController(CupcakeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cupcakes", service.listarTodos());
        return "cupcakes";
    }
}