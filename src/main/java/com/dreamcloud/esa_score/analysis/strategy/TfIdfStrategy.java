package com.dreamcloud.esa_score.analysis.strategy;

import com.dreamcloud.esa_score.analysis.TermInfo;
import com.dreamcloud.esa_score.score.TfIdfScore;

public interface TfIdfStrategy {
    double tf(double tf, TermInfo termInfo);
    double idf(int totalDocs, int termDocs);
    double norm(TfIdfScore[] scores);
}
