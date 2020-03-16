package com.findwise.searchengine.document;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@Document("document")
class DocumentMongo {

    @Id
    private long id;

    final String documentName;

    final String documentContent;

    Long getId() {
        return this.id;
    }
}
