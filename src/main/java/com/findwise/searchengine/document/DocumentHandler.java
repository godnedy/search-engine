package com.findwise.searchengine.document;

import com.findwise.searchengine.Document;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DocumentHandler {

    private final DocumentRepository documentRepository;

    public Long saveDocument(Document document) {
        DocumentMongo mongo = new DocumentMongo(document.name, document.content);
        return documentRepository.save(mongo).getId();
    }

}
