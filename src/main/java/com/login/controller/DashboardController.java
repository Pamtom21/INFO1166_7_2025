package com.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal; // ← AGREGA ESTE IMPORT

@Controller
public class DashboardController {
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Datos de ejemplo
        model.addAttribute("message", "¡Bienvenido al sistema de chat BNE!");
        model.addAttribute("totalConversaciones", 12);
        model.addAttribute("conversacionesActivas", 8);
        model.addAttribute("conversacionesPendientes", 3);
        model.addAttribute("conversacionesCerradas", 1);
        
        return "chat/dashboard";
    }
    
    @GetMapping("/chat")
    public String chat(Model model, Principal principal) { // ← Principal ahora está importado
        model.addAttribute("usuarioActual", principal.getName());
        return "chat/conversation";
    }
}