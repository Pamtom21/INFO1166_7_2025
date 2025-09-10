package com.func_chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    private Long senderId;
    private String senderType; // "COMPANY" or "PERSON"
    private String content;
    private Long conversationId;
}