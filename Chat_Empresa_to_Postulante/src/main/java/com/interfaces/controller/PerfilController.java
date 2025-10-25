package com.interfaces.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.model.Perfil;
import com.domain.model.Usuario;
import com.service.PerfilService;
import com.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/perfiles")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;
    private final UsuarioService usuarioService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<Perfil> crearPerfil(@PathVariable Long usuarioId, @RequestBody Perfil perfil) {
        Usuario usuario = usuarioService.obtenerPorId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        perfil.setUsuario(usuario);
        return ResponseEntity.ok(perfilService.crearPerfil(perfil));
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> listarPerfiles() {
        return ResponseEntity.ok(perfilService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> obtenerPerfil(@PathVariable Long id) {
        return perfilService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> actualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfilActualizado) {
        Perfil perfil = perfilService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        perfil.setResumen(perfilActualizado.getResumen());
        perfil.setHabilidades(perfilActualizado.getHabilidades());
        perfil.setExperiencia(perfilActualizado.getExperiencia());
        perfil.setEducacion(perfilActualizado.getEducacion());
        return ResponseEntity.ok(perfilService.actualizarPerfil(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfil(@PathVariable Long id) {
        perfilService.eliminarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
