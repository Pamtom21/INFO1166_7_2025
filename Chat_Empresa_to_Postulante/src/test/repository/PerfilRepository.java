package com.example.Chat_Empresa_to_Postulante.repository;

import com.example.Chat_Empresa_to_Postulante.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Permite acceder a los datos de perfil
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByUsuarioId(Long usuarioId);
}
