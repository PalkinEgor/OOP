package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void russiaTest() {
        Search search = new Search("бра", "data.txt");
        Integer[] expectedResult = {1, 8};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void chineTest() {
        Search search = new Search("裁", "data1.txt");
        Integer[] expectedResult = {0, 1, 2, 3, 4};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void japanTest() {
        Search search = new Search("ワールド", "data5.txt");
        Integer[] expectedResult = {0, 4, 8, 12, 16, 20, 24};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void mixedTest() {
        Search search = new Search("裁", "data2.txt");
        Integer[] expectedResult = {3, 4, 8, 9, 10, 15, 16, 17};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void wholeStringTest() {
        Search search = new Search("qwertyuiopasdfghjklzxcvbnm", "data3.txt");
        Integer[] expectedResult = {0};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void emptyTest() {
        Search search = new Search("qwerty", "data4.txt");
        Integer[] expectedResult = {};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void numberTest() {
        Search search = new Search("1", "data6.txt");
        Integer[] expectedResult = {0, 9, 11, 12, 13, 15, 17, 19, 21, 23, 25, 27};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void signTest() {
        Search search = new Search("+", "data7.txt");
        Integer[] expectedResult = {4, 12, 26};
        assertArrayEquals(expectedResult, search.solution().toArray());
    }

    @Test
    public void mediumTest(){
        Search search = new Search("+", "data9.txt");
        ArrayList<Integer> expectedResult = new ArrayList<>();
        long fileLen = 10000;
        for (int i = 0; i < fileLen; i += 100) {
            expectedResult.add(i);
        }
        assertArrayEquals(expectedResult.toArray(), search.solution().toArray());
    }

    /*@Test
    public void largeTest() {
        Search search = new Search("+", "data8.txt");
        ArrayList<Integer> expectedResult = new ArrayList<>();
        long fileLen = 50000000;
        for (int i = 0; i < fileLen; i += 100) {
            expectedResult.add(i);
        }
        assertArrayEquals(expectedResult.toArray(), search.solution().toArray());
    }*/
}
