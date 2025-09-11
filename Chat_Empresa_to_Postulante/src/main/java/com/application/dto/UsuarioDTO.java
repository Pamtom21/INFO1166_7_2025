package com.application.dto;

import com.domain.model.TipoUsuario;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private TipoUsuario tipo;

    public UsuarioDTO(Long id, String nombre, TipoUsuario tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

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
