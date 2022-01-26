package com.dreamcloud.esa_score.analysis.strategy.norm;

import com.dreamcloud.esa_score.score.TfIdfScore;
import com.dreamcloud.esa_score.analysis.strategy.NormalizationStrategy;

public class CosineNormalization implements NormalizationStrategy {
    public double norm(TfIdfScore[] scores) {
        double scoreSumOfSquares = 0.0;
        for (TfIdfScore score: scores) {
            scoreSumOfSquares += Math.pow(score.getScore(), 2);
        }
        return 1 / Math.sqrt(scoreSumOfSquares);
    }
}
