package ru.nsu.palkin;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Point class test.
 */
public class PointTest {
    @Test
    public void pointGetXTest() {
        Point point = new Point(1, 1);
        assertEquals(point.getX(), 1);
    }

    @Test
    public void pointGetYTest() {
        Point point = new Point(1, 1);
        assertEquals(point.getY(), 1);
    }
}
