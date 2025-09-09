package com.info1166.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 50)
    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El usuario debe tener entre 3 y 50 caracteres")
    private String username;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    
    @Column(name = "nombre_completo", nullable = false, length = 100)
    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre completo debe tener entre 2 y 100 caracteres")
    private String nombreCompleto;
    
    @Column(name = "enabled")
    private boolean enabled = true;
    
    @Column(name = "email", length = 100)
    private String email;
    
    // Constructores
    public Usuario() {}
    
    public Usuario(String username, String password, String nombreCompleto) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    // ✅ MÉTODO QUE FALTA - AGREGA ESTO:
    public String getNombre() {
        return nombreCompleto; // Retorna el nombre completo como nombre
    }
    
    public void setNombre(String nombre) {
        this.nombreCompleto = nombre; // Asigna al nombre completo
    }
}