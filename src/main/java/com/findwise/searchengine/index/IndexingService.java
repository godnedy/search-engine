package com.findwise.searchengine.index;

import java.util.List;

import com.findwise.searchengine.document.DocumentService;
import com.findwise.searchengine.term.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IndexingService {

    private final ContentTokenizer contentTokenizer;

    private final DocumentService documentService;

    private final TermService termService;

    public void indexDocument(String id, String content) {
        if(!documentService.exists(id)) {
            perfomIndexing(id, content);
        }
        //TODO add handle existing document
    }

    private void perfomIndexing(String id, String content) {
        List<WeightedToken> weightedTokens = contentTokenizer.tokenize(content);
        String documentId = documentService.saveDocument(id, content);
        weightedTokens.forEach(token -> termService.updateTerm(token, documentId));
    }
}
