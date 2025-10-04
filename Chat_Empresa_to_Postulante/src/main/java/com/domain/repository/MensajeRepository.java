package com.domain.repository;

import com.domain.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Buscar mensajes por chat (por id del chat)
    List<Mensaje> findByChatIdChat(Long chatId);

    // Opcional: buscar mensajes entre dos usuarios por remitente y destinatario
    // List<Mensaje> findByRemitenteIdAndDestinatarioId(Long remitenteId, Long destinatarioId);
}
