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

    public Mensaje enviarMensaje(MensajeDTO dto, Usuario remitente, Usuario destinatario) {
        Mensaje mensaje = new Mensaje(dto.getContenido(), remitente, destinatario);
        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> obtenerMensajes(Long remitenteId, Long destinatarioId) {
        return mensajeRepository.findByRemitenteIdAndDestinatarioId(remitenteId, destinatarioId);
    }
}
