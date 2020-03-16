package com.findwise.searchengine.term;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@RequiredArgsConstructor
@Document(collection = "terms")
public class Term {

    @Id
    private long id;

    @Indexed(unique = true)
    private final String term;

    private final List<DocumentIdWithTF> documentIdsWithTf;

    public void addDocumentIdWithTf(DocumentIdWithTF documentIdWithTF) {
        this.documentIdsWithTf.add(documentIdWithTF);
    }
}