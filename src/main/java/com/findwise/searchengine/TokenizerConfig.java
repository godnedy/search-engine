package com.findwise.searchengine;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenizerConfig {

    @Bean
    public Tokenizer myBean() {
        return SimpleTokenizer.INSTANCE;
    }
}
