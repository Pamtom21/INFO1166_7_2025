package com.domain.controller;

import com.domain.model.TipoUsuario;
import com.domain.model.Usuario;
import com.domain.repository.UsuarioRepository;
import com.domain.security.JwtUtil;
import com.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // DTO para recibir login como JSON
    public static record LoginRequest(String email, String password) {}
    public static record LoginResponse(String token, String email, String nombre) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email o contraseña incorrectos");
        }

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        String token = jwtUtil.generateToken(usuario.getEmail());

        return new LoginResponse(token, usuario.getEmail(), usuario.getNombre());
    }
    @PostMapping("/register")
    public String register(@RequestParam String nombre,
                        @RequestParam String email,
                        @RequestParam String password,
                        @RequestParam String tipo) throws Exception {

        // Verificar si el email ya existe
        if (userDetailsService.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email/RUT ya está registrado");
        }

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(password)); // contraseña encriptada
        usuario.setTipo(TipoUsuario.valueOf(tipo.toUpperCase()));

        userDetailsService.save(usuario);

        return "Usuario registrado correctamente";
    }
    
}
