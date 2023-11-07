package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

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
        assertArrayEquals(expectedResult.toArray(), search.Solution("data.txt").toArray());
    }

    @Test
    public void largeCharTest() {
        Search search = new Search("\uD81F\uDECF");
        ArrayList<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(0);
        expectedResult.add(2);
        expectedResult.add(4);
        expectedResult.add(6);
        expectedResult.add(8);
        assertArrayEquals(expectedResult.toArray(), search.Solution("data1.txt").toArray());
    }

    @Test
    public void longStringTest(){
        Search search = new Search("бра");
        ArrayList<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(0);
        expectedResult.add(3);
        expectedResult.add(6);
        expectedResult.add(9);
        expectedResult.add(12);
        expectedResult.add(15);
        expectedResult.add(18);
        expectedResult.add(21);
        expectedResult.add(24);
        assertArrayEquals(expectedResult.toArray(), search.Solution("data2.txt").toArray());
    }
}
