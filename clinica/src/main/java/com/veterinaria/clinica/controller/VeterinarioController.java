package com.veterinaria.clinica.controller;


import com.veterinaria.clinica.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/veterinario")
@RestController
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @Autowired
    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

}
