package com.dreamcloud.esa_score.score;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentNameResolver {
    private static final Map<Integer, String> titleMap = new ConcurrentHashMap<>();
    private static final Map<String, Integer> idMap = new ConcurrentHashMap<>();

    public static void loadFile(File inputFile) throws IOException {
        DataInputStream inputStream = new DataInputStream(new FileInputStream(inputFile));
        try {
            while (true) {
                int articleId = inputStream.readInt();
                int titleLength = inputStream.readInt();
                String title = new String(inputStream.readNBytes(titleLength));
                titleMap.put(articleId, title);
                idMap.put(title, articleId);
            }
        } catch (EOFException e) {
            //expected
        }
    }

    public static String getTitle(int id) {
        return titleMap.getOrDefault(id, String.valueOf(id));
    }

    public static int getId(String title) {
        return idMap.getOrDefault(title, -1);
    }
}
