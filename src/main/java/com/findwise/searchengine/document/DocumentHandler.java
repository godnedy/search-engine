package com.findwise.searchengine.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentHandler {

    private final DocumentRepository documentRepository;

    public String saveDocument(String id, String content) {
        DocumentMongo mongo = new DocumentMongo(id, content);
        return documentRepository.save(mongo).getId();
    }

}
