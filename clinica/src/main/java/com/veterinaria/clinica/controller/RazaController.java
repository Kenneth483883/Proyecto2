package com.veterinaria.clinica.controller;


import com.veterinaria.clinica.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/raza")
@RestController
public class RazaController {

    private final RazaService razaService;

    @Autowired
    public RazaController(RazaService razaService) {
        this.razaService = razaService;
    }

}
