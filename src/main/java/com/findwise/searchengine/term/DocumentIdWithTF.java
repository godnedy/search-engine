package com.findwise.searchengine.term;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DocumentIdWithTF {

    @NonNull
    public final Long documentId;
    @NonNull
    public final double tf;
}
