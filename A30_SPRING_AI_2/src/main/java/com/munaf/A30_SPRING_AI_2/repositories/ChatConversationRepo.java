package com.munaf.A30_SPRING_AI_2.repositories;

import com.munaf.A30_SPRING_AI_2.entities.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatConversationRepo extends JpaRepository<ChatConversation, String> {

    List<ChatConversation> findByChatHistory_chatHistoryIdOrderByCreatedAtAsc(String chatHistoryId);
}
