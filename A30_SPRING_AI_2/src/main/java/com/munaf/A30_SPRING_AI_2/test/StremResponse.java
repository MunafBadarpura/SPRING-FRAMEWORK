package com.munaf.A30_SPRING_AI_2.test;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class StremResponse {

    @Autowired
    private VertexAiGeminiChatModel chatModel;

    @GetMapping(value = "/stream-user/{query}")
    public Flux<String> getStreamAnswer(@PathVariable String query) {

        return chatModel.stream(new Prompt(query))
                .map(chatResponse -> chatResponse.getResult().getOutput().getText())
                // Remove newlines inside bold/italic markers
                .map(text -> text.replaceAll("\\*\\*\\s*([\\s\\S]*?)\\s*\\*\\*", "**$1**"))
                // Split into paragraphs
                .flatMap(paragraphText -> Flux.fromArray(paragraphText.split("\\n\\n")))
                // Stream each paragraph word by word
                .concatMap(paragraph -> {
                    String[] words = paragraph.split(" ");
                    return Flux.fromArray(words)
                            .map(word -> word + " ")
                            .delayElements(Duration.ofMillis(100))
                            .concatWith(Flux.just("\n"));
                })
                .onErrorReturn("Error occurred while streaming response");
    }


    @GetMapping(value = "/stream-user2/{query}")
    public String getStreamAnswer2(@PathVariable String query) {

        return chatModel.call(new Prompt(query))
                .getResult().getOutput().getText();
    }

}
