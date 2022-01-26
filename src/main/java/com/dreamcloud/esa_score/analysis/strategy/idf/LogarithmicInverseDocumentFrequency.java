package com.dreamcloud.esa_score.analysis.strategy.idf;

import com.dreamcloud.esa_score.analysis.strategy.InverseDocumentFrequencyStrategy;

public class LogarithmicInverseDocumentFrequency implements InverseDocumentFrequencyStrategy {
    @Override
    public double idf(int totalDocs, int termDocs) {
        return Math.log((double) totalDocs / (double) termDocs);
    }
}
