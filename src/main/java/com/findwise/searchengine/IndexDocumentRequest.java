package com.findwise.searchengine;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class IndexDocumentRequest {
    @NotEmpty
    private String id;
    @NotEmpty
    private String content;
}