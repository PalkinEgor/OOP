package ru.nsu.palkin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Search.
 */
public class Search {
    private String subString;
    private String fileName;
    private int bufferSize;
    private boolean resources;

    /**
     * Class constructor.
     *
     * @param subString - search string
     * @param fileName  - name of file
     */
    Search(String subString, String fileName, boolean resources) {
        this.subString = subString;
        this.fileName = fileName;
        this.bufferSize = Math.max(subString.length() * 8, 8192);
        this.resources = resources;
    }

    /**
     * Solution search method.
     *
     * @return list of indexes
     */
    public ArrayList<Long> solution() {
        Set<Long> resultSet = new HashSet<>();
        try {
            BufferedReader reader;
            if (resources) {
                InputStream fis = getClass().getClassLoader().getResourceAsStream(fileName);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                reader = new BufferedReader(isr);
            } else {
                reader = new BufferedReader(new FileReader(this.fileName));
            }
            char[] buffer = null;
            char[] remains = null;
            int globalPos = 0;
            int charCount = 0;
            while (true) {
                if (globalPos == 0) {
                    buffer = new char[this.bufferSize];
                    charCount = reader.read(buffer);
                    if (charCount == -1) {
                        break;
                    }
                } else {
                    int newBufferSize = (bufferSize / 8) * 7;
                    char[] newBuffer = new char[newBufferSize];
                    charCount = reader.read(newBuffer);
                    if (charCount == -1) {
                        break;
                    }
                    charCount = charCount + remains.length;
                    buffer = new char[this.bufferSize];
                    System.arraycopy(remains, 0, buffer, 0, remains.length);
                    System.arraycopy(newBuffer, 0, buffer, remains.length, newBufferSize);
                }
                String batch = new String(buffer);
                int pos = -1;
                while (true) {
                    pos = batch.indexOf(this.subString, pos + 1);
                    if (pos == -1) {
                        break;
                    }
                    resultSet.add((long) (pos + globalPos));
                }
                if (charCount == bufferSize) {
                    remains = Arrays.copyOfRange(buffer, (this.bufferSize / 8) * 7, bufferSize);
                    globalPos = globalPos + ((bufferSize / 8) * 7);
                } else {
                    globalPos = globalPos + charCount - 1;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error when reading to a file");
        }
        ArrayList<Long> resultArr = new ArrayList<>(resultSet);
        Collections.sort(resultArr);
        return resultArr;
    }
}
