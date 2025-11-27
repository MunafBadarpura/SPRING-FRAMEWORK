package com.munaf.A30_SPRING_AI_2.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "chat_history")
public class ChatHistory {

    @Id
    @Column(name = "chat_history_id")
    private String chatHistoryId = UUID.randomUUID().toString();

    @Column(name = "chat_history_name")
    private String chatHistoryName;

    @OneToMany(mappedBy = "chatHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatConversation> conversations = new ArrayList<>();

    @Column(name = "user_id")
    private Long userId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
