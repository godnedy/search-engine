package com.findwise.searchengine.document;


import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@Document("document")
class DocumentMongo {

    @Indexed() // ?
    @Id
    public final String id;

    public final String documentContent;

    String getId() {
        return this.id;
    }
}
