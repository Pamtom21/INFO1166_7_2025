package com.example.Chat_Empresa_to_Postulante.dto;

public class MensajeDTO {
    private String contenido;
    private Long remitenteId;
    private Long destinatarioId;

    public MensajeDTO() {}
//simplifica el envio de datos  y evitar exponer entidades completas 
    public MensajeDTO(String contenido, Long remitenteId, Long destinatarioId) {
        this.contenido = contenido;
        this.remitenteId = remitenteId;
        this.destinatarioId = destinatarioId;
    }

    // Frontend envie datos al backend sin enviar todo el objeto completo
