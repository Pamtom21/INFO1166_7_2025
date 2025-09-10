package com.func_chat.repository;

import com.func_chat.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    
    // Método para buscar por applicationId
    Optional<Conversation> findByApplicationId(Long applicationId);
    
    // Métodos para buscar por companyId y personId
    List<Conversation> findByCompanyId(Long companyId);
    List<Conversation> findByPersonId(Long personId);
    
    // Método para buscar conversaciones de usuario
    @Query("SELECT c FROM Conversation c WHERE " +
           "(c.companyId = :userId OR c.personId = :userId) AND c.isActive = true " +
           "ORDER BY c.updatedAt DESC")
    List<Conversation> findUserConversations(@Param("userId") Long userId);
    
    // Método para buscar por companyId, personId y jobOfferId
    Optional<Conversation> findByCompanyIdAndPersonIdAndJobOfferId(
        @Param("companyId") Long companyId, 
        @Param("personId") Long personId, 
        @Param("jobOfferId") Long jobOfferId);
    
    // Método CORREGIDO: el nombre debe coincidir exactamente
    @Query("SELECT c FROM Conversation c WHERE c.applicationId = :applicationId " +
           "AND c.companyId = :companyId AND c.personId = :personId")
    Optional<Conversation> findByApplicationIdAndCompanyIdAndPersonId(
        @Param("applicationId") Long applicationId,
        @Param("companyId") Long companyId,
        @Param("personId") Long personId);
}