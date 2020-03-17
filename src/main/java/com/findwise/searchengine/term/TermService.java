package com.findwise.searchengine.term;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.findwise.searchengine.index.WeightedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;

    public List<DocumentIdWithTF> getDocuments(String termValue) {
        Optional<TermDocument> term = termRepository.findFirstByTermValue(termValue);
        return term.map(TermDocument::getDocumentIdsWithTf).orElse(Collections.emptyList());
    }

    public void updateTerm(WeightedToken token, String documentId) {
        Optional<TermDocument> term = termRepository.findFirstByTermValue(token.term);
        if (term.isPresent()) {
            TermDocument existingTermDocument = term.get();
            existingTermDocument.addDocumentIdWithTf(new DocumentIdWithTF(documentId, token.tf));
            termRepository.save(existingTermDocument);
        } else {
            createTerm(token, documentId);
        }
    }

    private void createTerm(WeightedToken token, String documentId) {
        LinkedList<DocumentIdWithTF> list = new LinkedList<>();
        list.add(new DocumentIdWithTF(documentId, token.tf));
        termRepository.save(new TermDocument(token.term, list));
    }
}
