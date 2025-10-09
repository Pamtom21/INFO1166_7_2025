package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.model.Postulante;
import com.domain.repository.PostulanteRepository;

@Service
public class PostulanteService {

    @Autowired
    private PostulanteRepository postulanteRepository;

    // Crear o actualizar un postulante
    public Postulante guardarPostulante(Postulante postulante) {
        return postulanteRepository.save(postulante);
    }

    // Obtener todos los postulantes
    public List<Postulante> obtenerTodos() {
        return postulanteRepository.findAll();
    }

    // Obtener postulante por ID
    public Optional<Postulante> obtenerPorId(Long id) {
        return postulanteRepository.findById(id);
    }

    // Eliminar postulante
    public void eliminarPostulante(Long id) {
        postulanteRepository.deleteById(id);
    }
}
