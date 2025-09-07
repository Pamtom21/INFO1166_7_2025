package com.service;

import com.application.dto.MensajeDTO;
import com.domain.model.Mensaje;
import com.domain.model.chat;
import com.domain.model.Usuario;
import com.domain.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final MensajeRepository mensajeRepository;

    public ChatService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }
//recibe el dto construye un mensaje y lo guarda en la bd
public Mensaje enviarMensaje(chat chat, MensajeDTO dto, Usuario remitente, Usuario destinatario) {
    if (chat == null) {
        chat = new chat();
        chat.setRemitente(remitente.getNombre());
        chat.setDestinatario(destinatario.getNombre());
        chat.setFechaHora(LocalDateTime.now());
    }

    Mensaje mensaje = new Mensaje();
    mensaje.setContenido(dto.getContenido());
    mensaje.setFechaHora(LocalDateTime.now());
    mensaje.setChat(chat);

    chat.addMensajes(mensaje);

    return mensajeRepository.save(mensaje);
}
//devuelve la lista de mensajes entre dos usuarios
    public List<Mensaje> obtenerMensajes(Long remitenteId, Long destinatarioId) {
        return mensajeRepository.findByRemitenteIdAndDestinatarioId(remitenteId, destinatarioId);
    }
}
//logica del microservicio encapsula la logica del chat ,separando controlador de la base de datos 