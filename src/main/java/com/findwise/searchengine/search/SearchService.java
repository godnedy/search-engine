package com.findwise.searchengine.search;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.findwise.IndexEntry;
import com.findwise.searchengine.document.DocumentFinder;
import com.findwise.searchengine.term.DocumentIdWithTF;
import com.findwise.searchengine.term.TermFinder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
class SearchService {

    private final DocumentFinder documentFinder;

    private final TermFinder termFinder;

    public List<IndexEntry> search(String query) {
        List<DocumentIdWithTF> documentIdsWithTF = termFinder.getDocuments(query);
        int df = documentIdsWithTF.size();
        long totalNumberOfDocuments = documentFinder.getTotalNumberOfIndexedDocuments();
        return documentIdsWithTF.stream()
                .map(doc -> createIndexEntry(doc, df, totalNumberOfDocuments))
                .sorted(new SortByScore())
                .collect(Collectors.toList());

    }

    private IndexEntry createIndexEntry(DocumentIdWithTF documentIdWithTF, int df, long totalNumberOfDocuments) {
        IndexEntry indexEntry = new IndexEntryImpl();
        indexEntry.setScore(calculateTfIdf(documentIdWithTF.tf, df, totalNumberOfDocuments));
        indexEntry.setId(documentIdWithTF.documentId);
        return indexEntry;
    }

    private double calculateTfIdf(double tf, int df, long totalNumberOfDocuments) {
        double idf = Math.log((double) totalNumberOfDocuments / (df+1));
        return tf * idf;
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
            return Double.compare(a.getScore(), b.getScore());
        }
    }


}
