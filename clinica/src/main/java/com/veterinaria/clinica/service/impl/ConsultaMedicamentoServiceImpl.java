package com.veterinaria.clinica.service.impl;


import com.veterinaria.clinica.repository.ConsultaMedicamentoRepository;
import com.veterinaria.clinica.service.ConsultaMedicamentoService;
import org.springframework.stereotype.Service;

@Service
public class ConsultaMedicamentoServiceImpl implements ConsultaMedicamentoService {

    private final ConsultaMedicamentoRepository consultaMedicamentoRepository;

    public ConsultaMedicamentoServiceImpl(ConsultaMedicamentoRepository consultaMedicamentoRepository){
        this.consultaMedicamentoRepository = consultaMedicamentoRepository;
    }

}
