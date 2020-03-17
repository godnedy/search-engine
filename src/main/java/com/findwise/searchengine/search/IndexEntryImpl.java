package com.findwise.searchengine.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
class IndexEntryImpl implements IndexEntry {

    private String id;

    private double score;

}
