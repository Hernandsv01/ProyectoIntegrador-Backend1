package com.dh.integrador.service;

import com.dh.integrador.entities.Domicilio;
import com.dh.integrador.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DomicilioService {
    @Autowired
    DomicilioRepository repository;

    public List<Domicilio> listarDomicilios(){
        return repository.findAll();
    }

    public Domicilio guardar(Domicilio domicilio){
        return repository.save(domicilio);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }

    public Optional<Domicilio> buscar(Long id){
        return repository.findById(id);
    }

    public Domicilio actualizar(Domicilio domicilio) {
        if(buscar(domicilio.getId()).isPresent())
            return repository.save(domicilio);
        else
            return null;
    }
}
