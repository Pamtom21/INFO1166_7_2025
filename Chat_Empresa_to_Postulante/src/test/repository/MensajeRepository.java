package com.domain.repository;

import com.domain.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Buscar mensajes por chat
    List<Mensaje> findByChatIdChat(Long chatId);
}
