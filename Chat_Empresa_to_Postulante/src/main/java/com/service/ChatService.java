package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.application.dto.MensajeDTO;
import com.domain.model.Chat;
import com.domain.model.Mensaje;
import com.domain.model.Usuario;
import com.domain.repository.Chat_Repository;
import com.domain.repository.MensajeRepository;
import com.domain.repository.UsuarioRepository;

@Service
public class ChatService {

    private final MensajeRepository mensajeRepository;
    private final Chat_Repository chatRepository;
    private final UsuarioRepository usuarioRepository;

    public ChatService(MensajeRepository mensajeRepository,
                       Chat_Repository chatRepository,
                       UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.chatRepository = chatRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Envía un mensaje en un chat existente.
     */
    public Mensaje enviarMensaje(Chat chat, MensajeDTO dto, Usuario remitente, Usuario destinatario) {
        // Crear mensaje
        Mensaje mensaje = new Mensaje(dto.getContenido(), remitente, destinatario);

        // Agregar mensaje al chat
        chat.addMensaje(mensaje);

        return mensajeRepository.save(mensaje);
    }

    /**
     * Obtiene todos los mensajes entre dos usuarios.
     */
    public List<Mensaje> obtenerMensajes(Long remitenteId, Long destinatarioId) {
        Usuario remitente = usuarioRepository.findById(remitenteId)
                .orElseThrow(() -> new IllegalArgumentException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findById(destinatarioId)
                .orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));

        Optional<Chat> chat = chatRepository.findAll().stream()
                .filter(c -> (c.getRemitente().equals(remitente.getNombre()) &&
                              c.getDestinatario().equals(destinatario.getNombre())) ||
                             (c.getRemitente().equals(destinatario.getNombre()) &&
                              c.getDestinatario().equals(remitente.getNombre())))
                .findFirst();

        return chat.map(c -> mensajeRepository.findByChatIdChat(c.getIdChat()))
                   .orElse(List.of());
    }

    /**
     * Obtiene un chat existente entre dos usuarios.
     */
    public Optional<Chat> obtenerChatExistente(Long remitenteId, Long destinatarioId) {
        Usuario remitente = usuarioRepository.findById(remitenteId)
                .orElseThrow(() -> new IllegalArgumentException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findById(destinatarioId)
                .orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));

        return chatRepository.findAll().stream()
                .filter(c -> (c.getRemitente().equals(remitente.getNombre()) &&
                              c.getDestinatario().equals(destinatario.getNombre())) ||
                             (c.getRemitente().equals(destinatario.getNombre()) &&
                              c.getDestinatario().equals(remitente.getNombre())))
                .findFirst();
    }

    /**
     * Crea un nuevo chat entre dos usuarios.
     */
    public Chat crearChat(Usuario remitente, Usuario destinatario) {
        Chat chat = new Chat();
        chat.setRemitente(remitente.getNombre());
        chat.setDestinatario(destinatario.getNombre());
        chat.setFechaHora(LocalDateTime.now());
        return chatRepository.save(chat);
    }
}
