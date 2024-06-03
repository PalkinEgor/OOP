package ru.nsu.palkin.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
    private final int startBotX;
    private final int startBotY;
    private final Strategy botStrategy;
    private boolean gameOverStatus = false;
    private boolean botStatus = false;
    private Direction direction;
    private Direction botDirection;
    private ArrayList<Food> foods;
    private Snake snake;
    private Snake botSnake;
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
                int startX, int startY, int startBotX, int startBotY,
                Strategy botStrategy, ViewRenderer viewRenderer) {
        this.width = width;
        this.height = height;
        this.foodCount = foodCount;
        this.conditionOfVictory = conditionOfVictory;
        this.startX = startX;
        this.startY = startY;
        this.startBotX = startBotX;
        this.startBotY = startBotY;
        this.botStrategy = botStrategy;
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
        if (this.botStrategy == Strategy.GREEDY) {
            greedy();
        }
        if (this.botStrategy == Strategy.BFS) {
            bfs();
        }
        this.botSnake.moveSnake(this.botDirection);
        loseChecker();
        if (!this.botStatus) {
            updateBotStatus();
        }
        this.viewRenderer.clearField();
        this.viewRenderer.drawField();
        this.viewRenderer.drawFood(this.foods);
        this.viewRenderer.drawSnake(this.snake, this.botSnake, this.botStatus);
    }

    /**
     * Init game method.
     */
    private void initGame() {
        this.snake = new Snake(this.startX, this.startY, this.width, this.height);
        this.botSnake = new Snake(this.startBotX, this.startBotY, this.width, this.height);
        this.foods = new ArrayList<>();
        for (int i = 0; i < this.foodCount; i++) {
            this.foods.add(new Food(this.width, this.height));
            this.foods.get(this.foods.size() - 1).generateFood(getBadPoints());
        }
        this.direction = Direction.LEFT;
        this.botDirection = Direction.RIGHT;
    }

    /**
     * Calculate Manhattan distance
     *
     * @param first  - first point
     * @param second - second point
     * @return distance
     */
    private int getDistance(Point first, Point second) {
        return Math.abs(first.getX() - second.getX()) + Math.abs(first.getY() - second.getY());
    }

    /**
     * Method for getting bad direction.
     *
     * @param direction - direction
     * @return direction
     */
    private Direction getBadDirection(Direction direction) {
        if (direction == Direction.UP) {
            return Direction.DOWN;
        }
        if (direction == Direction.DOWN) {
            return Direction.UP;
        }
        if (direction == Direction.RIGHT) {
            return Direction.LEFT;
        }
        return Direction.RIGHT;
    }

    /**
     * Method to get available neighbours.
     *
     * @param bodyPart - point
     * @return list of points
     */
    private ArrayList<BodyPart> getNeighbours(BodyPart bodyPart) {
        ArrayList<BodyPart> result = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            BodyPart current = bodyPart.clone();
            if (dir == Direction.UP) {
                current.moveBodyPart(0, -1);
            }
            if (dir == Direction.DOWN) {
                current.moveBodyPart(0, 1);
            }
            if (dir == Direction.RIGHT) {
                current.moveBodyPart(1, 0);
            }
            if (dir == Direction.LEFT) {
                current.moveBodyPart(-1, 0);
            }
            boolean flag = false;
            for (int j = 1; j < this.botSnake.getSnake().size(); j++) {
                if (current.getX() == this.botSnake.getSnake().get(j).getX() &&
                        current.getY() == this.botSnake.getSnake().get(j).getY()) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            for (int j = 0; j < this.snake.getSnake().size(); j++) {
                if (current.getX() == this.snake.getSnake().get(j).getX() &&
                        current.getY() == this.snake.getSnake().get(j).getY()) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            result.add(current);
        }
        return result;
    }

    /**
     * BFS strategy bot controller.
     */
    private void bfs() {
        Deque<BodyPart> queue = new ArrayDeque<>();
        queue.addLast(this.botSnake.getSnake().get(0));
        int[][] distances = new int[this.height][this.width];
        BodyPart[][] parents = new BodyPart[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                distances[i][j] = -1;
                parents[i][j] = null;
            }
        }
        distances[this.botSnake.getSnake().get(0).getY()]
                [this.botSnake.getSnake().get(0).getX()] = 0;
        while (!queue.isEmpty()) {
            BodyPart current = queue.pop();
            ArrayList<BodyPart> neighbours = getNeighbours(current);
            for (BodyPart bd : neighbours) {
                if (distances[bd.getY()][bd.getX()] == -1) {
                    queue.addLast(bd);
                    distances[bd.getY()][bd.getX()] = distances[current.getY()]
                            [current.getX()] + 1;
                    parents[bd.getY()][bd.getX()] = current.clone();
                }
            }
        }

        int minDistance = this.height * this.width + 1;
        BodyPart destination = new BodyPart(-1, -1, this.width, this.height);
        for (int i = 0; i < this.foodCount; i++) {
            if (distances[this.foods.get(i).getFoodY()]
                    [this.foods.get(i).getFoodX()] < minDistance) {
                minDistance = distances[this.foods.get(i).getFoodY()]
                        [this.foods.get(i).getFoodX()];
                destination.setX(this.foods.get(i).getFoodX());
                destination.setY(this.foods.get(i).getFoodY());
            }
        }
        try {
            while (!(parents[destination.getY()][destination.getX()].getX()
                    == this.botSnake.getSnake().get(0).getX() &&
                    parents[destination.getY()][destination.getX()].getY()
                            == this.botSnake.getSnake().get(0).getY())) {
                destination = parents[destination.getY()][destination.getX()].clone();
            }
        } catch (NullPointerException ignored) {
        }
        for (Direction dir : Direction.values()) {
            BodyPart current = this.botSnake.getSnake().get(0).clone();
            if (dir == Direction.UP) {
                current.moveBodyPart(0, -1);
                if (current.getX() == destination.getX() && current.getY() == destination.getY()) {
                    this.botDirection = Direction.UP;
                    break;
                }
            }
            if (dir == Direction.DOWN) {
                current.moveBodyPart(0, 1);
                if (current.getX() == destination.getX() && current.getY() == destination.getY()) {
                    this.botDirection = Direction.DOWN;
                    break;
                }
            }
            if (dir == Direction.RIGHT) {
                current.moveBodyPart(1, 0);
                if (current.getX() == destination.getX() && current.getY() == destination.getY()) {
                    this.botDirection = Direction.RIGHT;
                    break;
                }
            }
            if (dir == Direction.LEFT) {
                current.moveBodyPart(-1, 0);
                if (current.getX() == destination.getX() && current.getY() == destination.getY()) {
                    this.botDirection = Direction.LEFT;
                    break;
                }
            }
        }
    }

    /**
     * Greedy strategy bot controller.
     */
    private void greedy() {
        int minDistance = this.height * this.width;
        Direction minDirection = this.botDirection;
        for (int i = 0; i < this.foodCount; i++) {
            Direction badDir = getBadDirection(this.botDirection);
            for (Direction dir : Direction.values()) {
                BodyPart current = this.botSnake.getSnake().get(0).clone();
                if (dir != badDir) {
                    if (dir == Direction.UP) {
                        current.moveBodyPart(0, -1);
                    }
                    if (dir == Direction.DOWN) {
                        current.moveBodyPart(0, 1);
                    }
                    if (dir == Direction.RIGHT) {
                        current.moveBodyPart(1, 0);
                    }
                    if (dir == Direction.LEFT) {
                        current.moveBodyPart(-1, 0);
                    }
                }
                boolean flag = false;
                for (int j = 1; j < this.botSnake.getSnake().size(); j++) {
                    if (current.getX() == this.botSnake.getSnake().get(j).getX() &&
                            current.getY() == this.botSnake.getSnake().get(j).getY()) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                }
                for (int j = 0; j < this.snake.getSnake().size(); j++) {
                    if (current.getX() == this.snake.getSnake().get(j).getX() &&
                            current.getY() == this.snake.getSnake().get(j).getY()) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                }
                int currentDistance = getDistance(new Point(current.getX(), current.getY()),
                        new Point(this.foods.get(i).getFoodX(), this.foods.get(i).getFoodY()));
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    if (dir == Direction.UP) {
                        minDirection = Direction.UP;
                    }
                    if (dir == Direction.DOWN) {
                        minDirection = Direction.DOWN;
                    }
                    if (dir == Direction.RIGHT) {
                        minDirection = Direction.RIGHT;
                    }
                    if (dir == Direction.LEFT) {
                        minDirection = Direction.LEFT;
                    }
                }
            }
        }
        this.botDirection = minDirection;
    }

    /**
     * Update bot status method.
     */
    private void updateBotStatus() {
        for (int i = 1; i < this.botSnake.getSnake().size(); i++) {
            if (this.botSnake.getSnake().get(0).getX() == this.botSnake.getSnake().get(i).getX()
                    && this.botSnake.getSnake().get(0).getY()
                    == this.botSnake.getSnake().get(i).getY()) {
                this.botStatus = true;
                break;
            }
        }
        for (int i = 0; i < this.snake.getSnake().size(); i++) {
            if (this.botSnake.getSnake().get(0).getX() == this.snake.getSnake().get(i).getX()
                    && this.botSnake.getSnake().get(0).getY()
                    == this.snake.getSnake().get(i).getY()) {
                this.botStatus = true;
                break;
            }
        }
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
        if (!this.botStatus) {
            for (int i = 0; i < this.botSnake.getSnake().size(); i++) {
                if (this.snake.getSnake().get(0).getX() == this.botSnake.getSnake().get(i).getX()
                        && this.snake.getSnake().get(0).getY()
                        == this.botSnake.getSnake().get(i).getY()) {
                    this.gameOverStatus = true;
                    break;
                }
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
            if (!this.botStatus) {
                if (this.botSnake.getSnake().get(0).getX() == foods.get(i).getFoodX()
                        && this.botSnake.getSnake().get(0).getY() == foods.get(i).getFoodY()) {
                    this.botSnake.getSnake().add(new BodyPart(-1, -1,
                            this.width, this.height));
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
        if (!this.botStatus) {
            for (int i = 0; i < this.botSnake.getSnake().size(); i++) {
                badPoints.add(new Point(this.botSnake.getSnake().get(i).getX(),
                        this.botSnake.getSnake().get(i).getY()));
            }
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
