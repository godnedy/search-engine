package com.findwise.searchengine.search;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.findwise.searchengine.document.DocumentService;
import com.findwise.searchengine.term.DocumentIdWithTF;
import com.findwise.searchengine.term.TermService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final DocumentService documentService;

    private final TermService termService;

    public List<IndexEntry> search(String query) {
        List<DocumentIdWithTF> documentIdsWithTF = termService.getDocuments(query);
        int df = documentIdsWithTF.size();
        long totalNumberOfDocuments = documentService.getTotalNumberOfIndexedDocuments();
        return documentIdsWithTF.stream()
                .map(doc -> createIndexEntry(doc, df, totalNumberOfDocuments))
                .sorted(new SortByScore())
                .collect(Collectors.toList());
    }

    private IndexEntry createIndexEntry(DocumentIdWithTF documentIdWithTF, int df, long totalNumberOfDocuments) {
        IndexEntry indexEntry = new IndexEntryImpl();
        indexEntry.setScore(ScoreCalculator.calculateTfIdf(documentIdWithTF.tf, df, totalNumberOfDocuments));
        indexEntry.setId(documentIdWithTF.documentId);
        return indexEntry;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    private class IndexEntryImpl implements IndexEntry {

        private String id;
        private double score;

    }

    private class SortByScore implements Comparator<IndexEntry> {
        @Override
        public int compare(IndexEntry a, IndexEntry b) {
            return Double.compare(b.getScore(), a.getScore());
        }
    }


}
