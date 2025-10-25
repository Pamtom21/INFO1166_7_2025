package com.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.domain.model.Perfil;
import com.domain.model.Usuario;
import com.domain.repository.PerfilRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public Perfil crearPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public List<Perfil> obtenerTodos() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> obtenerPorId(Long id) {
        return perfilRepository.findById(id);
    }

    public Optional<Perfil> obtenerPorUsuario(Usuario usuario) {
        return perfilRepository.findByUsuario(usuario);
    }

    public Perfil actualizarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public void eliminarPerfil(Long id) {
        perfilRepository.deleteById(id);
    }
}