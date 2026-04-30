package com.veterinaria.clinica.service.impl;


import com.veterinaria.clinica.repository.ConsultaRepository;
import com.veterinaria.clinica.service.ConsultaService;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }
}
