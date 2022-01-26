package com.dreamcloud.esa_score.fs;

import com.dreamcloud.esa_score.score.TermScore;

import java.io.*;

public class TermScoreWriter {
    DataOutputStream outputStream;

    public TermScoreWriter() {

    }

    public void open(File termIndex) throws IOException {
        outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(termIndex)));
    }

    public void writeTermScores(TermScore[] termScores) throws IOException {
        for (TermScore termScore: termScores) {
            outputStream.writeInt(termScore.document);
            outputStream.writeFloat(termScore.score);
        }
    }

    public void writeTermScores(byte[] termScores) throws IOException {
        outputStream.write(termScores);
    }

    public void close() throws IOException {
        this.outputStream.close();
    }
}
