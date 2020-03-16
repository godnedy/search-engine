package com.findwise.searchengine.index


import spock.lang.Specification
import spock.lang.Subject

class TermHandlerSpec extends Specification {

    private static final TERM = "term"

    def termRepository = Mock(TermRepository)

    @Subject
    TermHandler termHandler

    def "saveTerm saves an object with document id and tf"() {
        given:
            termRepository._(*_) >> Optional.empty()
            termHandler = new TermHandler(termRepository)
            def newToken = new WeightedToken(TERM, 0.5d)
        when:
            termHandler.updateTerm(newToken, "documentID")
        then:
            1 ==1

            1 * termRepository.save(_ as Term) >> { Term t ->
                assert t.documentIdsWithTf.size() == 1
                assert t.documentIdsWithTf.get(0).tf == 0.5d
                assert t.documentIdsWithTf.get(0).documentId == "documentID"

            }
    }

    def "saveTerm adds next object with document id and tf to term if term already exists"() {
        given:
            LinkedList<DocumentIdWithTF> list = new LinkedList<>();
            list.add(new DocumentIdWithTF("Id1", 0.3d));
            def existingTerm = new Term(TERM, list)
            termRepository._(*_) >> Optional.of(existingTerm)
            termHandler = new TermHandler(termRepository)
            def newToken = new WeightedToken(TERM, 0.5d)
        when:
            termHandler.updateTerm(newToken, "Id2")
        then:
            1 * termRepository.save(_ as Term) >> { Term t ->
                assert t.documentIdsWithTf.size() == 2
                assert t.documentIdsWithTf.get(0).tf == 0.3d
                assert t.documentIdsWithTf.get(0).documentId == "Id1"
                assert t.documentIdsWithTf.get(1).tf == 0.5d
                assert t.documentIdsWithTf.get(1).documentId == "Id2"
            }
    }
}
