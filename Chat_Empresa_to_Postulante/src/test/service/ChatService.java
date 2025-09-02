package com.example.Chat_Empresa_to_Postulante.service;

import com.example.Chat_Empresa_to_Postulante.dto.MensajeDTO;
import com.example.Chat_Empresa_to_Postulante.model.Mensaje;
import com.example.Chat_Empresa_to_Postulante.model.Usuario;
import com.example.Chat_Empresa_to_Postulante.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final MensajeRepository mensajeRepository;

    public ChatService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }
//recibe el dto construye un mensaje y lo guarda en la bd
    public Mensaje enviarMensaje(MensajeDTO dto, Usuario remitente, Usuario destinatario) {
        Mensaje mensaje = new Mensaje(dto.getContenido(), remitente, destinatario);
        return mensajeRepository.save(mensaje);
    }
//devuelve la lista de mensajes entre dos usuarios
    public List<Mensaje> obtenerMensajes(Long remitenteId, Long destinatarioId) {
        return mensajeRepository.findByRemitenteIdAndDestinatarioId(remitenteId, destinatarioId);
    }
}
//logica del microservicio encapsula la logica del chat ,separando controlador de la base de datos 