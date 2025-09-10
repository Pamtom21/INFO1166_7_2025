package com.func_chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.func_chat.model.Mensaje;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.enviarMensaje")
    @SendTo("/topic/mensajes")
    public Mensaje enviarMensaje(Mensaje mensaje) {
        // falta agregar lógica para guardar en base de datos
        System.out.println("Mensaje recibido: " + mensaje.getContenido());
        return mensaje;
    }

    @MessageMapping("/chat.mensajePrivado")
    public void enviarMensajePrivado(Mensaje mensaje) {
        // Enviar mensaje privado a un usuario específico
        String destino = "/topic/mensajes." + mensaje.getDestinatario();
        messagingTemplate.convertAndSend(destino, mensaje);
    }
}