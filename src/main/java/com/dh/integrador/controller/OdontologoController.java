package com.dh.integrador.controller;

import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.OdontologoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger logger = Logger.getLogger(TurnoController.class);
    
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/listar")
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrar(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @PutMapping
    public Odontologo actualizarPaciente(@RequestBody Odontologo odontologo){
        return odontologoService.actualizar(odontologo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        if(odontologoService.buscar(id).isPresent()){
            return ResponseEntity.ok(odontologoService.buscar(id).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        try {
            odontologoService.borrar(id);
        } catch (Exception e) {
            logger.error(e);
        }
        return ResponseEntity.ok("Se elimino el odontologo sin problemas");
    }
}
