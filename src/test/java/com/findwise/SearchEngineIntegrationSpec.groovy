package com.findwise


import com.findwise.searchengine.Document
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SearchEngineIntegrationSpec extends Specification {

    @Subject
    def SearchEngine searchEngine

    @Ignore
    @Unroll
    def "search returns proper list of documents for #query query"() {
        given:
            def documents = documentsToBeIndexed()
            searchEngine.indexDocument(documents.get(0).name, documents.get(0).content)
            searchEngine.indexDocument(documents.get(1).name, documents.get(1).content)
            searchEngine.indexDocument(documents.get(2).name, documents.get(2).content)
        when:
            def result = searchEngine.search()
            def expectedResult = Arrays.asList(expectedDocuments.split(","))
        then:
            result.containsAll(expectedResult)
            expectedResult.containsAll(result)

        where:
            query   ||  expectedDocuments
           "brown"  ||  "Document1, Document2"
           "fox"    ||  "Document1, Document3"
    }

    static List<Document> documentsToBeIndexed(){
        def documents =  new ArrayList<Document>()
        documents.addAll([new Document("Document1", "the brown fox jumped over the brown dog"),
                          new Document("Document2", "the lazy brown dog sat in the corner"),
                          new Document("Document1", "the red fox bit the lazy dog")])
        documents
    }
}
