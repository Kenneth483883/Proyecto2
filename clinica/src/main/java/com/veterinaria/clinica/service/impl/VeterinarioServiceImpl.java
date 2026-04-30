package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.repository.VeterinarioRepository;
import com.veterinaria.clinica.service.VeterinarioService;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository){
        this.veterinarioRepository = veterinarioRepository;
    }

}
