package com.findwise;

import java.util.List;

public interface SearchEngine {

    void indexDocument(String id, String content);

    List<IndexEntry> search(String term);
}
