package com.findwise.searchengine.term;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@RequiredArgsConstructor
@Document(collection = "terms")
class TermDocument {

    @Id
    private final String termValue;

    private final List<DocumentIdWithTF> documentIdsWithTf;

    void addDocumentIdWithTf(DocumentIdWithTF documentIdWithTF) {
        this.documentIdsWithTf.add(documentIdWithTF);
    }
}