package com.example.Chat_Empresa_to_Postulante.service;

import com.example.Chat_Empresa_to_Postulante.model.Perfil;
import com.example.Chat_Empresa_to_Postulante.model.Usuario;
import com.example.Chat_Empresa_to_Postulante.repository.PerfilRepository;
import com.example.Chat_Empresa_to_Postulante.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//Logica para manejar perfiles 
@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository) {
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Crear un perfil para un usuario existente
    public Perfil crearPerfil(Long usuarioId, String correo, String telefono, int edad) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con id: " + usuarioId);
        }
        Usuario usuario = usuarioOpt.get();
        Perfil perfil = new Perfil(correo, telefono, edad, usuario);
        return perfilRepository.save(perfil);
    }

    // Obtener perfil por id de usuario
    public Perfil obtenerPerfilPorUsuarioId(Long usuarioId) {
        return perfilRepository.findByUsuarioId(usuarioId);
    }
}
