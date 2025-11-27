package com.munaf.A30_SPRING_AI_2.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "chat_conversation")
public class ChatConversation {

    @Id
    @Column(name = "chat_conversation_id")
    private String chatConversationId = UUID.randomUUID().toString();


    @Column(columnDefinition = "TEXT")
    private String userContent;

    @Column(columnDefinition = "TEXT")
    private String assistantContent;

    @ManyToOne
    @JoinColumn(name = "chat_history_id")
    private ChatHistory chatHistory;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
