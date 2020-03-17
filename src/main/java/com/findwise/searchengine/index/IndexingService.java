package com.findwise.searchengine.index;

import java.util.List;

import com.findwise.searchengine.document.DocumentHandler;
import com.findwise.searchengine.term.TermHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IndexingService {

    private final ContentTokenizer contentTokenizer;

    private final DocumentHandler documentHandler;

    private final TermHandler termHandler;

    public void indexDocument(String id, String content) {
        List<WeightedToken> weightedTokens = contentTokenizer.tokenize(content);
        String documentId = documentHandler.saveDocument(id, content);
        weightedTokens.forEach(token -> termHandler.updateTerm(token, documentId));
    }
}
