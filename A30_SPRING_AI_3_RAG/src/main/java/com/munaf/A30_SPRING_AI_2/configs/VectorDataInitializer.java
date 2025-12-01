package com.munaf.A30_SPRING_AI_2.configs;

import com.munaf.A30_SPRING_AI_2.utils.Helper;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VectorDataInitializer {

    private final VectorStore vectorStore;
    private final Helper helper;

    public VectorDataInitializer(VectorStore vectorStore, Helper helper) {
        this.vectorStore = vectorStore;
        this.helper = helper;
    }

    // uncomment to load data into vector store

//    @PostConstruct
//    public void loadSchemaIntoVectorStore() {
//        System.out.println("STARTED LOADING SCHEMA INTO VECTOR STORE");
//
//        // fetching all data
//        List<String> data = helper.getData();
//
//        // converting to documents
//        List<Document> documents = data.stream()
//                .map(d -> {
//                    Document doc = new Document(d);
//                    doc.getMetadata().put("source", "user-profile"); // this is optional (to identify the source of the data)
//                    return doc;
//                })
//                .toList();
//
//        // adding to vector store
//        vectorStore.add(documents);
//
//        System.out.println("FINISHED LOADING SCHEMA INTO VECTOR STORE");
//    }

}
