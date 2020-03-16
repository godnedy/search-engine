package com.findwise.searchengine.index

import opennlp.tools.tokenize.SimpleTokenizer
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ContentTokenizerSpec extends Specification {

    @Subject
    ContentTokenizer tokenizer = new ContentTokenizer(SimpleTokenizer.INSTANCE)


    @Unroll
    def "Tokenize returns proper set of tokens for #string String"() {
        when:
            def result = tokenizer.tokenize(string)
        then:
            result.size() == expectedSize

        where:
            string          ||  expectedSize
            "hello there"   ||  2
            ""              ||  0
            ".hello there"  ||  2
            ".hello?"       ||  1
            "hello ?"       ||  1
            "hello "        ||  1
            "hello ? "      ||  1
            "."             ||  0
            ".?"            ||  0
    }


   @Unroll
   def "Tokenize returns tf per content no #no"() {
       when:
           def result = tokenizer.tokenize(content)

       then:
            result.stream().allMatch{token -> expectedTf.get(token.term) == token.tf}

       where:
           no || content || expectedTf
           1  || "the brown fox jumped over the brown dog" || EXPECTED_TF_1
           2  || "the lazy brown dog sat in the corner"   || EXPECTED_TF_2
           3  || "the"   || EXPECTED_TF_3
   }

    private static final Map<String, Double> EXPECTED_TF_1 = Map.of(
            "the", 0.25d,
            "brown", 0.25d,
            "fox", 0.125d,
            "jumped", 0.125d,
            "over", 0.125d,
            "dog", 0.125d)

    private static final Map<String, Double> EXPECTED_TF_2 = Map.of(
            "the", 0.25d,
            "lazy", 0.125d,
            "brown", 0.125d,
            "dog", 0.125d,
            "sat", 0.125d,
            "in", 0.125d,
            "corner", 0.125d)

    private static final Map<String, Double> EXPECTED_TF_3 = Map.of(
            "the", 1d)

}
