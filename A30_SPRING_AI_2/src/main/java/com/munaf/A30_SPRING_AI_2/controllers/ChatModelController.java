package com.munaf.A30_SPRING_AI_2.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatModelController {

    // when single model using
    // private ChatClient chatClient;

    // public ChatController(ChatClient.Builder chatClientBuilder) {
    //    this.chatClient = chatClientBuilder.build();
    // }


    // when multiple model using
    // private ChatClient ollamaChatClient;
    // private ChatClient vertexChatClient;

    // public ChatController(OllamaChatModel ollamaChatModel, VertexAiGeminiChatModel vertexChatModel) {
    //     this.ollamaChatClient = ChatClient.builder(ollamaChatModel).build();
    //     this.vertexChatClient = ChatClient.builder(vertexChatModel).build();
    // }


    // best practices when multiple model using
    private ChatClient ollamaChatClient;
    private ChatClient vertexChatClient;

    public ChatModelController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient, @Qualifier("vertexChatClient") ChatClient vertexChatClient) {
        this.ollamaChatClient = ollamaChatClient;
        this.vertexChatClient = vertexChatClient;
    }

    @GetMapping("/ollama/{query}")
    public String chatWithOllama(@PathVariable String query) {
        return ollamaChatClient
                .prompt(query)
                .call()
                .content();
    }

    @GetMapping("/vertex/{query}")
    public String chatWithVertex(@PathVariable String query) {
        return vertexChatClient
                .prompt(query)
                .call()
                .content();
    }


    @GetMapping("/vertex/custom/{query}")
    public String chatWithCustomVertex(@PathVariable String query) {
//        return vertexChatClient
//                .prompt(query)
//                .options(VertexAiGeminiChatOptions.builder().temperature(0.7).topP(0.8).build())
//                .call()
//                .content();


        Prompt prompt = new Prompt(query, VertexAiGeminiChatOptions
                .builder()
                .temperature(0.7)
                .model("gemini-2.5-pro")
                .topP(0.8)
                .build()
        );

        return vertexChatClient
                .prompt(prompt)
                .call()
                .content();
    }

}
