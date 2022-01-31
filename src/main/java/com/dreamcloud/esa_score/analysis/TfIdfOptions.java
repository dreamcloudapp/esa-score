package com.dreamcloud.esa_score.analysis;

public class TfIdfOptions {
    protected String mode;
    protected boolean isBm25 = false;
    protected float bm25_b;
    protected float bm25_k;
    protected float bm25_delta;

    public TfIdfOptions(String charMode) {
        this.setMode(charMode);
    }

    public boolean isBm25() {
        return isBm25;
    }

    public static TfIdfOptions fromString(String mode) {
        switch(mode) {
            //Handle special options
            case "bm25":
                return createBm25();
            case "bm25+":
                return createBm25Plus();
            case "bm11":
                return createBm11();
            case "bm15":
                return createBm15();
            default:
                //Handle standard char codes
                return new TfIdfOptions(mode);
        }
    }

    public static TfIdfOptions createTfIdf(char tfOption, char idfOption, char normalizationOption) {
        return new TfIdfOptions(new StringBuilder().append(tfOption).append(idfOption).append(normalizationOption).toString());
    }

    public static TfIdfOptions createBm25(float b, float k, float delta) {
        TfIdfOptions options = new TfIdfOptions("b:nbn");
        options.setBm25_b(b);
        options.setBm25_b(k);
        options.setBm25_b(delta);
        return options;
    }

    public static TfIdfOptions createBm25() {
        return createBm25(0.5f, 1.2f, 0.0f);
    }

    public static TfIdfOptions createBm11() {
        return createBm25(1.0f, 1.2f, 0.0f);
    }

    public static TfIdfOptions createBm15() {
        return createBm25(0.0f, 1.2f, 0.0f);
    }

    public static TfIdfOptions createBm25Plus() {
        return createBm25(0.5f, 1.2f, 1.0f);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        if (mode.startsWith("b:")) {
            mode = mode.substring(2);
            isBm25 = true;
        }
        this.mode = mode;
    }

    public float getBm25_b() {
        return bm25_b;
    }

    public void setBm25_b(float bm25_b) {
        this.bm25_b = bm25_b;
    }

    public float getBm25_k() {
        return bm25_k;
    }

    public void setBm25_k(float bm25_k) {
        this.bm25_k = bm25_k;
    }

    public float getBm25_delta() {
        return bm25_delta;
    }

    public void setBm25_delta(float bm25_delta) {
        this.bm25_delta = bm25_delta;
    }
}
