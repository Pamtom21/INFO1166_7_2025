package com.example.Chat_Empresa_to_Postulante.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.example.Chat_Empresa_to_Postulante.model.Mensaje; // <-- Import necesario



@Entity
@Table(name = "chat")
public class chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;

    private String remitente;
    private String destinatario;
    private LocalDateTime fechaHora;

    // Relación uno a muchos con mensaje
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mensaje> mensajes;

    public chat() {}

    // Getters y Setters
    public Long getIdChat() { return idChat; }
    public void setIdChat(Long idChat) { this.idChat = idChat; }

    public String getRemitente() { return remitente; }
    public void setRemitente(String remitente) { this.remitente = remitente; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public List<Mensaje> getMensajes() { return mensajes; }
    public void setMensajes(List<Mensaje> mensajes) { this.mensajes = mensajes; }
}
