package com.findwise.searchengine.index;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("document")
class DocumentMongo {

    private String documentName;

    private String getDocumentContent;
}
