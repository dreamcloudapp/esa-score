package com.dreamcloud.esa_score.analysis.strategy;

public interface InverseDocumentFrequencyStrategy {
    double idf(int totalDocs, int termDocs);
}
