package com.dh.integrador.controller;

import com.dh.integrador.entities.Paciente;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger logger = Logger.getLogger(TurnoController.class);
    
    @Autowired
    private PacienteService pacienteService;
    
    @GetMapping("/listar")
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }
    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizar(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        if(pacienteService.buscar(id).isPresent()){
            return ResponseEntity.ok(pacienteService.buscar(id).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        try {
            pacienteService.eliminar(id);
        } catch (Exception e) {
            logger.error(e);
        }
        return ResponseEntity.ok("Se elimino el paciente sin problemas");
    }



}
