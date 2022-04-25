package com.dh.integrador.service;

import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.OdontologoService;
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
    public class OdontologoServiceTest {

        @Autowired
        private OdontologoService odontologoService;


        public void cargarDataSet() {
            Odontologo odontologo = odontologoService.guardar(new Odontologo("Lalala", "paula", "1233"));
        }
            //get

            @Test
            public void a_agregarOdontologoTest () {
                this.cargarDataSet();
                Odontologo odontologo1 = odontologoService.guardar(new Odontologo("rema", "nancy", "234"));

                Assert.assertNotNull(odontologo1);
            }

            @Test
            public void b_buscarOdontologoTest () {
                this.cargarDataSet();
                Odontologo odontologo2 = odontologoService.guardar(new Odontologo( "Lalala", "paula", "3456"));

                Assert.assertNotNull(odontologoService.buscar(odontologo2.getId()));
            }
            @Test
            public void c_listarOdontologoTest () {
                Assert.assertNotNull(odontologoService.listarOdontologos());
            }

            @Test
            public void d_actualizarOdontologoTest () {
                Odontologo odontologo = odontologoService.guardar(new Odontologo("Lalala", "paula", "1233"));
                Assert.assertEquals("Lalala", odontologo.getNombre());
            }

            @Test
            public void e_eliminarOdontologo () throws ResourceNotFoundException {
                odontologoService.borrar(1L);
                Assert.assertFalse(odontologoService.buscar(1L).isPresent());
            }

        }



