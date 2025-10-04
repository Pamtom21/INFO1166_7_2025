package com.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un chat entre empresa y postulante.
 */
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;

    @Column(nullable = false)
    private String remitente;

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    // Relación uno a muchos con mensajes
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mensaje> mensajes = new ArrayList<>();

    public Chat() {}

    @PrePersist
    protected void prePersist() {
        if (fechaHora == null) {
            fechaHora = LocalDateTime.now();
        }
    }

    // Getters y Setters
    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    // Métodos para gestionar la relación con Mensaje
    public void addMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
        mensaje.setChat(this);
    }

    public void removeMensaje(Mensaje mensaje) {
        mensajes.remove(mensaje);
        mensaje.setChat(null);
    }
}
