package com.service;

import com.application.dto.ChatDTO;
import com.application.dto.CrearChatDTO;
import com.application.dto.MensajeDTO;
import com.domain.model.chat;
import com.domain.model.Mensaje;
import com.domain.model.Usuario;
import com.domain.repository.ChatRepository;
import com.domain.repository.MensajeRepository;
import com.domain.repository.UsuarioRepository;
import com.infrastructure.security.AESUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final UsuarioRepository usuarioRepo;
    private final ChatRepository chatRepo;
    private final MensajeRepository mensajeRepo;

    public ChatService(UsuarioRepository usuarioRepo, ChatRepository chatRepo, MensajeRepository mensajeRepo) {
        this.usuarioRepo = usuarioRepo;
        this.chatRepo = chatRepo;
        this.mensajeRepo = mensajeRepo;
    }

    // Crear un nuevo chat entre dos usuarios
    public chat crearChat(Long remitenteId, Long destinatarioId) {
        Usuario remitente = usuarioRepo.findById(remitenteId)
            .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepo.findById(destinatarioId)
            .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

        chat chat = new chat();
        chat.setRemitente(remitente);
        chat.setDestinatario(destinatario);
        chat.setFechaHora(LocalDateTime.now());

        return chatRepo.save(chat);
    }

    // Enviar un mensaje encriptado
    public Mensaje enviarMensaje(MensajeDTO dto) {
        chat chat = chatRepo.findById(dto.getChatId())
            .orElseThrow(() -> new RuntimeException("Chat no encontrado"));

        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setContenido(AESUtil.encriptar(dto.getContenido()));
        mensaje.setFechaHora(LocalDateTime.now());

        return mensajeRepo.save(mensaje);
    }

    // Obtener historial de mensajes desencriptados
    public List<MensajeDTO> obtenerMensajes(Long chatId) {
        List<Mensaje> mensajes = mensajeRepo.findByChatId(chatId);

        return mensajes.stream().map(m -> {
            MensajeDTO dto = new MensajeDTO();
            dto.setId(m.getId());
            dto.setChatId(chatId);
            dto.setContenido(AESUtil.desencriptar(m.getContenido()));
            dto.setFechaHora(m.getFechaHora());
            return dto;
        }).toList();
    }

    // Obtener todos los chats del usuario autenticado
    public List<ChatDTO> obtenerChatsDelUsuario(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<chat> chats = chatRepo.findByRemitenteOrDestinatario(usuario, usuario);

        return chats.stream().map(c -> {
            ChatDTO dto = new ChatDTO();
            dto.setId(c.getId());
            dto.setRemitenteNombre(c.getRemitente().getNombre());
            dto.setDestinatarioNombre(c.getDestinatario().getNombre());
            dto.setFechaHora(c.getFechaHora());
            return dto;
        }).toList();
    }
}