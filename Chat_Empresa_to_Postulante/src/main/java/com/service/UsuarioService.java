package com.example.Chat_Empresa_to_Postulante.service;

import com.example.Chat_Empresa_to_Postulante.model.Usuario;
import com.example.Chat_Empresa_to_Postulante.model.TipoUsuario;
import com.example.Chat_Empresa_to_Postulante.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Registra un usuario con validación de correo único.
     *
     * @param nombre nombre del usuario
     * @param correo correo del usuario
     * @param tipoStr tipo de usuario como String (EMPRESA o POSTULANTE)
     * @return Usuario registrado
     */
    public Usuario registrarUsuario(String nombre, String correo, String tipoStr) {
        if (correoExiste(correo)) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        // Convertimos el tipo de usuario de String a enum de forma segura
        TipoUsuario tipo = TipoUsuario.fromString(tipoStr);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setTipo(tipo);

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
}
