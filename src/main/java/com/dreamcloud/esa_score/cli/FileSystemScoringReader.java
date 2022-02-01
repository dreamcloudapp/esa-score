package com.dreamcloud.esa_score.cli;

import com.dreamcloud.esa_score.analysis.CollectionInfo;
import com.dreamcloud.esa_score.fs.*;
import com.dreamcloud.esa_score.score.DocumentNameResolver;
import com.dreamcloud.esa_score.score.DocumentScoreCachingReader;
import com.dreamcloud.esa_score.score.DocumentScoreReader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.File;
import java.io.IOException;

public class FileSystemScoringReader {
    private static String TERM_INDEX_FILE = "term-index";
    private static String DOCUMENT_SCORE_FILE = "document-scores";
    private static String SCORE_MECHANISM = "score-mechanism";
    private static String SCORE_CACHE = "score-cache";
    private static String ID_TITLES_FILE = "id-titles";

    private File termIndexFile;
    private File documentScoreFile;
    private String scoreMechanism;
    private String scoreCache;
    private File idTitlesFile;


    private static TermIndex termIndex;

    public void addOptions(Options options) {
        Option option = new Option(null, TERM_INDEX_FILE, true, "");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, DOCUMENT_SCORE_FILE, true, "");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, SCORE_MECHANISM, true, "");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, SCORE_CACHE, true, "");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, ID_TITLES_FILE, true, "");
        option.setRequired(false);
        options.addOption(option);
    }

    public void parseOptions(CommandLine cli) {
        this.termIndexFile = new File(cli.getOptionValue(TERM_INDEX_FILE, "term-index.dc"));
        this.documentScoreFile = new File(cli.getOptionValue(DOCUMENT_SCORE_FILE, "document-scores.dc"));
        this.scoreMechanism = cli.getOptionValue(SCORE_MECHANISM, "disk");
        this.scoreCache = cli.getOptionValue(SCORE_CACHE, "");
        if (cli.hasOption(ID_TITLES_FILE)) {
            this.idTitlesFile = new File(cli.getOptionValue(ID_TITLES_FILE));
        }
    }

    private TermIndex getTermIndex() throws IOException {
        if (termIndex == null) {
            TermIndexReader reader = new TermIndexReader();
            reader.open(termIndexFile);
            termIndex = reader.readIndex();
        }
        return termIndex;
    }

    public DocumentNameResolver getDocumentNameResolver() throws IOException {
        return new DocumentNameResolver(this.idTitlesFile);
    }

    public CollectionInfo getCollectionInfo() throws IOException {
        TermIndex termIndex = getTermIndex();
        return new CollectionInfo(termIndex.getDocumentCount(), termIndex.getAverageDocumentLength(), termIndex.getDocumentFrequencies());
    }

    public CollectionWriter getCollectionWriter() {
        return new DiskCollectionWriter(termIndexFile, documentScoreFile);
    }

    public DocumentScoreReader getScoreReader() throws IOException {
        DocumentScoreDataReader scoreDataReader;
        switch(scoreMechanism) {
            case "disk":
                scoreDataReader = new DocumentScoreFileReader(documentScoreFile);
                break;
            case "memory":
                scoreDataReader = new DocumentScoreMemoryReader(documentScoreFile);
                break;
            default:
                throw new IllegalArgumentException("Score mechanism '" + scoreMechanism + "' is not supported.'");
        }

        TermIndex termIndex = getTermIndex();
        DocumentScoreReader scoreReader = new ScoreReader(termIndex, scoreDataReader);


        if (!"".equals(scoreCache)) {
            switch(scoreCache) {
                case "top-hits":
                    scoreReader = new DocumentScoreCachingReader(scoreReader);
                    break;
                default:
                    throw new IllegalArgumentException("Score cache mechanism '" + scoreCache + "' is not supported.'");
            }
        }
        return scoreReader;
    }
}
