package com.dh.integrador.service;

import com.dh.clase36.integradora.entities.Paciente;
import com.dh.clase36.integradora.exceptions.ResourceNotFoundException;
import com.dh.clase36.integradora.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{
    @Autowired
    PacienteRepository repository;

    public List<Paciente> listarPacientes() {
        return repository.findAll();
    }
    /*
    @Override
    public Paciente buscarXEmail(String email) {
        return pacienteIDao.buscarEmail(email);
    }
     */

    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente actualizar(Paciente paciente) {
        if(buscar(paciente.getId()).isPresent())
            return repository.save(paciente);
        else
            return null;
    }

    public Optional<Paciente> buscar(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado=buscar(id);
        if(pacienteBuscado.isPresent())
            repository.deleteById(id);
        else{
            throw new ResourceNotFoundException("No existe el paciente con el id= "+id+". Ingresar un id correcto");
        }
    }
}
