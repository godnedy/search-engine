package com.findwise.searchengine.index;

import java.util.List;

import com.findwise.searchengine.Document;
import com.findwise.searchengine.document.DocumentHandler;
import com.findwise.searchengine.term.TermHandler;
import com.findwise.searchengine.term.WeightedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class IndexingService {

    private final ContentTokenizer contentTokenizer;

    private final DocumentHandler documentHandler;

    private final TermHandler termHandler;

    public void indexDocuments(List<Document> documents) {
        documents.forEach(this::indexDocument);
    }

    private void indexDocument(Document document) {
        List<WeightedToken> weightedTokens = contentTokenizer.tokenize(document.content);
        Long documentId = documentHandler.saveDocument(document);
        weightedTokens.forEach(token -> termHandler.updateTerm(token, documentId));
    }
}
