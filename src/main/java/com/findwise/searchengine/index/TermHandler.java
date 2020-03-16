package com.findwise.searchengine.index;

import java.util.LinkedList;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TermHandler { //TODO maybe service?

    private final TermRepository termRepository;

    void updateTerm(WeightedToken token, String documentId) {
        Optional<Term> term = termRepository.findById(token.term);
        if (term.isPresent()) {
            Term existingTerm = term.get();
            existingTerm.addDocumentIdWithTf(new DocumentIdWithTF(documentId, token.tf));
            termRepository.save(existingTerm);
        } else {
            createTerm(token, documentId);
        }
    }

    private void createTerm(WeightedToken token, String documentId) {
        LinkedList<DocumentIdWithTF> list = new LinkedList<>();
        list.add(new DocumentIdWithTF(documentId, token.tf));
        termRepository.save(new Term(token.term, list));
    }
}
