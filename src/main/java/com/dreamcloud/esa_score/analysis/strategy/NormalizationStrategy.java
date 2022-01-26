package com.dreamcloud.esa_score.analysis.strategy;

import com.dreamcloud.esa_score.score.TfIdfScore;

public interface NormalizationStrategy {
    double norm(TfIdfScore[] scores);
}
