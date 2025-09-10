package com.func_chat.service.impl;

import com.func_chat.dto.MessageRequest;
import com.func_chat.dto.MessageResponse;
import com.func_chat.model.Conversation;
import com.func_chat.model.Message;
import com.func_chat.repository.ConversationRepository;
import com.func_chat.repository.MessageRepository;
import com.func_chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    
    @Override
    @Transactional
    public Conversation getOrCreateConversation(Long applicationId, Long companyId, 
                                              Long personId, Long jobOfferId) {
        // ✅ Usar el nombre CORRECTO del método
        return conversationRepository.findByApplicationIdAndCompanyIdAndPersonId(applicationId, companyId, personId)
            .orElseGet(() -> {
                Conversation newConversation = new Conversation();
                newConversation.setApplicationId(applicationId);
                newConversation.setCompanyId(companyId);
                newConversation.setPersonId(personId);
                newConversation.setJobOfferId(jobOfferId);
                newConversation.setIsActive(true);
                return conversationRepository.save(newConversation);
            });
    }
    
    @Override
    @Transactional
    public MessageResponse sendMessage(MessageRequest messageRequest) {
        Conversation conversation = conversationRepository.findById(messageRequest.getConversationId())
            .orElseThrow(() -> new RuntimeException("Conversación no encontrada"));
        
        Message message = new Message();
        message.setConversation(conversation);
        message.setSenderId(messageRequest.getSenderId());
        message.setSenderType(messageRequest.getSenderType());
        message.setContent(messageRequest.getContent());
        
        Message savedMessage = messageRepository.save(message);
        
        // Actualizar timestamp de la conversación
        conversation.setUpdatedAt(LocalDateTime.now());
        conversationRepository.save(conversation);
        
        // Enviar mediante WebSocket
        MessageResponse response = convertToResponse(savedMessage);
        messagingTemplate.convertAndSend(
            "/topic/conversation/" + messageRequest.getConversationId(), 
            response
        );
        
        return response;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> getConversationMessages(Long conversationId) {
        List<Message> messages = messageRepository.findByConversationIdOrderBySentAtAsc(conversationId);
        return messages.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Conversation> getUserConversations(Long userId, String userType) {
        // ✅ Usar el nombre CORRECTO del método
        return conversationRepository.findUserConversations(userId);
    }
    
    @Override
    @Transactional
    public void markMessagesAsRead(Long conversationId, String userType) {
        messageRepository.markMessagesAsRead(conversationId, userType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long getUnreadMessagesCount(Long conversationId, String userType) {
        return messageRepository.countUnreadMessages(conversationId, userType);
    }
    
    @Override
    public MessageResponse convertToResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setSenderId(message.getSenderId());
        response.setSenderType(message.getSenderType());
        response.setContent(message.getContent());
        response.setSentAt(message.getSentAt());
        response.setIsRead(message.getIsRead());
        response.setConversationId(message.getConversation().getId());
        return response;
    }
}