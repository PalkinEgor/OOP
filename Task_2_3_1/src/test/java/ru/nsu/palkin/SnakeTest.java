package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.Direction;
import ru.nsu.palkin.model.Snake;

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

    @Test
    public void snakeMoveSnakeUpTest() {
        Snake snake = new Snake(1, 1, 10, 10);
        snake.moveSnake(Direction.UP);
        assertEquals(snake.getSnake().get(0).getX(), 1);
        assertEquals(snake.getSnake().get(0).getY(), 0);
    }

    @Test
    public void snakeMoveSnakeDownTest() {
        Snake snake = new Snake(1, 1, 10, 10);
        snake.moveSnake(Direction.DOWN);
        assertEquals(snake.getSnake().get(0).getX(), 1);
        assertEquals(snake.getSnake().get(0).getY(), 2);
    }

    @Test
    public void snakeMoveSnakeLeftTest() {
        Snake snake = new Snake(1, 1, 10, 10);
        snake.moveSnake(Direction.LEFT);
        assertEquals(snake.getSnake().get(0).getX(), 0);
        assertEquals(snake.getSnake().get(0).getY(), 1);
    }

    @Test
    public void snakeMoveSnakeRightTest() {
        Snake snake = new Snake(1, 1, 10, 10);
        snake.moveSnake(Direction.RIGHT);
        assertEquals(snake.getSnake().get(0).getX(), 2);
        assertEquals(snake.getSnake().get(0).getY(), 1);
    }
}
