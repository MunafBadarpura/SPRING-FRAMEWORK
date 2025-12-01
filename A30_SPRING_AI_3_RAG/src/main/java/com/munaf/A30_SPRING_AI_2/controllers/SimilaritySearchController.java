package com.munaf.A30_SPRING_AI_2.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class SimilaritySearchController {

    @Value("classpath:prompts/systemMessage.st")
    private Resource systemPrompt;

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public SimilaritySearchController(@Qualifier("vertexChatClient") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }


    @GetMapping("/search/{query}/{userId}")
    public String manualSearch(@PathVariable String query, @PathVariable String userId) {
        // load data from vector store
        SearchRequest searchRequest = SearchRequest.builder()
                .query(query)
                .topK(4) // how many documents to return
                .similarityThreshold(0.5) // (0.0 to 1.0) , 0.0 means no similarity, 1.0 means exact match
                .build();

        List<Document> documents = vectorStore.similaritySearch(searchRequest);
        List<String> documentList = documents.stream()
                .map(doc -> doc.getText())
                .toList();
        String finalContext = String.join(",", documentList);

        return chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                .user(query)
                .system(s -> s.text(systemPrompt).param("documents", finalContext))
                .call()
                .content();
    }

    @GetMapping("/search-with-qa-advisor/{query}/{userId}")
    public String searchWithQaAdvisor(@PathVariable String query, @PathVariable String userId) {

        QuestionAnswerAdvisor questionAnswerAdvisor = QuestionAnswerAdvisor.builder(vectorStore)
                .searchRequest(SearchRequest.builder().topK(4).similarityThreshold(0.5).build())
                .build();

        return chatClient
                .prompt()
                .advisors(questionAnswerAdvisor)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                .user(query)
                .call()
                .content();
    }


    @GetMapping("/search-with-rag-advisor/{query}/{userId}")
    public String searchWithRagAdvisor(@PathVariable String query, @PathVariable String userId) {

        RetrievalAugmentationAdvisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
                .documentRetriever(VectorStoreDocumentRetriever.builder()
                        .vectorStore(vectorStore)
                        .topK(3)
                        .similarityThreshold(0.5)
                        .build())
                .build();

        return chatClient
                .prompt()
                .advisors(retrievalAugmentationAdvisor)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                .user(query)
                .call()
                .content();
    }


}
