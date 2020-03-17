package com.findwise.searchengine.search;

public interface IndexEntry {

    String getId();

    void setId(String id);

    double getScore();

    void setScore(double score);
}
