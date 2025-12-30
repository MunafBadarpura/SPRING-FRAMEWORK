package com.munaf.A30_SPRING_AI_ANUJ.util;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImportPdfInVector {

    @Value("classpath:most_asked_doubts.pdf")
    private Resource mostAskedDoubtsPdf;

    private final VectorStore vectorStore;


    public boolean loadPdf() {
        try {
            // read the pdf
            PagePdfDocumentReader pdfDocumentReader = new PagePdfDocumentReader(mostAskedDoubtsPdf);

            // create document
            List<Document> pages = pdfDocumentReader.get();

            // split document into chunks
            TokenTextSplitter textSplitter = TokenTextSplitter.builder()
                    .withChunkSize(400)
                    .build();

            List<Document> chunks = textSplitter.apply(pages);

            // added metadata
            List<Document> finalChunks = chunks.stream()
                    .map(chunk -> (Document) chunk.getMetadata().put("source", "most_asked_doubts.pdf"))
                    .toList();

            // add in vector store
            vectorStore.add(chunks);

            return true;
        } catch (Exception e) {
            System.out.println("Error loading pdf: " + e.getMessage());
            return false;
        }
    }

}
