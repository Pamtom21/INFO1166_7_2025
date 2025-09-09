package com.domain.repository;

import com.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Chat_Repository extends JpaRepository<Chat, Long> {
}