package com.munaf.A30_SPRING_AI_2.services.impl;

import com.munaf.A30_SPRING_AI_2.services.DataLoaderService;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

    @Value("classpath:schema.json")
    private Resource schemaJsonResource;


    @Override
    public void loadDataFromJson() {
        JsonReader jsonReader = new JsonReader(schemaJsonResource);

        List<Document> documentsList = jsonReader.read();

        System.out.println("Total Documents : " + documentsList.size());
        for (Document document : documentsList) {
            System.out.println("Document : " + document);
        }

        return;
    }

}
