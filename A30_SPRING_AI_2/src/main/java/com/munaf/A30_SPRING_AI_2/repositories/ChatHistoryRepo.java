package com.munaf.A30_SPRING_AI_2.repositories;

import com.munaf.A30_SPRING_AI_2.entities.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatHistoryRepo extends JpaRepository<ChatHistory, String> {
    List<ChatHistory> findByUserIdOrderByUpdatedAtDesc(Long userId);

    List<ChatHistory> findByUserIdAndChatHistoryNameContainingIgnoreCaseOrderByUpdatedAtDesc(Long userId, String searchText);
}
