package com.findwise.searchengine;

import com.findwise.SearchEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentController {

    private final SearchEngine searchEngine;

    @GetMapping(path = "/{term}")
    public ResponseEntity getDocumentList(@PathVariable("term") String term) {
        return ResponseEntity.ok(searchEngine.search(term));
    }

    @PostMapping
    public ResponseEntity createDocumentEntry(@RequestBody @Validated IndexDocumentRequest request) {
        searchEngine.indexDocument(request.getId(), request.getContent());
        return new ResponseEntity(CREATED);
    }

}