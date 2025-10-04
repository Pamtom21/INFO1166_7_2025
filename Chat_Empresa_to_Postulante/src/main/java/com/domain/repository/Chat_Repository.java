package com.domain.repository;

import com.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Chat_Repository extends JpaRepository<Chat, Long> {
    // Consultas personalizadas pueden agregarse aquí si se necesitan
}
