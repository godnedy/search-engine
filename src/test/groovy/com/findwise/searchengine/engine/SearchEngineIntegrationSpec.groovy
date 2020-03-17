package com.findwise.searchengine.engine


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@SpringBootTest
class SearchEngineIntegrationSpec extends Specification {

    private static final String ID1 = "Document1"
    private static final String ID2 = "Document2"
    private static final String ID3 = "Document3"

    @Subject
    @Autowired
    SearchEngine searchEngine

    @Unroll
    def "search returns proper list of documents for #query query"() {
        given:
            searchEngine.indexDocument(ID1, "the brown fox jumped over the brown dog")
            searchEngine.indexDocument(ID2, "the lazy brown dog sat in the corner")
            searchEngine.indexDocument(ID3, "the red fox bit the lazy dog")
        when:
            def result = searchEngine.search("brown")
        then:
            result.size() == 2
            result.get(0).id == ID1
            result.get(1).id == ID2
        when:
            def result2 = searchEngine.search("fox")
        then:
            result2.size() == 2
            result2.get(0).id == ID3
            result2.get(1).id == ID1
    }
}
