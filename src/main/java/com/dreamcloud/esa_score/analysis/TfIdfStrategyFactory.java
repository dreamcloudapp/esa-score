package com.dreamcloud.esa_score.analysis;

import com.dreamcloud.esa_score.analysis.strategy.TfIdfStrategy;

public class TfIdfStrategyFactory {
    public TfIdfStrategy getStrategy(TfIdfOptions options) {
        TfIdfStrategy calculator = new TfIdfCalculator(options.getMode());
        if (options.isBm25()) {
            calculator = new BM25Calculator(calculator, options.getBm25_k(), options.getBm25_b(), options.getBm25_delta());
        }
        return calculator;
    }
}
