package com.findwise.searchengine.index;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@Document(collection = "terms")
public class Term {

    @Id
    private final String term;

    private final List<DocumentIdWithTF> documentIdsWithTFS;

    public List<DocumentIdWithTF> getDocumentIdsWithTf() {
        return documentIdsWithTFS;
    }

    public void addDocumentIdWithTf(DocumentIdWithTF documentIdWithTF) {
        this.documentIdsWithTFS.add(documentIdWithTF);
    }
}