package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.repository.EspecieRepository;
import com.veterinaria.clinica.service.EspecieService;
import org.springframework.stereotype.Service;


@Service
public class EspecieServiceImpl implements EspecieService {

    private final EspecieRepository especieRepository;

    public EspecieServiceImpl(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

}