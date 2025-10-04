package com.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.model.Usuario;
import com.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Ruta para registrar un usuario
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            // Validar que el correo no exista
            if (usuarioService.correoExiste(usuario.getCorreo())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("El correo ya está registrado");
            }

            // Registrar usuario usando el servicio corregido
            Usuario registrado = usuarioService.registrarUsuario(
                    usuario.getNombre(),
                    usuario.getCorreo(),       // Agregado
                    usuario.getTipo().name()   // Tipo de usuario como String
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(registrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
