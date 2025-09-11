package com.application.dto;

import java.time.LocalDateTime;

public class ChatDTO {
    private Long id;
    private String remitenteNombre;
    private String destinatarioNombre;
    private LocalDateTime fechaHora;

    public ChatDTO() {
    }

    public ChatDTO(Long id, String remitenteNombre, String destinatarioNombre, LocalDateTime fechaHora) {
        this.id = id;
        this.remitenteNombre = remitenteNombre;
        this.destinatarioNombre = destinatarioNombre;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemitenteNombre() {
        return remitenteNombre;
    }

    public void setRemitenteNombre(String remitenteNombre) {
        this.remitenteNombre = remitenteNombre;
    }

    public String getDestinatarioNombre() {
        return destinatarioNombre;
    }

    public void setDestinatarioNombre(String destinatarioNombre) {
        this.destinatarioNombre = destinatarioNombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
