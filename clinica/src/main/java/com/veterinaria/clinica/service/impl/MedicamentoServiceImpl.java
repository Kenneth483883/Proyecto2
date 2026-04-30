package com.veterinaria.clinica.service.impl;


import com.veterinaria.clinica.repository.MedicamentoRepository;
import com.veterinaria.clinica.service.MedicamentoService;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository){
        this.medicamentoRepository = medicamentoRepository;
    }

}
