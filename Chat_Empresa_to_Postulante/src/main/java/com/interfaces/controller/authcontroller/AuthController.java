package com.interfaces.controller.authcontroller;

import com.domain.model.TipoUsuario;
import com.domain.model.Usuario;
import com.domain.repository.UsuarioRepository;
import com.domain.security.JwtUtil;
import com.service.MyUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AuthController(
        AuthenticationManager authManager,
        MyUserDetailsService userDetailsService,
        JwtUtil jwtUtil,
        PasswordEncoder passwordEncoder,
        UsuarioRepository usuarioRepository
    ) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

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
    public static record RegisterRequest(String nombre, String email, String password, String tipo) {}
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) throws Exception {
        if (userDetailsService.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email/RUT ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre());
        usuario.setEmail(request.email());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setTipo(TipoUsuario.valueOf(request.tipo().toUpperCase()));

        userDetailsService.save(usuario);

        return "Usuario registrado correctamente";
}
}
