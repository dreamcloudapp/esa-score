package com.dreamcloud.esa_score.analysis.strategy.tf;

import com.dreamcloud.esa_score.analysis.TermInfo;
import com.dreamcloud.esa_score.analysis.strategy.TermFrequencyStrategy;

public class LogarithmicAverageTermFrequency implements TermFrequencyStrategy {
    public double tf(double tf, TermInfo termInfo) {
        return (1 + Math.log(tf)) / (1 + Math.log(termInfo.avgTf));
    }
}
