package com.findwise.searchengine.document


import spock.lang.Specification
import spock.lang.Subject

class DocumentHandlerSpec extends Specification {

    private static final String ID = "Id1"

    def documentRepository = Mock(DocumentRepository)

    @Subject
    DocumentService documentHandler

    def "saveDocument savesDocument in repository" () {
        given:
            documentHandler = new DocumentService(documentRepository)
            def returnedObject = new DocDocument(ID, "bar")
        when:
            documentHandler.saveDocument("name1", "content1")
        then:
            1 * documentRepository.save(_) >> returnedObject
        }

    def "saveDocument returns id of saved document" () {
        given:
            documentHandler = new DocumentService(documentRepository)
            def returnedObject = new DocDocument(ID, "bar")
            documentRepository._(*_) >> returnedObject
        when:
            def result = documentHandler.saveDocument("name1", "content1")
        then:
            result == ID
    }
}
