package com.domain.repository;

import com.domain.model.chat;
import com.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<chat, Long> {
    List<chat> findByRemitenteOrDestinatario(Usuario remitente, Usuario destinatario);
}