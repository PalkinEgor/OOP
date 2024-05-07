package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.Food;
import ru.nsu.palkin.model.Point;

/**
 * Food class test.
 */
public class FoodTest {
    @Test
    public void foodGenerateFoodTest() {
        ArrayList<Point> badPoints = new ArrayList<>();
        badPoints.add(new Point(0, 1));
        badPoints.add(new Point(1, 0));
        badPoints.add(new Point(1, 1));
        Food food = new Food(2, 2);
        food.generateFood(badPoints);
        assertEquals(food.getFoodX(), 0);
        assertEquals(food.getFoodY(), 0);
    }
}
