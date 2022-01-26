package com.dreamcloud.esa_score.analysis.strategy.norm;

import com.dreamcloud.esa_score.score.TfIdfScore;
import com.dreamcloud.esa_score.analysis.strategy.NormalizationStrategy;

public class ByteLengthInverseDocumentFrequency implements NormalizationStrategy {
    @Override
    public double norm(TfIdfScore[] scores) {
        //todo: implement this sucker
        double length = 0;
        for (TfIdfScore score: scores) {
            length += Math.max(1, score.getScore()) * score.getTerm().length();
        }
        return 1 / length;
    }
}
