package com.dh.integrador.controller;

import com.dh.integrador.entities.Paciente;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    /*public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
     */
    /*
    @GetMapping("/index")
    public String traerPaciente(Model model, @RequestParam("email") String email){
        //buscar al paciente con el email
        Paciente paciente= pacienteService.buscarXEmail(email);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "index";
    }

     */
    @GetMapping
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
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
            pacienteService.eliminar(id);
            return ResponseEntity.ok("Se elimino el paciente sin problemas");
    }



}
