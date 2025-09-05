package com.interfaces.controller;

import com.application.dto.MensajeDTO;
import com.domain.model.Mensaje;
import com.domain.model.Usuario;
import com.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
//permite enviar mensaje
    @PostMapping("/enviar")
    public Mensaje enviar(@RequestBody MensajeDTO mensajeDTO) {
        //  Aquí deberia cargar usuarios desde la BD
        Usuario remitente = new Usuario();
        Usuario destinatario = new Usuario();
        return chatService.enviarMensaje(null, mensajeDTO, remitente, destinatario);
    }
//obtiene el historial del chat 
    @GetMapping("/mensajes/{remitenteId}/{destinatarioId}")
    public List<Mensaje> obtenerMensajes(@PathVariable Long remitenteId, @PathVariable Long destinatarioId) {
        return chatService.obtenerMensajes(remitenteId, destinatarioId);
    }
}