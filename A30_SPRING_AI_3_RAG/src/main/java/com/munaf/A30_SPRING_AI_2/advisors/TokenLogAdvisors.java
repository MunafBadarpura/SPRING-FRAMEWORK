package com.munaf.A30_SPRING_AI_2.advisors;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

public class TokenLogAdvisors implements CallAdvisor, StreamAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        // Before calling llm
        System.out.println("TokenLogAdvisors is called ...");

        System.out.println("Request" + chatClientRequest.prompt().getContents());

        // Pass query to llm
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        // After llm called

        System.out.println("Response : " + chatClientResponse.chatResponse().getResult().getOutput().getText());
        System.out.println("Prompt Tokens : " + chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
        System.out.println("Completion Tokens : " + chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());

        return chatClientResponse;

    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {

        System.out.println("Stream TokenLogAdvisors is called ...");
        System.out.println("Model Name : " + chatClientRequest.prompt().getOptions().getModel());

        Flux<ChatClientResponse> chatClientResponseFlux = streamAdvisorChain.nextStream(chatClientRequest);
        return chatClientResponseFlux;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
