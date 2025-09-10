package com.func_chat.repository;

import com.func_chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationIdOrderBySentAtAsc(Long conversationId);
    
    @Query("SELECT COUNT(m) FROM Message m WHERE m.conversation.id = :conversationId " +
           "AND m.isRead = false AND m.senderType != :userType")
    Long countUnreadMessages(@Param("conversationId") Long conversationId, 
                            @Param("userType") String userType);
    
    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId " +
           "AND m.sentAt > :since ORDER BY m.sentAt ASC")
    List<Message> findMessagesSince(@Param("conversationId") Long conversationId, 
                                   @Param("since") LocalDateTime since);
    
    @Modifying
    @Query("UPDATE Message m SET m.isRead = true WHERE m.conversation.id = :conversationId " +
           "AND m.senderType != :userType AND m.isRead = false")
    void markMessagesAsRead(@Param("conversationId") Long conversationId, 
                           @Param("userType") String userType);
    
    @Query(value = "SELECT DISTINCT ON (conversation_id) * FROM messages " +
           "WHERE conversation_id IN :conversationIds ORDER BY conversation_id, sent_at DESC", 
           nativeQuery = true)
    List<Message> findLastMessagesByConversationIds(@Param("conversationIds") List<Long> conversationIds);
}