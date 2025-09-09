package com.example.Chat_Empresa_to_Postulante.model;

import jakarta.persistence.*;

@Entity
public class Perfil {
    @Id // id único del perfil
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correo;   // correo del usuario
    private String telefono; // número de teléfono
    private int edad;        // edad del usuario

    // cada usuario tiene un perfil asociado
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    public Perfil() {}

    // Constructor para inicializar el perfil con sus datos
    public Perfil(String correo, String telefono, int edad, Usuario usuario) {
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
        this.usuario = usuario;
    }

    // Getters y setters para mostrar los datos del perfil
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
