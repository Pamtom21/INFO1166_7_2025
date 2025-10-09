package com.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.model.Postulante;

@Repository
public interface PostulanteRepository extends JpaRepository<Postulante, Long> {
    // Permite buscar un postulante por correo
    Optional<Postulante> findByCorreo(String correo);
}
