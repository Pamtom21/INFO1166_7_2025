package com.example.Chat_Empresa_to_Postulante.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    private LocalDateTime fechaEnvio;

    @ManyToOne
    private Usuario remitente;

    @ManyToOne
    private Usuario destinatario;

    public Mensaje() {
        this.fechaEnvio = LocalDateTime.now();
    }

    public Mensaje(String contenido, Usuario remitente, Usuario destinatario) {
        this.contenido = contenido;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fechaEnvio = LocalDateTime.now();
    }

}
