package com.dreamcloud.esa_score.score;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentNameResolver {
    private static final Map<Integer, String> titleMap = new ConcurrentHashMap<>();

    public static void loadFile(File inputFile) throws IOException {
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);
       reader.lines().forEach((String s) -> {
           ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
           int id = byteBuffer.getInt();
           StringBuilder title = new StringBuilder();
           while (byteBuffer.remaining() > 0) {
               title.append(byteBuffer.get());
           }
           titleMap.put(id, title.toString());
       });
    }

    public static String getTitle(int id) {
        return titleMap.getOrDefault(id, String.valueOf(id));
    }
}
