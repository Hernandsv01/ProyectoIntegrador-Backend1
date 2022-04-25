package com.dh.integrador.service;

import com.dh.integrador.entities.Domicilio;
import com.dh.integrador.entities.Paciente;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.PacienteService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;


    public void cargarDataSet(){
        Domicilio domicilio = new Domicilio(
                "Av Misiones",
                48,
                "Ensenada",
                "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Paula", "Mendez","santiago@paz.com" ,888444888, LocalDate.of(20222, 03, 16), domicilio));}


    //get

    @Test
    public void a_agregarPacienteTest() {
        this.cargarDataSet();
        Domicilio domicilio = new Domicilio("Calle", 123, "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "papap@gmail.com", 12345678, LocalDate.of(2022, 04, 22), domicilio));

        Assert.assertNotNull(paciente);
    }

        @Test
        public void b_buscarPacienteTest () {
            this.cargarDataSet();
            Domicilio domicilio = new Domicilio("Calle", 123, "Temperley", "Buenos Aires");
            Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "papap@gmail.com", 12345678, LocalDate.of(2022, 04, 22), domicilio));

            Assert.assertNotNull(pacienteService.buscar(paciente.getId()));
        }
        @Test
        public void c_listarPacienteTest () {
            Assert.assertNotNull(pacienteService.listarPacientes());
        }

        @Test
        public void d_actualizarPacienteTest () {
            Domicilio domicilio = new Domicilio("algo", 123, "baire", "buenos Aires");
            Paciente paciente =   new Paciente("Tomas", "Pereyra", "papap@gmail.com", 12345678, LocalDate.of(2022, 04, 22), domicilio);
            Assert.assertEquals(LocalDate.of(2022, 04, 22), paciente.getFechaIngreso());
    }

        @Test
        public void e_eliminarPaciente () throws ResourceNotFoundException {
        pacienteService.eliminar(1L);
              Assert.assertFalse(pacienteService.buscar(1L).isPresent());
        }

    }