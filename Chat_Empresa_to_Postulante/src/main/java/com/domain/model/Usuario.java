package com.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // nombre del usuario

    private String correo; // correo del usuario

    @Enumerated(EnumType.STRING)
    @JsonProperty // asegura serialización/deserialización JSON
    private TipoUsuario tipo;

    // Constructor vacío requerido por JPA
    public Usuario() {}

    // Constructor completo útil para DTOs o pruebas
    public Usuario(Long id, String nombre, String correo, TipoUsuario tipo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
    }

    // Getters y setters
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

}
