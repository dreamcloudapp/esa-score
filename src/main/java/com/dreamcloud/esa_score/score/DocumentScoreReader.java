package com.dreamcloud.esa_score.score;

import java.io.IOException;
import java.util.Vector;

public interface DocumentScoreReader {
    void getTfIdfScores(String term, Vector<TfIdfScore> outVector) throws IOException;
    void getTfIdfScores(String[] terms, Vector<TfIdfScore> outVector) throws IOException;
}
