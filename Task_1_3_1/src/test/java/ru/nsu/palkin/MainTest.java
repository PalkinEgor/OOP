package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void russiaTest() {
        Search search = new Search("бра", "data.txt", true);
        Long[] expectedResult = {1L, 8L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void chineTest() {
        Search search = new Search("裁", "data1.txt", true);
        Long[] expectedResult = {0L, 1L, 2L, 3L, 4L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void japanTest() {
        Search search = new Search("ワールド", "data5.txt", true);
        Long[] expectedResult = {0L, 4L, 8L, 12L, 16L, 20L, 24L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void mixedTest() {
        Search search = new Search("裁", "data2.txt", true);
        Long[] expectedResult = {3L, 4L, 8L, 9L, 10L, 15L, 16L, 17L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void wholeStringTest() {
        Search search = new Search("qwertyuiopasdfghjklzxcvbnm", "data3.txt", true);
        Long[] expectedResult = {0L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void emptyTest() {
        Search search = new Search("qwerty", "data4.txt", true);
        Long[] expectedResult = {};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void numberTest() {
        Search search = new Search("1", "data6.txt", true);
        Long[] expectedResult = {0L, 9L, 11L, 12L, 13L, 15L, 17L, 19L, 21L, 23L, 25L, 27L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void signTest() {
        Search search = new Search("+", "data7.txt", true);
        Long[] expectedResult = {4L, 12L, 26L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void mediumTest() {
        Search search = new Search("+", "data9.txt", true);
        ArrayList<Long> expectedResult = new ArrayList<>();
        long fileLen = 10000;
        for (int i = 0; i < fileLen; i += 100) {
            expectedResult.add((long) i);
        }
        assertArrayEquals(expectedResult.toArray(), search.solution().toArray());
    }

    @Test
    public void aaaTest() {
        Search search = new Search("aaa", "data10.txt", true);
        Long[] expectedResult = {0L, 1L, 2L, 3L, 4L, 5L, 6L};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void largeTest() {
        ArrayList<Long> expectedResult = new ArrayList<>();
        long fileLen = 5368709120L;
        for (long i = 0; i < fileLen; i += 100) {
            expectedResult.add(i);
        }
        generateFile("data8.txt");
        Search search = new Search("+", "data8.txt", false);
        ArrayList<Long> realResult = search.solution();
        File file = new File("data8.txt");
        file.delete();
        assertArrayEquals(expectedResult.toArray(), realResult.toArray());
    }

    private void generateFile(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            Random rand = new Random();
            long fileLen = 5368709120L;
            for (long i = 0; i < fileLen; i++) {
                if (i % 100 == 0) {
                    writer.write('+');
                } else {
                    int currentElem = rand.nextInt(26) + 97;
                    char currentChar = (char) currentElem;
                    writer.write(currentChar);
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error when writing to a file");
        }
    }
}