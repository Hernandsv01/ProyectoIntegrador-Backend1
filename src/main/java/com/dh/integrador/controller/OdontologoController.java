package com.dh.integrador.controller;

import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.OdontologoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }
    @PostMapping
    public Odontologo registrar(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);
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
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.borrar(id);
        return ResponseEntity.ok("Se elimino el odontologo sin problemas");
    }
}
