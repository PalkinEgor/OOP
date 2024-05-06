package ru.nsu.palkin.task_2_3_1;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.task_2_3_1.Model.Food;
import ru.nsu.palkin.task_2_3_1.Model.Point;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
