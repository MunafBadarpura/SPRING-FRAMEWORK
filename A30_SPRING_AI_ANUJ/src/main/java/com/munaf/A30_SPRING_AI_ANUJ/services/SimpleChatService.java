package com.munaf.A30_SPRING_AI_ANUJ.services;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SimpleChatService {

    private final ChatClient vertexChatClient;
    private final VectorStore vectorStore;


    public String simpleChat(String userQuery) {
        return vertexChatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .user(userQuery)
                .system("Help user with their query")
                .call()
                .content();
    }


    public String mostAskedDoubts(String userQuery) {
        String systemPrompt = """
                You are a helpful assistant.
                You are given a question by the user.
                You are also given a list of most asked doubts and their answers. 
                Your task is to find the most relevant answer to the user's question from the list of most asked doubts and their answers. 
                If you are not able to find the answer in the list of most asked doubts and their answers, then you should say 'I am not sure about the answer to this question'. 
                The list of most asked doubts and their answers is as follows: {context}
                The user's question is: {question}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(systemPrompt);


        SearchRequest searchRequest = SearchRequest.builder()
                .query(userQuery)
                .topK(3)
                .build();

        List<Document> documents = vectorStore.similaritySearch(searchRequest);

        List<String> documentList = documents.stream()
                .map(doc -> doc.getText())
                .toList();
        String finalContext = String.join(",", documentList);

        String renderedPrompt = promptTemplate.render(Map.of("context", finalContext, "question", userQuery ));

        return vertexChatClient
                .prompt(renderedPrompt)
                .call()
                .content();
    }

}
