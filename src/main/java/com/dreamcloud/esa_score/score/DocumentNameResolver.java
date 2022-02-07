package com.dreamcloud.esa_score.score;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentNameResolver {
    private static final Map<Integer, String> titleMap = new ConcurrentHashMap<>();
    private static final Map<String, Integer> idMap = new ConcurrentHashMap<>();

    public static void loadFile(File inputFile) throws IOException {
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        while (line != null) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(line.getBytes(StandardCharsets.UTF_8));
            int id = byteBuffer.getInt();
            StringBuilder title = new StringBuilder();
            while (byteBuffer.remaining() > 0) {
                title.append(byteBuffer.get());
            }
            titleMap.put(id, title.toString());
            idMap.put(title.toString(), id);
            line = reader.readLine();;
        }
    }

    public static String getTitle(int id) {
        return titleMap.getOrDefault(id, String.valueOf(id));
    }

    public static int getId(String title) {
        return idMap.getOrDefault(title, -1);
    }
}
