package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.repository.RazaRepository;
import com.veterinaria.clinica.service.RazaService;
import org.springframework.stereotype.Service;


@Service
public class RazaServiceImpl implements RazaService {

    private final RazaRepository razaRepository;

    public RazaServiceImpl(RazaRepository razaRepository) {
        this.razaRepository = razaRepository;
    }

}
