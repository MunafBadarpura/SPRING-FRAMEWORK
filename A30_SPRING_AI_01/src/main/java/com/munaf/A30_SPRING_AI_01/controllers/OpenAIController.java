package com.munaf.A30_SPRING_AI_01.controllers;

import com.munaf.A30_SPRING_AI_01.dto.GeminiResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {


    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    // Use this when you have single ai model
    public OpenAIController(ChatClient.Builder chatClientBuilder) {
        this.chatMemory = MessageWindowChatMemory.builder().build();

        this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    // Use this when you have multiple ai model
//    public OpenAIController(VertexAiGeminiChatModel chatModel) {
//        this.chatMemory = MessageWindowChatMemory.builder().build();
//
//        this.chatClient = chatClientBuilder
//            .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
//            .build();
//    }


    @GetMapping("/chat/{message}")
    public GeminiResponseDTO chat(@PathVariable String message) {
        ChatResponse chatResponse = chatClient.prompt(message).call().chatResponse();
        String response = chatResponse.getResult().getOutput().getText();

        return GeminiResponseDTO.builder()
                .response(response)
                .id(chatResponse.getMetadata().getId())
                .modelName(chatResponse.getMetadata().getModel())
                .promptTokens(chatResponse.getMetadata().getUsage().getPromptTokens())
                .completionTokens(chatResponse.getMetadata().getUsage().getCompletionTokens())
                .build();

    }


    @GetMapping("/recommendMovie") // promoting for specific things
    public GeminiResponseDTO recommendMovie(@RequestParam(required = false, defaultValue = "anything") String type,
                                            @RequestParam(required = false, defaultValue = "anything") String year,
                                            @RequestParam(required = false, defaultValue = "anything") String lang) {

        String promptMessage = """
                Suggest me a movie with type {type} and in year {year} and in language {lang} G
                Give me only 1 movie also give me cast name and length of the movie
                The formate of response is liek this :
                1. Movie Name :
                2. Movie Type :
                3. Movie Year :
                4. Movie Language :
                5. Movie Length :
                6. Movie Cast :
                
                
                noting extra nothing more dont give any thing text without this formate
                """;
        promptMessage = promptMessage.replace("{type}", type);
        promptMessage = promptMessage.replace("{year}", year);
        promptMessage = promptMessage.replace("{lang}", lang);

        PromptTemplate promptTemplate = new PromptTemplate(promptMessage);
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatClient.prompt(prompt).call().chatResponse();
        String response = chatResponse.getResult().getOutput().getText();

        return GeminiResponseDTO.builder()
                .response(response)
                .id(chatResponse.getMetadata().getId())
                .modelName(chatResponse.getMetadata().getModel())
                .promptTokens(chatResponse.getMetadata().getUsage().getPromptTokens())
                .completionTokens(chatResponse.getMetadata().getUsage().getCompletionTokens())
                .build();
    }


//    @GetMapping("/embeddings/{request}")
//    public float[] embeddings(@PathVariable String request) {
//        return embeddingModel.embed(request);
//    }


}
