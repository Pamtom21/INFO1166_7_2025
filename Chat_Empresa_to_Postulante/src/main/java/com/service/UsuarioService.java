package com.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.domain.model.TipoUsuario;
import com.domain.model.Usuario;
import com.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Registra un usuario con validación de correo único.
     */
    public Usuario registrarUsuario(String nombre, String correo, String tipoStr) {
        if (correoExiste(correo)) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        TipoUsuario tipo = TipoUsuario.valueOf(tipoStr.toUpperCase());

        Usuario usuario = new Usuario(nombre, correo, tipo);

        return usuarioRepository.save(usuario);
    }

    /**
     * Verifica si un correo ya existe.
     */
    public boolean correoExiste(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    /**
     * Obtiene un usuario por correo.
     */
    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    /**
     * Obtiene un usuario por su ID.
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}
