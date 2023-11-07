package ru.nsu.palkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class Search.
 */
public class Search {
    public String pattern;
    public HashMap<Character, Integer> offset;
    public int otherElemOffset;

    /**
     * Class constructor.
     *
     * @param pattern - search string
     */
    Search(String pattern) {
        this.pattern = pattern;
        this.offset = new HashMap<>();
        int len = pattern.length();
        this.otherElemOffset = len;
        int[] offset = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            int flag = 0;
            for (int j = i + 1; j < len - 1; j++) {
                if (pattern.charAt(i) == pattern.charAt(j)) {
                    offset[i] = offset[j];
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                offset[i] = len - 1 - i;
            }
        }
        offset[len - 1] = len;
        for (int i = 0; i < len - 1; i++) {
            if (pattern.charAt(i) == pattern.charAt(len - 1)) {
                offset[len - 1] = offset[i];
                break;
            }
        }
        for (int i = 0; i < len; i++) {
            this.offset.put(pattern.charAt(i), offset[i]);
        }
    }

    /**
     * Solution search method.
     *
     * @param fileName - file name
     * @return list of indexes
     */
    public ArrayList<Integer> solution(String fileName) {
        ArrayList<Integer> result = new ArrayList<>();
        int globalPos = 0;
        try {
            InputStream fis = getClass().getClassLoader().getResourceAsStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);

            int patternSize = this.pattern.length();
            int bufferSize = patternSize * 8;
            /*if (bufferSize < 8192) {
                bufferSize = 8192;
            }*/

            char[] buffer = null;
            char[] remains = null;
            int charCount = 0;
            while (true) {
                if (globalPos == 0) {
                    buffer = new char[bufferSize];
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
                    buffer = new char[bufferSize];
                    System.arraycopy(remains, 0, buffer, 0, remains.length);
                    System.arraycopy(newBuffer, 0, buffer, remains.length, newBufferSize);
                    System.out.println(buffer);
                }
                int localOffset = 0;
                while (localOffset + patternSize <= charCount) {
                    if (buffer[localOffset + patternSize - 1] == this.pattern.charAt(patternSize - 1)) {
                        int flag = 1;
                        for (int i = patternSize - 1; i >= 0; i--) {
                            if (this.pattern.charAt(i) != buffer[localOffset + i]) {
                                flag = 0;
                                break;
                            }
                        }
                        if (flag == 1) {
                            if (!result.contains(globalPos + localOffset)) {
                                result.add(globalPos + localOffset);
                            }
                        }
                        localOffset = localOffset + this.offset.get(this.pattern.charAt(patternSize - 1));
                    } else {
                        if (this.offset.containsKey(buffer[localOffset + patternSize - 1])) {
                            localOffset = localOffset + this.offset.get(buffer[localOffset + patternSize - 1]);
                        } else {
                            localOffset = localOffset + this.otherElemOffset;
                        }
                    }
                }
                if (charCount == bufferSize) {
                    remains = Arrays.copyOfRange(buffer, (bufferSize / 8) * 7, bufferSize);
                    globalPos = globalPos + (bufferSize / 8) * 7;
                } else {
                    globalPos = globalPos + charCount - 1;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
