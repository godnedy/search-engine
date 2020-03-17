package com.findwise.searchengine.term;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TermFinder {

    private final TermRepository termRepository;

    public List<DocumentIdWithTF> getDocuments(String termValue) {  //TODO should I return it here or in Service?
        Optional<Term> term = termRepository.findFirstByTerm(termValue);
        return term.map(Term::getDocumentIdsWithTf).orElse(Collections.emptyList());
    }

}
