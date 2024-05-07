package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.BodyPart;
import ru.nsu.palkin.model.Direction;
import ru.nsu.palkin.model.Game;
import ru.nsu.palkin.model.Point;
import ru.nsu.palkin.model.Snake;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Game test class.
 */
public class GameTest {
    @Test
    public void loseCheckerTest() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Game game = new Game(10, 10, 10, 10, 0, 0, null);
        Snake snake = game.getSnake();
        snake.getSnake().add(new BodyPart(0, 0, 10, 10));
        Method method = Game.class.getDeclaredMethod("loseChecker");
        method.setAccessible(true);
        method.invoke(game);
        assertTrue(game.getGameOverStatus());
    }

    @Test
    public void getBadPointsTest() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Game game = new Game(10, 10, 0, 10, 0, 0, null);
        Method method = Game.class.getDeclaredMethod("getBadPoints");
        method.setAccessible(true);
        ArrayList<Point> badPoints = (ArrayList<Point>) method.invoke(game);
        assertEquals(badPoints.get(0).getX(), 0);
        assertEquals(badPoints.get(0).getY(), 0);
    }

    @Test
    public void foodHandlerTest() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Game game = new Game(3, 1, 2, 10, 0, 0, null);
        Snake snake = game.getSnake();
        Method method = Game.class.getDeclaredMethod("foodHandler");
        method.setAccessible(true);
        snake.moveSnake(Direction.RIGHT);
        method.invoke(game);
        snake.moveSnake(Direction.RIGHT);
        method.invoke(game);
        assertEquals(snake.getSnake().size(), 3);
    }
}
