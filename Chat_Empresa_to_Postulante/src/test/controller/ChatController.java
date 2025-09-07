package com.example.Chat_Empresa_to_Postulante.controller;

import com.example.Chat_Empresa_to_Postulante.dto.MensajeDTO;
import com.example.Chat_Empresa_to_Postulante.model.Mensaje;
import com.example.Chat_Empresa_to_Postulante.model.Usuario;
import com.example.Chat_Empresa_to_Postulante.service.ChatService;
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
        Usuario remitente = new Usuario("Empresa", null);
        Usuario destinatario = new Usuario("Postulante", null);
        return chatService.enviarMensaje(mensajeDTO, remitente, destinatario);
    }
//obtiene el historial del chat 
    @GetMapping("/mensajes/{remitenteId}/{destinatarioId}")
    public List<Mensaje> obtenerMensajes(@PathVariable Long remitenteId, @PathVariable Long destinatarioId) {
        return chatService.obtenerMensajes(remitenteId, destinatarioId);
    }
}
