package com.interfaces.controller.chatcontroller;

import com.application.dto.ChatDTO;
import com.application.dto.CrearChatDTO;
import com.application.dto.MensajeDTO;
import com.domain.model.Mensaje;
import com.domain.model.chat;
import com.infrastructure.security.AESUtil;
import com.service.ChatService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // 🔹 Crear un nuevo chat entre dos usuarios
    @PostMapping("/crear")
    public chat crearChat(@RequestBody CrearChatDTO dto) {
        System.out.println("Creando chat entre: " + dto.getRemitenteId() + " y " + dto.getDestinatarioId());
        return chatService.crearChat(dto.getRemitenteId(), dto.getDestinatarioId());
    }

    // 🔹 Enviar un mensaje (temporalmente sin validar JWT)
    @PostMapping("/enviar")
    public MensajeDTO enviar(@RequestBody MensajeDTO dto, Authentication authentication) {
        if (authentication != null) {
            System.out.println("Usuario autenticado: " + authentication.getName());
        } else {
            System.out.println("⚠️ Usuario NO autenticado (temporal)");
        }

        // Enviar mensaje usando el servicio
        Mensaje mensaje = chatService.enviarMensaje(dto);

        // Devolver DTO desencriptado
        MensajeDTO respuesta = new MensajeDTO();
        respuesta.setId(mensaje.getId());
        respuesta.setChatId(mensaje.getChat().getId());
        respuesta.setContenido(AESUtil.desencriptar(mensaje.getContenido()));
        respuesta.setFechaHora(mensaje.getFechaHora());

        System.out.println("Mensaje enviado: " + respuesta.getContenido());

        return respuesta;
    }

    // 🔹 Obtener historial de mensajes desencriptados de un chat
    @GetMapping("/mensajes/{chatId}")
    public List<MensajeDTO> obtenerMensajes(@PathVariable Long chatId) {
        System.out.println("Obteniendo mensajes del chat: " + chatId);
        return chatService.obtenerMensajes(chatId);
    }

    // 🔹 Obtener todos los chats del usuario autenticado
    @GetMapping("/mis-chats")
    public List<ChatDTO> obtenerChatsDelUsuario(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            System.out.println("Obteniendo chats para usuario: " + email);
            return chatService.obtenerChatsDelUsuario(email);
        } else {
            System.out.println("⚠️ Usuario no autenticado intentando obtener chats");
            return List.of(); // devolver lista vacía temporalmente
        }
    }
}
