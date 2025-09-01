package com.example.Chat_Empresa_to_Postulante.repository;

import com.example.Chat_Empresa_to_Postulante.model.chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Chat_Repository extends JpaRepository<chat, Long> {
}
