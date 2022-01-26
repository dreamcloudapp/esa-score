package com.dreamcloud.esa_score.analysis.strategy.norm;

import com.dreamcloud.esa_score.score.TfIdfScore;
import com.dreamcloud.esa_score.analysis.strategy.NormalizationStrategy;

public class NoNormalization implements NormalizationStrategy {
    public double norm(TfIdfScore[] scores) {
        return 1;
    }
}
