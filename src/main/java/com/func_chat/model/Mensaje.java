package com.func_chat.model;

import java.time.LocalDateTime;

public class Mensaje {
    private String contenido;
    private String remitente;
    private String destinatario;
    private LocalDateTime timestamp;
    private String tipo; // "ENVIADO" o "RECIBIDO"

    // Constructores
    public Mensaje() {
        this.timestamp = LocalDateTime.now();
    }

    public Mensaje(String contenido, String remitente, String destinatario, String tipo) {
        this.contenido = contenido;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.tipo = tipo;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getRemitente() { return remitente; }
    public void setRemitente(String remitente) { this.remitente = remitente; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}