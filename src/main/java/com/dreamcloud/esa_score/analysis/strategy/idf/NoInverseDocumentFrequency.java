package com.dreamcloud.esa_score.analysis.strategy.idf;

import com.dreamcloud.esa_score.analysis.strategy.InverseDocumentFrequencyStrategy;

public class NoInverseDocumentFrequency implements InverseDocumentFrequencyStrategy {
    public double idf(int totalDocs, int termDocs) {
        return 1;
    }
}
