package com.dreamcloud.esa_score.score;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

public class LoggingScoreReader implements DocumentScoreReader {
    private final DocumentScoreReader reader;
    AtomicLong timeTaken = new AtomicLong(0);
    AtomicLong termsRead = new AtomicLong(0);

    public LoggingScoreReader(DocumentScoreReader reader) {
        this.reader = reader;
    }

    public void getTfIdfScores(String term, Vector<TfIdfScore> outVector) throws IOException {
        long startTime = System.nanoTime();
        reader.getTfIdfScores(term, outVector);
        timeTaken.addAndGet(System.nanoTime() - startTime);
        termsRead.incrementAndGet();
    }

    public void getTfIdfScores(String[] terms, Vector<TfIdfScore> outVector) throws IOException {
        long startTime = System.nanoTime();
        reader.getTfIdfScores(terms, outVector);
        timeTaken.addAndGet(System.nanoTime() - startTime);
        termsRead.addAndGet(terms.length);
    }

    public double getTermsReadPerSecond() {
        return termsRead.get() / (timeTaken.get() / 1000000000.0d);
    }

    public long getTermsRead() {
        return termsRead.get();
    }
}
