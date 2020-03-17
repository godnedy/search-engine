package com.findwise.searchengine.term

import spock.lang.Specification
import spock.lang.Subject

class TermDocumentFinderSpec extends Specification {

    private final static String TERM = "termValue"

    def termRepository = Mock(TermRepository)

    @Subject
    TermService termService

    def "getDocuments returns a list of DocumentIdsWithTf assigned to term" () {
        given:
            termService = new TermService(termRepository)
            termRepository.findFirstByTermValue(TERM) >> existingTermWithDocuments()
        when:
            List<DocumentIdWithTF> result = termService.getDocuments(TERM)
        then:
            result.size() == 2
            result.get(0).documentId == "Id1"
            result.get(0).tf == 0.1d
            result.get(1).documentId == "Id2"
            result.get(1).tf == 0.2d
    }

    def "getDocuments returns empty list if no term found" () {
        given:
            termService = new TermService(termRepository)
            termRepository.findFirstByTermValue(TERM) >> Optional.empty()
        when:
            List<DocumentIdWithTF> result = termService.getDocuments(TERM)
        then:
            result.size() == 0
    }

    def existingTermWithDocuments() {
        LinkedList<DocumentIdWithTF> documents = new LinkedList<>()
        documents.add(new DocumentIdWithTF("Id1", 0.1d))
        documents.add(new DocumentIdWithTF("Id2", 0.2d))
        return Optional.of(new TermDocument(TERM, documents))
    }
}
