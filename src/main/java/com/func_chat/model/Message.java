package com.func_chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;
    
    @Column(name = "sender_id", nullable = false)
    private Long senderId;
    
    @Column(name = "sender_type", nullable = false, length = 20)
    private String senderType; // "COMPANY" or "PERSON"
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
    
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;
    
    @PrePersist
    protected void onCreate() {
        sentAt = LocalDateTime.now();
    }
}