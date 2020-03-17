package com.findwise.searchengine.engine;

import com.findwise.searchengine.index.IndexingService;
import com.findwise.searchengine.search.SearchService;
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
