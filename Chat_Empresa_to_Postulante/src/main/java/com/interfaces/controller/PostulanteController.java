package com.interfaces.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.model.Postulante;
import com.service.PostulanteService;

@RestController
@RequestMapping("/postulantes")
public class PostulanteController {

    @Autowired
    private PostulanteService postulanteService;

    // Crear un postulante
    @PostMapping
    public ResponseEntity<Postulante> crearPostulante(@RequestBody Postulante postulante) {
        Postulante creado = postulanteService.guardarPostulante(postulante);
        return ResponseEntity.ok(creado);
    }

    // Obtener todos los postulantes
    @GetMapping
    public ResponseEntity<List<Postulante>> listarPostulantes() {
        return ResponseEntity.ok(postulanteService.obtenerTodos());
    }

    // Obtener postulante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Postulante> obtenerPostulante(@PathVariable Long id) {
        Optional<Postulante> postulante = postulanteService.obtenerPorId(id);
        return postulante.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar postulante
    @PutMapping("/{id}")
    public ResponseEntity<Postulante> actualizarPostulante(@PathVariable Long id,
                                                           @RequestBody Postulante postulante) {
        Optional<Postulante> existente = postulanteService.obtenerPorId(id);
        if (existente.isPresent()) {
            postulante.setId(id);
            return ResponseEntity.ok(postulanteService.guardarPostulante(postulante));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar postulante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPostulante(@PathVariable Long id) {
        postulanteService.eliminarPostulante(id);
        return ResponseEntity.noContent().build();
    }
}