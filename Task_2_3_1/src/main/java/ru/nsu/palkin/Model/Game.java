package ru.nsu.palkin.model;

import java.util.ArrayList;
import ru.nsu.palkin.view.ViewRenderer;

/**
 * Game class.
 */
public class Game {
    private final int width;
    private final int height;
    private final int foodCount;
    private final int conditionOfVictory;
    private final int startX;
    private final int startY;
    private boolean gameOverStatus;
    private Direction direction;
    private ArrayList<Food> foods;
    private Snake snake;
    private ViewRenderer viewRenderer;

    /**
     * Class constructor.
     *
     * @param width              - width of window.
     * @param height             - height of window
     * @param foodCount          - count of food
     * @param conditionOfVictory - max snake length
     * @param startX             - start x position
     * @param startY             - start y position
     * @param viewRenderer       - veiwRederer object
     */
    public Game(int width, int height, int foodCount, int conditionOfVictory,
                int startX, int startY, ViewRenderer viewRenderer) {
        this.width = width;
        this.height = height;
        this.foodCount = foodCount;
        this.conditionOfVictory = conditionOfVictory;
        this.startX = startX;
        this.startY = startY;
        this.viewRenderer = viewRenderer;
        initGame();
    }

    /**
     * Game loop method.
     */
    public void loopGame() {
        if (this.gameOverStatus) {
            this.viewRenderer.drawWord("You Lose");
            return;
        }
        if (this.snake.getSnake().size() == this.conditionOfVictory) {
            this.viewRenderer.drawWord("You Win!");
            return;
        }
        foodHandler();
        this.snake.moveSnake(this.direction);
        loseChecker();
        this.viewRenderer.clearField();
        this.viewRenderer.drawField();
        this.viewRenderer.drawFood(this.foods);
        this.viewRenderer.drawSnake(this.snake);
    }

    /**
     * Init game method.
     */
    private void initGame() {
        this.snake = new Snake(this.startX, this.startY, this.width, this.height);
        this.foods = new ArrayList<>();
        for (int i = 0; i < this.foodCount; i++) {
            this.foods.add(new Food(this.width, this.height));
            this.foods.get(this.foods.size() - 1).generateFood(getBadPoints());
        }
        this.direction = Direction.LEFT;
    }

    /**
     * Lose checking method.
     */
    private void loseChecker() {
        for (int i = 1; i < this.snake.getSnake().size(); i++) {
            if (this.snake.getSnake().get(0).getX() == this.snake.getSnake().get(i).getX()
                    && this.snake.getSnake().get(0).getY()
                    == this.snake.getSnake().get(i).getY()) {
                this.gameOverStatus = true;
                break;
            }
        }
    }

    /**
     * Food checking method.
     */
    private void foodHandler() {
        for (int i = 0; i < this.foodCount; i++) {
            if (this.snake.getSnake().get(0).getX() == foods.get(i).getFoodX()
                    && this.snake.getSnake().get(0).getY() == foods.get(i).getFoodY()) {
                this.snake.getSnake().add(new BodyPart(-1, -1, this.width, this.height));
                this.foods.remove(foods.get(i));
                ArrayList<Point> badPoints = getBadPoints();
                if (badPoints.size() == this.width * this.height) {
                    break;
                }
                this.foods.add(new Food(this.width, this.height));
                this.foods.get(this.foods.size() - 1).generateFood(badPoints);
            }
        }
    }

    /**
     * Get bad points method.
     *
     * @return list of bad points
     */
    private ArrayList<Point> getBadPoints() {
        ArrayList<Point> badPoints = new ArrayList<>();
        for (Food value : this.foods) {
            badPoints.add(new Point(value.getFoodX(), value.getFoodY()));
        }
        for (int i = 0; i < this.snake.getSnake().size(); i++) {
            badPoints.add(new Point(this.snake.getSnake().get(i).getX(),
                    this.snake.getSnake().get(i).getY()));
        }
        return badPoints;
    }

    /**
     * Getter for snake object.
     *
     * @return snake
     */
    public Snake getSnake() {
        return this.snake;
    }

    /**
     * Getter for game over status.
     *
     * @return true or false
     */
    public boolean getGameOverStatus() {
        return this.gameOverStatus;
    }

    /**
     * Getter for direction.
     *
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Setter for direction.
     *
     * @param direction - direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
