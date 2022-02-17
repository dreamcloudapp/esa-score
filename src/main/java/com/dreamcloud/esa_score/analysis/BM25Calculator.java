package com.dreamcloud.esa_score.analysis;

import com.dreamcloud.esa_score.analysis.strategy.TfIdfStrategy;
import com.dreamcloud.esa_score.score.TfIdfScore;

public class BM25Calculator implements TfIdfStrategy {
    TfIdfStrategy tfIdfStrategy;
    protected double k;
    protected double b;
    protected double delta;

    public BM25Calculator(TfIdfStrategy tfIdfStrategy, double k, double b, double delta) {
        this.tfIdfStrategy = tfIdfStrategy;
        this.k = k;
        this.b = b;
        this.delta = delta;
    }

    public double tf(double tf, TermInfo termInfo) {
        double tfScore = this.tfIdfStrategy.tf(tf, termInfo);
        return Math.log(1 + ((tfScore * (k + 1)) / (Math.log(tfScore) + 1 + (k * (1 - b + (b * (termInfo.dl / termInfo.avgDl)))))) + delta);
    }

    public double idf(int totalDocs, int termDocs) {
        return this.tfIdfStrategy.idf(totalDocs, termDocs);
    }

    public double norm(TfIdfScore[] scores) {
        return this.tfIdfStrategy.norm(scores);
    }
}
