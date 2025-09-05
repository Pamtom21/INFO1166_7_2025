package com.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.*;

@Entity
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; //nombre del usuario
    private String email; // email o rut (definir)
    private String password; // contraseña del usuario

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    public Usuario() {}
//tipo de usuario empresa o postulante
    public String getNombre(){return this.nombre; }
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getEmail(){return this.email; }
    public void setEmail(String email){this.email = email;}

    
    public void setPassword(String password){this.password = password;}

    public TipoUsuario getTipo(){return tipo; }
    public void setTipo(TipoUsuario tipo){this.tipo = tipo;}
    //  Modelo de datos que guarda la informacion de usuarios..


    //aplicacion userdetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(tipo.name()));
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; } // email será el username

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}