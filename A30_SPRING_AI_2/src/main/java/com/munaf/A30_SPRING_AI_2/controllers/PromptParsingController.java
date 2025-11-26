package com.munaf.A30_SPRING_AI_2.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat/prompt")
public class PromptParsingController {

    private final ChatClient vertexChatClient;

    @Value("classpath:prompts/systemMessage.st")
    private Resource programmingSystemMessage;

    public PromptParsingController(@Qualifier("vertexChatClient") ChatClient chatClient) {
        this.vertexChatClient = chatClient;
    }


    @GetMapping("/{query}")
    public String parsePrompt(@PathVariable String query) {
        String content = vertexChatClient
                .prompt()
                .user(query)
                .system("Expert as a backend developer")
                .call()
                .content();

        return content;
    }

    @GetMapping("meta-data/{query}")
    public String chatResponseMetaData(String query) {
        ChatResponse chatResponse = vertexChatClient
                .prompt()
                .user(query)
                .system("Expert as a backend developer")
                .call()
                .chatResponse();


        // we can get getMetadata(), getResult(),
        // inside metadata we can getPromptMetadata(), getModel(), getRateLimit(), getUsage()

        // for result => chatResponse.getResult().getOutput().getText();
        // for modelName = chatResponse.getMetadata().getModel()
        // for promptTokens = chatResponse.getMetadata().getUsage().getPromptTokens()
        // for completionTokens = chatResponse.getMetadata().getUsage().getCompletionTokens()


        return chatResponse.getResult().getOutput().getText();
    }


    @GetMapping("/dynamic/{query}/{language}") // here pass task and programming language
    public String chatResponseDynamic(@PathVariable  String query, @PathVariable String language) {

        String prompt = "Act as a programming expert in {language}, and create code for {query}";

        String content = vertexChatClient
                .prompt()
                .user(p -> p.text(prompt)
                        .param("language", language)
                        .param("query", query)
                )
                .call()
                .content();

        return content;
    }


    @GetMapping("/templet/{query}/{language}") // here pass task and programming language
    public String promptTempleting(@PathVariable  String query, @PathVariable String language) {

        String rawPrompt = "Act as a programming expert in {language}, and create code for {query}";

        // step 1 : create prompt template
        PromptTemplate promptTemplate = PromptTemplate.builder()
                .template(rawPrompt)
                .build();

        // step 2 : render prompt template
        String prompt = promptTemplate.render(
                Map.of("language", language, "query", query)
        );

        // step 3 : call chat client
        String content = vertexChatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        return content;
    }


    @GetMapping("/templet-with-system-prompt/{query}/{language}") // here pass task and programming language
    public String promptTempletingWithSystemPrompt(@PathVariable(value = "query") String userQuery, @PathVariable String language) {

        String systemPrompt = "Act as a programming expert in {language}";

        // step 1 : create system prompt template
        SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder()
                .template(systemPrompt)
                .build();

        // step 2 : render system prompt template (use createMessage so we can pass multiple in chatClient)
        Message systemPromptMessage = systemPromptTemplate.createMessage(
                Map.of("language", language)
        );

        // step 3 : create PromptTemplate
        PromptTemplate promptTemplate = PromptTemplate.builder()
                .template(userQuery)
                .build();

        // step 4 : render prompt template (use createMessage so we can pass multiple in chatClient)
        Message userPromptMessage = promptTemplate.createMessage(
                Map.of("query", userQuery)
        );

        // step 5 : create Prompt object
        Prompt prompt = new Prompt(systemPromptMessage, userPromptMessage);

        // step 6 : call chat client
        String content = vertexChatClient
                .prompt(prompt)
                .call()
                .content();

        return content;
    }


    @GetMapping("/templet-with-fluent-api/{query}/{language}") // here pass task and programming language
    public String promptTempletingWithFluentApi(@PathVariable(value = "query") String query, @PathVariable String language) {
        String userQuery = "Give me {query} and in {language}";

        return vertexChatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system(s -> s.text(programmingSystemMessage)
                        .param("language", language)
                )
                .user(u -> u.text(userQuery)
                        .param("query", query)
                        .param("language", language)
                )
                .call()
                .content();
    }



}
