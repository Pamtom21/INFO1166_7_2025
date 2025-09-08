package com.domain.repository;

import com.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);  // buscar usuario por correo
    boolean existsByCorreo(String correo);          // verificar si el correo ya existe
}
