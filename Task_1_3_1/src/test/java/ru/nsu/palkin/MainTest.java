package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void smallCharTest() {
        Search search = new Search("бра");
        ArrayList<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        expectedResult.add(8);
        assertArrayEquals(expectedResult.toArray(), search.solution("data.txt").toArray());
    }

    @Test
    public void largeCharTest() {
        Search search = new Search("\uD81F\uDECF");
        Integer[] expectedResult = {0, 2, 4, 6, 8};
        assertArrayEquals(expectedResult, search.solution("data1.txt").toArray());
    }

    @Test
    public void longStringTest() {
        Search search = new Search("бр");
        Integer[] expectedResult = {0, 3, 6, 9, 12, 15, 18};
        assertArrayEquals(expectedResult, search.solution("data2.txt").toArray());
    }
}
