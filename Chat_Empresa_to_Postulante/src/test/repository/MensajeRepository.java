package com.example.Chat_Empresa_to_Postulante.repository;

import com.example.Chat_Empresa_to_Postulante.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//busca mensaje entre dos usuarios
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByRemitenteIdAndDestinatarioId(Long remitenteId, Long destinatarioId);
}
