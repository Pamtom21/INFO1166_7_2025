package com.example.Chat_Empresa_to_Postulante.repository;

import com.example.Chat_Empresa_to_Postulante.model.empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Empresa_Repository extends JpaRepository<empresa, Long> {
}
