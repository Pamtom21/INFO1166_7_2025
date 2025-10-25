package com.interfaces.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dto.MensajeDTO;
import com.domain.model.Chat;
import com.domain.model.Mensaje;
import com.domain.model.Usuario;
import com.domain.repository.UsuarioRepository;
import com.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ChatController(ChatService chatService, UsuarioRepository usuarioRepository) {
        this.chatService = chatService;
        this.usuarioRepository = usuarioRepository;
    }

    // Permite enviar mensaje
    @PostMapping("/enviar")
    public ResponseEntity<Mensaje> enviar(@RequestBody MensajeDTO mensajeDTO) {
        // Buscar usuarios por ID
        Usuario remitente = usuarioRepository.findById(mensajeDTO.getRemitenteId())
                .orElseThrow(() -> new IllegalArgumentException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findById(mensajeDTO.getDestinatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));

        // Buscar si ya existe chat entre estos usuarios
        Chat chat = chatService.obtenerChatExistente(remitente.getId(), destinatario.getId())
                .orElseGet(() -> chatService.crearChat(remitente, destinatario));

        // Enviar mensaje
        Mensaje mensaje = chatService.enviarMensaje(chat, mensajeDTO, remitente, destinatario);
        return ResponseEntity.ok(mensaje);
    }

    // Obtiene el historial de mensajes entre dos usuarios
    @GetMapping("/mensajes/{remitenteId}/{destinatarioId}")
    public ResponseEntity<List<Mensaje>> obtenerMensajes(@PathVariable Long remitenteId,
                                                         @PathVariable Long destinatarioId) {
        List<Mensaje> mensajes = chatService.obtenerMensajes(remitenteId, destinatarioId);
        return ResponseEntity.ok(mensajes);
    }
}
