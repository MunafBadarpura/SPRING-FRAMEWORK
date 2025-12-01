package com.munaf.A30_SPRING_AI_2.controllers;

import com.munaf.A30_SPRING_AI_2.dto.ChatConversationDTO;
import com.munaf.A30_SPRING_AI_2.dto.ChatHistoryDTO;
import com.munaf.A30_SPRING_AI_2.dto.PromptRequest;
import com.munaf.A30_SPRING_AI_2.entities.ChatConversation;
import com.munaf.A30_SPRING_AI_2.entities.ChatHistory;
import com.munaf.A30_SPRING_AI_2.repositories.ChatConversationRepo;
import com.munaf.A30_SPRING_AI_2.repositories.ChatHistoryRepo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/chat/memory")
@CrossOrigin("*")
public class ChatMemoryController {

    @Autowired
    private ChatHistoryRepo chatHistoryRepo;

    @Autowired
    private ChatConversationRepo chatConversationRepo;

    private final ChatClient vertexChatClient;

    public ChatMemoryController(@Qualifier("vertexChatClient") ChatClient vertexChatClient) {
        this.vertexChatClient = vertexChatClient;
    }

    @GetMapping("/response/{prompt}/{userId}")
    public String chatMemoryResponse(@PathVariable String prompt, @PathVariable String userId) {
        return vertexChatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                .user(prompt)
                .call()
                .content();
    }

    // call LLM
    @PostMapping("/gpt")
    public Flux<String> chatMemoryResponse2(@RequestBody PromptRequest promptRequest) {
        String userPrompt = promptRequest.getUserPrompt();
        String chatHistoryId = promptRequest.getChatHistoryId();
        Long userId = promptRequest.getUserId();
        String model = promptRequest.getModel();

        if (userPrompt == null || userPrompt.isEmpty()) throw new RuntimeException("Prompt is required");

        ChatHistory chatHistory;

        // 1. if chatHistoryId is null then create new chat history
        if (chatHistoryId == null || chatHistoryId.isEmpty()) {
            chatHistory = new ChatHistory();
            chatHistory.setChatHistoryName(userPrompt.length() <= 6 ? userPrompt : userPrompt.substring(0, 6) + "...");
            chatHistory.setUserId(userId);
            chatHistory = chatHistoryRepo.save(chatHistory); // save new chat
            chatHistoryId = chatHistory.getChatHistoryId();
        } else {
            chatHistory = chatHistoryRepo.findById(chatHistoryId)
                    .orElseThrow(() -> new RuntimeException("Chat history not found"));
        }

        // 3. Create Prompt
        Prompt prompt = new Prompt(userPrompt, VertexAiGeminiChatOptions
                .builder()
                .model(model)
                .build()
        );

        // 2. call LLM
        String finalChatHistoryId = chatHistoryId;
        Flux<String> aiResponse =  vertexChatClient
                .prompt(prompt)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, finalChatHistoryId))
                .stream()
                .content();

//        // 3. create and save conversation
//        ChatConversation chatConversation = new ChatConversation();
//        chatConversation.setChatHistory(chatHistory);
//        chatConversation.setUserContent(userPrompt);
//        chatConversation.setAssistantContent(aiResponse);
//        chatConversation = chatConversationRepo.save(chatConversation);

        // 4. Save conversation AFTER collecting AI response chunks
        ChatHistory finalChatHistory = chatHistory;
        aiResponse
                .collectList()
                .map(list -> String.join("", list)) // combine chunks
                .doOnNext(fullResponse -> {
                    ChatConversation chatConversation = new ChatConversation();
                    chatConversation.setChatHistory(finalChatHistory);
                    chatConversation.setUserContent(userPrompt);
                    chatConversation.setAssistantContent(fullResponse);
                    chatConversationRepo.save(chatConversation);
                })
                .subscribe();   // run async

        // 5. Return streaming Flux to client
        return aiResponse;
    }

    // get all history
    @GetMapping("/history/{userId}")
    public List<ChatHistoryDTO> getAllHistory(@PathVariable Long userId) {
        List<ChatHistory> byUserIdOrderByUpdatedAtDesc = chatHistoryRepo.findByUserIdOrderByUpdatedAtDesc(userId);

        // convert to dto
        return byUserIdOrderByUpdatedAtDesc.stream()
                .map(ChatHistoryDTO::mapToDto)
                .toList();
    }

    // search history
    @GetMapping("history/search/{searchText}/{userId}")
    public List<ChatHistoryDTO> searchHistory(@PathVariable String searchText, @PathVariable Long userId) {
        List<ChatHistory> results =
                chatHistoryRepo.findByUserIdAndChatHistoryNameContainingIgnoreCaseOrderByUpdatedAtDesc(
                        userId,
                        searchText
                );

        // convert to dto
        return results.stream()
                .map(ChatHistoryDTO::mapToDto)
                .toList();
    }

    // update history name
    @PutMapping("/history/{chatHistoryId}")
    public ChatHistoryDTO updateHistoryName(@PathVariable String chatHistoryId, @RequestParam String chatHistoryName) {
        ChatHistory chatHistory = chatHistoryRepo.findById(chatHistoryId)
                .orElseThrow(() -> new RuntimeException("Chat history not found"));
        chatHistory.setChatHistoryName(chatHistoryName);
        chatHistory = chatHistoryRepo.save(chatHistory);
        return ChatHistoryDTO.mapToDto(chatHistory);
    }

    // delete history
    @DeleteMapping("/history/{chatHistoryId}")
    public void deleteHistory(@PathVariable String chatHistoryId) {
        chatHistoryRepo.deleteById(chatHistoryId);
    }

    // get conversation
    @GetMapping("/conversation/{chatHistoryId}")
    public List<ChatConversationDTO> findByChatHistoryIdOrderByCreatedAtAsc(@PathVariable String chatHistoryId) {
        List<ChatConversation> byChatHistoryChatHistoryIdOrderByCreatedAtAsc = chatConversationRepo.findByChatHistory_chatHistoryIdOrderByCreatedAtAsc(chatHistoryId);

        return byChatHistoryChatHistoryIdOrderByCreatedAtAsc.stream()
                .map(ChatConversationDTO::mapToDto)
                .toList();
    }

    // get all models
    @GetMapping("/get-models")
    public List<String> getModels() {
        return List.of(
                "gemini-2.0-flash",
                "gemini-2.5-pro", // supporting stream response
                "gemini-2.5-flash", // supporting stream response
                "gemini-2.5-flash-lite"
        );
    }

}

//convoId = userId_randomId
//
//user a => when conversationId is = null
//    then create and save in db
//    then in next we get convoId