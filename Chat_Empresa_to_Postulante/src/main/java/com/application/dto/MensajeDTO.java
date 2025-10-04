package com.application.dto;

/**
 * DTO para Mensaje, usado para simplificar el envío de datos
 * entre frontend y backend, evitando exponer la entidad completa.
 */
public class MensajeDTO {

    private String contenido;
    private Long remitenteId;
    private Long destinatarioId;

    /**
     * Constructor vacío requerido por frameworks como Spring Boot.
     */
    public MensajeDTO() {}

    /**
     * Constructor completo.
     * 
     * @param contenido      Contenido del mensaje
     * @param remitenteId    ID del usuario remitente
     * @param destinatarioId ID del usuario destinatario
     */
    public MensajeDTO(String contenido, Long remitenteId, Long destinatarioId) {
        this.contenido = contenido;
        this.remitenteId = remitenteId;
        this.destinatarioId = destinatarioId;
    }

    // Getters y setters
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(Long remitenteId) {
        this.remitenteId = remitenteId;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }
}
