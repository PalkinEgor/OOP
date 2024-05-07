package ru.nsu.palkin;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.Snake;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Snake class test.
 */
public class SnakeTest {
    @Test
    public void snakeGetSnakeTest() {
        Snake snake = new Snake(1, 2, 10, 10);
        assertEquals(snake.getSnake().get(0).getX(), 1);
        assertEquals(snake.getSnake().get(0).getY(), 2);
    }
}
