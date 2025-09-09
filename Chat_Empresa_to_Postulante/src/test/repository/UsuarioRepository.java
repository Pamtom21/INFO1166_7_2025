package com.example.Chat_Empresa_to_Postulante.repository;

import com.example.Chat_Empresa_to_Postulante.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Permite acceder a los datos del usuario 
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
