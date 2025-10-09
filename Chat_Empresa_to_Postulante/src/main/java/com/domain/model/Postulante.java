package com.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column
    private String cvUrl; // URL del CV del postulante, opcional

    // Constructor vacío requerido por JPA
    public Postulante() {}

    // Constructor completo útil para DTOs o pruebas
    public Postulante(Long id, String nombre, String correo, String cvUrl) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.cvUrl = cvUrl;
    }

    // Constructor sin id, usado al crear un nuevo postulante
    public Postulante(String nombre, String correo, String cvUrl) {
        this.nombre = nombre;
        this.correo = correo;
        this.cvUrl = cvUrl;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getCvUrl() { return cvUrl; }
    public void setCvUrl(String cvUrl) { this.cvUrl = cvUrl; }
}
