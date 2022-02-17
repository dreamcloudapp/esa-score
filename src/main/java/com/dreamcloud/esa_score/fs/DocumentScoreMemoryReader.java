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

    //todo: cleanup for segmentation
    public ByteBuffer readScores(long offset, int numScores) throws IOException {
        return ByteBuffer.wrap(scoreData, (int) offset, numScores * FileSystem.DOCUMENT_SCORE_BYTES);
    }
}
