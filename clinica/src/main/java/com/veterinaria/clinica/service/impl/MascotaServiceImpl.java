package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.repository.MascotaRepository;
import com.veterinaria.clinica.service.MascotaService;
import org.springframework.stereotype.Service;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository  mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

}
