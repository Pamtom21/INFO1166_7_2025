package com.example.Chat_Empresa_to_Postulante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Chat_Empresa_to_Postulante.model.Usuario;
import com.example.Chat_Empresa_to_Postulante.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.correoExiste(usuario.getCorreo())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("El correo ya está registrado");
            }

            Usuario registrado = usuarioService.registrarUsuario(
                    usuario.getNombre(),
                    usuario.getCorreo(),
                    usuario.getTipo() // 👈 Aquí depende si es enum o String
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(registrado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
