package com.findwise.searchengine.index;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import opennlp.tools.tokenize.Tokenizer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentTokenizer {

    private final Tokenizer tokenizer;


    public List<WeightedToken> tokenize(String content) {
        String contentWithLettersAndSpaceOnly = removeUnwantedCharacters(content);
        String[] tokens = tokenizer.tokenize(contentWithLettersAndSpaceOnly);
        return calculateTermFrequency(tokens);
    }


    private String removeUnwantedCharacters(String content) {
        return content.replaceAll("[^a-zA-Z ]", "");
    }

    private List<WeightedToken> calculateTermFrequency(String[] tokens) {
        int numberOfTerms = tokens.length;
        if(numberOfTerms > 0) {
            HashMap<String, Integer> tokensWithTheirCount = new HashMap<>();
            for (String token : tokens) {
                if (tokensWithTheirCount.containsKey(token)) {
                    int count = tokensWithTheirCount.get(token);
                    tokensWithTheirCount.replace(token, ++count);
                } else {
                    tokensWithTheirCount.put(token, 1);
                }
            }
            return tokensWithTheirCount.entrySet().stream()
                    .map(entry -> new WeightedToken(entry.getKey(), (double) entry.getValue() / numberOfTerms))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
