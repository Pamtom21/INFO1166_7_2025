package com.domain.repository;

import com.domain.model.empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Empresa_Repository extends JpaRepository<empresa, Long> {
}
