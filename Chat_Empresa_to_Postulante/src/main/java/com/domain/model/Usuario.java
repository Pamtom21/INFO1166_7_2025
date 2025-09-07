package com.domain.model;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; //nombre del usuario

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    public Usuario() {}
//tipo de usuario empresa o postulante
    public String getNombre(){return this.nombre; }
    public void setNombre(String nombre){this.nombre = nombre;}

    public TipoUsuario getTipo(){return this.tipo; }
    public void setTipo(TipoUsuario tipo){this.tipo = tipo;}
    //  Modelo de datos que guarda la informacion de usuarios..
}
