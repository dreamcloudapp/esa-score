package com.dreamcloud.esa_score.fs;

import java.io.*;

public class TermIndexWriter {
    private final double averageDocumentLength;
    DataOutputStream outputStream;
    long offset = 0;
    int documentCount;

    public TermIndexWriter(int documentCount, double averageDocumentLength) {
        this.documentCount = documentCount;
        this.averageDocumentLength = averageDocumentLength;
    }

    public void open(File termIndex) throws IOException {
        OutputStream sourceOutputStream = new FileOutputStream(termIndex);
        sourceOutputStream = new BufferedOutputStream(sourceOutputStream);
        outputStream = new DataOutputStream(sourceOutputStream);
        offset = 0;
        outputStream.writeInt(documentCount);
        outputStream.writeFloat((float) averageDocumentLength);
    }

    public void writeTerm(String term, int numScores) throws IOException {
        int termLength = term.getBytes().length;
        long termOffset = offset;
        offset += (long) numScores * FileSystem.DOCUMENT_SCORE_BYTES;

        outputStream.writeInt(termLength);
        outputStream.write(term.getBytes());
        outputStream.writeInt(numScores);
        outputStream.writeLong(termOffset);
        outputStream.writeInt(numScores);
    }

    public void close() throws IOException {
        outputStream.close();
    }
}
