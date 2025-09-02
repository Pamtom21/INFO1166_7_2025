package com.example.Chat_Empresa_to_Postulante.model;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    public Usuario() {}

    public Usuario(String nombre, TipoUsuario tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Getters y setters
}
