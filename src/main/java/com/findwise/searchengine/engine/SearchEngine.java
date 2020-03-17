package com.findwise.searchengine.engine;

import java.util.List;

import com.findwise.searchengine.search.IndexEntry;

public interface SearchEngine {

    void indexDocument(String id, String content);

    List<IndexEntry> search(String term);
}
