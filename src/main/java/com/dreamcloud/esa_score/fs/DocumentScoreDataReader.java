package com.dreamcloud.esa_score.fs;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface DocumentScoreDataReader {
    ByteBuffer readScores(int offset, int numScores) throws IOException;
}
