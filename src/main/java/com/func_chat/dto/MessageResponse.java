package com.func_chat.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {
    private Long id;
    private Long senderId;
    private String senderType;
    private String content;
    private LocalDateTime sentAt;
    private Boolean isRead;
    private Long conversationId;
}