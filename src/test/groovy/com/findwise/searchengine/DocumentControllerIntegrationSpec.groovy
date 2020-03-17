package com.findwise.searchengine

import com.fasterxml.jackson.databind.ObjectMapper
import com.findwise.searchengine.engine.SearchEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@SpringBootTest
class DocumentControllerIntegrationSpec extends Specification {

    private static final String DOCUMENT_1_ID = "Id1"

    private static final String DOCUMENT_1_CONTENT = "really nice content"

    private static final IndexDocumentRequest DEFAULT_REQUEST =
            new IndexDocumentRequest(DOCUMENT_1_ID, DOCUMENT_1_CONTENT)

    ObjectMapper mapper = new ObjectMapper()

    @Autowired
    SearchEngine searchEngine

    @Subject
    DocumentController documentController

    def setup() {
        documentController = new DocumentController(searchEngine)
    }

    def "should index document"() {
        when:
            def response = documentController.indexDocument(DEFAULT_REQUEST)

        then:
            response.statusCode == CREATED
    }

    def "should find term in added document"() {
        given:
            documentController.indexDocument(DEFAULT_REQUEST)
            documentController.indexDocument(new IndexDocumentRequest("id2", "nothing else matters"))
        when:
            ResponseEntity response = documentController.getDocumentList("nice")
        then:
            response.statusCode == OK
            response.body.get(0).getId() == DOCUMENT_1_ID
            response.body.get(0).getScore() > 0
    }

    def "should return empty list if the term is not a match"() {
        given:
            documentController.indexDocument(DEFAULT_REQUEST)
        when:
            ResponseEntity response = documentController.getDocumentList("nicer")
        then:
            response.statusCode == OK
            response.body.size() == 0
    }
}