package com.interfaces.controller;
import com.application.dto.MensajeDTO;
import com.domain.model.Mensaje;
import com.domain.model.Usuario;
import com.domain.model.Chat;
import com.service.ChatService;
import com.domain.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

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
        Chat chat = null;
        List<Mensaje> mensajesExistentes = chatService.obtenerMensajes(remitente.getId(), destinatario.getId());
        if (!mensajesExistentes.isEmpty()) {
            chat = mensajesExistentes.get(0).getChat(); // Tomamos el chat existente
        }

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
