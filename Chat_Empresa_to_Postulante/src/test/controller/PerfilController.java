package com.example.Chat_Empresa_to_Postulante.controller;

import com.example.Chat_Empresa_to_Postulante.model.Perfil;
import com.example.Chat_Empresa_to_Postulante.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//expone edpoints rest para crear y obtener perfil
@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {

    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    // Crear perfil
    @PostMapping("/crear/{usuarioId}")
    public Perfil crearPerfil(@PathVariable Long usuarioId,
                              @RequestParam String correo,
                              @RequestParam String telefono,
                              @RequestParam int edad) {
        return perfilService.crearPerfil(usuarioId, correo, telefono, edad);
    }

    // Obtener perfil
    @GetMapping("/usuario/{usuarioId}")
    public Perfil obtenerPerfil(@PathVariable Long usuarioId) {
        return perfilService.obtenerPerfilPorUsuarioId(usuarioId);
    }
}
