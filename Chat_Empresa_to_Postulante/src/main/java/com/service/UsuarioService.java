package com.service;

import com.domain.model.Usuario;
import com.domain.model.TipoUsuario;
import com.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para registrar un usuario con validación de correo único
    public Usuario registrarUsuario(String nombre, String tipoStr) {
        // Convertimos el tipo de usuario de String a enum
        TipoUsuario tipo;
        try {
            tipo = TipoUsuario.valueOf(tipoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de usuario inválido. Debe ser EMPRESA o POSTULANTE.");
        }

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setTipo(tipo);

        return usuarioRepository.save(usuario);
    }

    // Método opcional para validar si el correo ya existe
    public boolean correoExiste(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    // Método opcional para obtener un usuario por correo
    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}
