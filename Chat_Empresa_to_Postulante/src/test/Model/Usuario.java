package com.example.Chat_Empresa_to_Postulante.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // nombre del usuario

    @Column(nullable = false, unique = true)
    private String correo; // correo del usuario (único)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo; // EMPRESA o POSTULANTE

    // Constructor vacío (obligatorio para JPA)
    public Usuario() {}

    // Constructor parcial (sin ID, ya que lo genera la BD)
    public Usuario(String nombre, String correo, TipoUsuario tipo) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
    }

    // Constructor completo (útil en algunos casos)
    public Usuario(Long id, String nombre, String correo, TipoUsuario tipo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
    }

    // Getters y Setters
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
