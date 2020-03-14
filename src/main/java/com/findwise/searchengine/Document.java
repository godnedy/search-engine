package com.findwise.searchengine;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Document {

    @NonNull
    public final String id;
    @NonNull
    public final String content;
}
