package com.func_chat.service;

import com.func_chat.dto.MessageRequest;
import com.func_chat.dto.MessageResponse;
import com.func_chat.model.Conversation;
import com.func_chat.model.Message;
import java.util.List;

public interface ChatService {
    Conversation getOrCreateConversation(Long applicationId, Long companyId, 
                                       Long personId, Long jobOfferId);
    MessageResponse sendMessage(MessageRequest messageRequest);
    List<MessageResponse> getConversationMessages(Long conversationId);
    List<Conversation> getUserConversations(Long userId, String userType);
    void markMessagesAsRead(Long conversationId, String userType);
    Long getUnreadMessagesCount(Long conversationId, String userType);
    MessageResponse convertToResponse(Message message);
}