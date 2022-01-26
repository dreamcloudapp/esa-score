package com.dreamcloud.esa_score.analysis.strategy;

import com.dreamcloud.esa_score.analysis.TermInfo;

public interface TermFrequencyStrategy {
    double tf(double tf, TermInfo termInfo);
}
