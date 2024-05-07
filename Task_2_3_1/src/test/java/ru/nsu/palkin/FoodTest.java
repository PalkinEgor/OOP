package ru.nsu.palkin;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.Point;
import ru.nsu.palkin.model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Food class test.
 */
public class FoodTest {
    @Test
    public void foodGenerateFoodTest() {
        Food food = new Food(2, 2);
        ArrayList<Point> badPoints = new ArrayList<>();
        badPoints.add(new Point(0, 1));
        badPoints.add(new Point(1, 0));
        badPoints.add(new Point(1, 1));
        food.generateFood(badPoints);
        assertEquals(food.getFoodX(), 0);
        assertEquals(food.getFoodY(), 0);
    }
}
