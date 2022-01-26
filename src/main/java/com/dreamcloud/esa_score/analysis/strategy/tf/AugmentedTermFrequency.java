package com.dreamcloud.esa_score.analysis.strategy.tf;

import com.dreamcloud.esa_score.analysis.TermInfo;
import com.dreamcloud.esa_score.analysis.strategy.TermFrequencyStrategy;

public class AugmentedTermFrequency implements TermFrequencyStrategy {
    public double tf(double tf, TermInfo termInfo) {
        return 0.5 + ((0.5 * tf) / termInfo.maxTf);
    }
}
