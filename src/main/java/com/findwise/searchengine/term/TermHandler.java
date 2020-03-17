package com.findwise.searchengine.term;

import java.util.LinkedList;
import java.util.Optional;

import com.findwise.searchengine.index.WeightedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TermHandler { //TODO maybe service?

    private final TermRepository termRepository;

    public void updateTerm(WeightedToken token, String documentId) {
        Optional<Term> term = termRepository.findFirstByTerm(token.term);
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
