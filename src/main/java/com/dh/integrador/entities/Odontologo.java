package com.dh.integrador.entities;

import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;

@Entity
@Table(name="odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String matricula;

//    @OneToMany(mappedBy = "odontologo",fetch = FetchType.LAZY)
//    private Set<Turno> turnos= new HashSet<>();
    
    
    public Odontologo(){

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

//    public Set<Turno> getTurnos() {
//        return turnos;
//    }
//
//    public void setTurnos(Set<Turno> turnos) {
//        this.turnos = turnos;
//    }
}
