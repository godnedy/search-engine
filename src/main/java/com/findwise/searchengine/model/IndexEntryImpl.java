package com.findwise.searchengine.model;

import com.findwise.IndexEntry;
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
