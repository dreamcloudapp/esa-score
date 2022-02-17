package com.dreamcloud.esa_score.fs;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface DocumentScoreDataReader {
    ByteBuffer readScores(long offset, int numScores) throws IOException;
}
