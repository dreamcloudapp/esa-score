package com.dreamcloud.esa_score.score;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentNameResolver {
    private final Map<Integer, String> titleMap = new ConcurrentHashMap<>();

    public DocumentNameResolver(File inputFile) throws IOException {
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

    public String getTitle(int id) {
        return titleMap.get(id);
    }
}
