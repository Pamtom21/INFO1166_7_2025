package com.interfaces.controller.usuarioscontroller;

import com.domain.model.TipoUsuario;
import com.domain.model.Usuario;
import com.domain.repository.UsuarioRepository;
import com.application.dto.UsuarioDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/empresas")
    public List<Usuario> obtenerEmpresas() {
        return usuarioRepository.findByTipo(TipoUsuario.EMPRESA);
    }

    @GetMapping("/postulantes")
    public List<Usuario> obtenerPostulantes() {
        return usuarioRepository.findByTipo(TipoUsuario.POSTULANTE);
    }

    @GetMapping("/profile")
    public ResponseEntity<UsuarioDTO> obtenerPerfil(Authentication authentication) {
        String email = authentication.getName(); // extraído del token
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        UsuarioDTO dto = new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getTipo());
        return ResponseEntity.ok(dto);
    }
}
