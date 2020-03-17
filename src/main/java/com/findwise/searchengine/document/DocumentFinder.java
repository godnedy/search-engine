package com.findwise.searchengine.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentFinder {

    private final DocumentRepository documentRepository;

    public long getTotalNumberOfIndexedDocuments() {
        return documentRepository.count();
    }
}
