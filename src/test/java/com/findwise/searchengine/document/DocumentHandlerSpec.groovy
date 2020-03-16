package com.findwise.searchengine.document

import com.findwise.searchengine.Document
import spock.lang.Specification
import spock.lang.Subject

class DocumentHandlerSpec extends Specification {

    private static final Long ID = 1L

    def documentRepository = Mock(DocumentRepository)

    @Subject
    DocumentHandler documentHandler

    def "saveDocument savesDocument in repository" () {
        given:
            documentHandler = new DocumentHandler(documentRepository)
            def document = new Document("name1", "content1")
            def returnedObject = new DocumentMongo("foo", "bar")
        when:
            documentHandler.saveDocument(document)
        then:
            1 * documentRepository.save(_) >> returnedObject
        }

    def "saveDocument returns id of saved document" () {
        given:
            documentHandler = new DocumentHandler(documentRepository)
            def document = new Document("name1", "content1")
            def returnedObject = new DocumentMongo("foo", "bar")
            returnedObject.id = ID;
            documentRepository._(*_) >> returnedObject
        when:
            def result = documentHandler.saveDocument(document)
        then:
            result == ID
    }
}
