package com.domain.repository;

import com.domain.model.chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Chat_Repository extends JpaRepository<chat, Long> {
}
