package com.login.dto;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.login.model.Usuario;
import com.login.repository.UsuarioRepository;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("registroDto", new RegistroDto());
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@Valid @ModelAttribute RegistroDto registroDto, 
                                  BindingResult result, Model model) {
        
        // Validar confirmación de contraseña
        if (!registroDto.getPassword().equals(registroDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.registroDto", "Las contraseñas no coinciden");
        }
        
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByUsername(registroDto.getUsername())) {
            result.rejectValue("username", "error.registroDto", "El usuario ya existe");
        }
        
        if (result.hasErrors()) {
            return "registro";
        }
        
        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(registroDto.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        usuario.setNombreCompleto(registroDto.getNombreCompleto());
        
        usuarioRepository.save(usuario);
        
        return "redirect:/login?registroExitoso";
    }


    @GetMapping("/")
    public String home() {
        return "home";
    }
}