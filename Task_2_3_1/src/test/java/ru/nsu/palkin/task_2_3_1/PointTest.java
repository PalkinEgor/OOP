package ru.nsu.palkin.task_2_3_1;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.task_2_3_1.Model.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
