package com.findwise.searchengine.search

import spock.lang.Specification
import spock.lang.Unroll

class ScoreCalculatorSpec extends Specification {

    @Unroll
    def "CalculateTfIdf should return proper values"() {
        when:
            double result = ScoreCalculator.calculateTfIdf(tf, df, numberOfDocuments)
        then:
            almostEqual(result, tfidf)
        where:
            tf      || df || numberOfDocuments || tfidf
            1/5     || 2   ||   2              ||   0
            0/5     || 1   ||   2              ||   0
            3/7     || 1   ||   2              ||   0.129d
    }


    def almostEqual(double a, double b){
        return Math.abs(a-b) < 0.01
    }
}
