package com.test.controller; // Ajusta según tu paquete de tests
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

    // Permite enviar mensaje (para tests)
    @PostMapping("/enviar")
    public ResponseEntity<Mensaje> enviar(@RequestBody MensajeDTO mensajeDTO) {
        Usuario remitente = usuarioRepository.findById(mensajeDTO.getRemitenteId())
                .orElseThrow(() -> new IllegalArgumentException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findById(mensajeDTO.getDestinatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));

        Chat chat = null;
        List<Mensaje> mensajesExistentes = chatService.obtenerMensajes(remitente.getId(), destinatario.getId());
        if (!mensajesExistentes.isEmpty()) {
            chat = mensajesExistentes.get(0).getChat();
        }

        Mensaje mensaje = chatService.enviarMensaje(chat, mensajeDTO, remitente, destinatario);
        return ResponseEntity.ok(mensaje);
    }

    // Obtiene historial de mensajes entre dos usuarios (para tests)
    @GetMapping("/mensajes/{remitenteId}/{destinatarioId}")
    public ResponseEntity<List<Mensaje>> obtenerMensajes(@PathVariable Long remitenteId,
                                                         @PathVariable Long destinatarioId) {
        List<Mensaje> mensajes = chatService.obtenerMensajes(remitenteId, destinatarioId);
        return ResponseEntity.ok(mensajes);
    }
}