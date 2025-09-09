package com.example.Chat_Empresa_to_Postulante.model;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id // id unica del usuario 
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

    // Getters y setters  para mostrar sus datos del usuario
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
