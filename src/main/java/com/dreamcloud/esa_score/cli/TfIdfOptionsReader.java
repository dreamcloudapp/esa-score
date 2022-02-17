package com.dreamcloud.esa_score.cli;

import com.dreamcloud.esa_score.analysis.TfIdfOptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class TfIdfOptionsReader {
    protected static String TFIDF_MODE = "tfidf-mode";
    protected static String BM25_B = "bm25-b";
    protected static String BM25_K = "bm25-k";
    protected static String BM25_DELTA = "bm25-delta";


    public void addOptions(Options options) {
        Option option = new Option(null, TFIDF_MODE, true, "");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, BM25_B, true, "");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, BM25_K, true, "");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, BM25_DELTA, true, "");
        option.setRequired(false);
        options.addOption(option);
    }

    public TfIdfOptions getOptions(CommandLine cli) {
        String tfIdfMode = cli.getOptionValue(TFIDF_MODE, "ltc");
        TfIdfOptions options = TfIdfOptions.fromString(tfIdfMode);

        if (cli.hasOption(BM25_B)) {
            options.setBm25_b(Float.parseFloat(cli.getOptionValue(BM25_B)));
        }

        if (cli.hasOption(BM25_K)) {
            options.setBm25_k(Float.parseFloat(cli.getOptionValue(BM25_K)));
        }

        if (cli.hasOption(BM25_DELTA)) {
            options.setBm25_delta(Float.parseFloat(cli.getOptionValue(BM25_DELTA)));
        }

        options.display();
        return options;
    }
}
