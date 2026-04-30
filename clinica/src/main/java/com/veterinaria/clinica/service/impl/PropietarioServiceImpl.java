package com.veterinaria.clinica.service.impl;


import com.veterinaria.clinica.repository.PropietarioRepository;
import com.veterinaria.clinica.service.PropietarioService;
import org.springframework.stereotype.Service;


@Service
public class PropietarioServiceImpl implements PropietarioService {

    private final PropietarioRepository  propietarioRepository;

    public PropietarioServiceImpl(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

}
