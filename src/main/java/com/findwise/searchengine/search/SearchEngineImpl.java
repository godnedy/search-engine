package com.findwise.searchengine.search;

import com.findwise.SearchEngine;
import com.findwise.searchengine.index.IndexingService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class SearchEngineImpl implements SearchEngine {

    @Delegate
    private final IndexingService indexingService;

    @Delegate
    private final SearchService searchService;
}
