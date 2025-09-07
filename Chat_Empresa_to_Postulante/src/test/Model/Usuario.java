package com.example.Chat_Empresa_to_Postulante.model;

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
    public Usuario(String nombre, TipoUsuario tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    //  Modelo de datos que guarda la informacion de usuarios..
}
