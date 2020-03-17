package com.findwise.searchengine.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public String saveDocument(String id, String content) {
        DocDocument mongo = new DocDocument(id, content);
        return documentRepository.save(mongo).getId();
    }

    public long getTotalNumberOfIndexedDocuments() {
        return documentRepository.count();
    }

    public boolean exists(String id) {
        return documentRepository.existsById(id);
    }


}
