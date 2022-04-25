package com.dh.integrador.service;

import com.dh.integrador.entities.Domicilio;
import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.entities.Paciente;
import com.dh.integrador.entities.Turno;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.OdontologoService;
import com.dh.integrador.service.PacienteService;
import com.dh.integrador.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void cargaDatosSet(){
        Domicilio domicilio = new Domicilio(
                "Bustillo",
                16000,
                "Bariloche",
                "Rio Negro"
        );
        Paciente paciente = pacienteService.guardar(
                new Paciente(
                        "Chennales",
                        "Leandro",
                        "lean@gmail.com",
                        565683,
                        LocalDate.of(2022,04,01),
                        domicilio)
        );
        this.odontologoService.guardar(
                new Odontologo(
                        "abc123",
                        "Silvia",
                        "Urda")
        );

    }
        @Test
        public void altaTurnoTest(){
            this.cargaDatosSet();
            turnoService.guardar(new Turno(
                    pacienteService.buscar(1L).get(),
                    odontologoService.buscar(1L).get(), LocalDate.of(2022, 05, 19)));
            Assert.assertNotNull(turnoService.buscar(1L));
        }

        @Test
        public void buscarTurnoTest(){
            Assert.assertNotNull(turnoService.buscar(1L));

        }
        @Test
        public void c_actualizarTurnoTest(){
            Turno turno = new Turno(
                            1L,
                            pacienteService.buscar(1L).get(),
                            odontologoService.buscar(1L).get(),
                            LocalDate.of(2022,10,1)
                    );
            Assert.assertEquals(
                    LocalDate.of(2022,10,1), turno.getFecha()
            );
        }
        @Test
        public void d_listarTurnoTest() {
            Assert.assertNotNull(turnoService.listarTurnos());
        }

        @Test
        public void eliminarTurnoTest() throws ResourceNotFoundException {
            try {
                turnoService.eliminar(1L);
            } catch (Exception e) {
                Assert.assertTrue(!turnoService.buscar(1L).isPresent());
            }
        }

    }


