package com.findwise.searchengine.term;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DocumentIdWithTF {

    @NonNull
    public final String documentId;
    @NonNull
    public final double tf;
}
