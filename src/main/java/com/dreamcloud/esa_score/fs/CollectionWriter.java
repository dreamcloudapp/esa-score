package com.dreamcloud.esa_score.fs;

import com.dreamcloud.esa_score.analysis.CollectionInfo;
import com.dreamcloud.esa_score.score.TfIdfScore;

import java.io.IOException;

public interface CollectionWriter {
    void writeCollectionInfo(CollectionInfo collectionInfo);
    void writeDocumentScores(int documentId, TfIdfScore[] scores);
    void close() throws IOException;
}
