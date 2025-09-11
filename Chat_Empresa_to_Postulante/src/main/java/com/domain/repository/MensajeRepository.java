package com.domain.repository;

import com.domain.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByChatId(Long chatId); // Esto funciona porque Chat tiene propiedad "id"
}

