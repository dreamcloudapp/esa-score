package com.dreamcloud.esa_score.fs;

import java.io.*;
import java.nio.ByteBuffer;

public class DocumentScoreMemoryReader implements DocumentScoreDataReader {
    byte[] scoreData;

    public DocumentScoreMemoryReader(File scoreFile) throws IOException {
        InputStream inputStream = new FileInputStream(scoreFile);
        inputStream = new BufferedInputStream(inputStream);
        scoreData = inputStream.readAllBytes();
    }

    public ByteBuffer readScores(int offset, int numScores) throws IOException {
        return ByteBuffer.wrap(scoreData, offset, numScores * FileSystem.DOCUMENT_SCORE_BYTES);
    }
}
