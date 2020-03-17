package com.findwise.searchengine.index;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeightedToken {
    @NonNull
    public final String term;
    @NonNull
    public final double tf;
}
