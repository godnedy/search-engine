package com.findwise.searchengine.search;


import lombok.experimental.UtilityClass;

@UtilityClass
class ScoreCalculator {

    static double calculateTfIdf(double tf, int df, long totalNumberOfDocuments) {
        double idf = Math.log10((double) totalNumberOfDocuments / df);
        return tf * idf;
    }
}
