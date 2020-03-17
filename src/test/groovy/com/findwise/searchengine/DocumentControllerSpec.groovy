package com.findwise.searchengine.controller

import com.findwise.searchengine.engine.SearchEngine
import com.findwise.searchengine.DocumentController
import com.findwise.searchengine.IndexDocumentRequest
import spock.lang.Specification

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

class DocumentControllerSpec extends Specification {

    def searchEngine = Mock(SearchEngine)

    def controller = new DocumentController(searchEngine)

    def "should call indexingService while saving documents"() {
        given:
            def request = new IndexDocumentRequest("id", "content")

        when:
            def response = controller.indexDocument(request)

        then:
            1 * searchEngine.indexDocument(*_)
            response.statusCode == CREATED
    }

    def "should call searchService while looking for a term"() {
        given:
            def term = "termValue"
        when:
            def response = controller.getDocumentList(term)
        then:
            1 * searchEngine.search(term)
            response.statusCode == OK
    }

}