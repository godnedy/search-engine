package com.findwise.searchengine.index;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DocumentIdWithTF {

    @NonNull
    public final String documentId;
    @NonNull
    public final double tf;
}
