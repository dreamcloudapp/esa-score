package com.dreamcloud.esa_score.analysis.strategy.tf;

import com.dreamcloud.esa_score.analysis.TermInfo;
import com.dreamcloud.esa_score.analysis.strategy.TermFrequencyStrategy;

/**
 * Normal term frequency (doesn't adjust in any way).
 */
public class NaturalTermFrequency implements TermFrequencyStrategy {
    public double tf(double tf, TermInfo termInfo) {
        return tf;
    }
}
