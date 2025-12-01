package com.munaf.A30_SPRING_AI_2.configs;

import org.springframework.ai.vertexai.embedding.VertexAiEmbeddingConnectionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertexEmbeddingConfig {

    @Value("${spring.ai.vertex.ai.embedding.project-id}")
    private String projectId;

    @Value("${spring.ai.vertex.ai.embedding.location}")
    private String location;

    @Bean
    VertexAiEmbeddingConnectionDetails connectionDetails() {
        return VertexAiEmbeddingConnectionDetails.builder()
                .projectId(projectId)
                .location(location)
                .build();
    }
}