package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.Point;

/**
 * Point class test.
 */
public class PointTest {
    @Test
    public void pointGetTestX() {
        Point point = new Point(1, 1);
        assertEquals(point.getX(), 1);
    }

    @Test
    public void pointGetTestY() {
        Point point = new Point(1, 1);
        assertEquals(point.getY(), 1);
    }
}
