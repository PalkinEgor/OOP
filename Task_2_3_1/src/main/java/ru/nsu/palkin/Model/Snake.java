package ru.nsu.palkin.model;

import java.util.ArrayList;

/**
 * Snake class.
 */
public class Snake {
    private ArrayList<BodyPart> snake;

    /**
     * Snake constructor.
     *
     * @param startX - start x position
     * @param startY - start y position
     * @param width  - width of window
     * @param height - height of window
     */
    public Snake(int startX, int startY, int width, int height) {
        this.snake = new ArrayList<>();
        this.snake.add(new BodyPart(startX, startY, width, height));
    }

    /**
     * Move snake method.
     */
    public void moveSnake(Direction direction) {
        for (int i = this.snake.size() - 1; i >= 1; i--) {
            this.snake.get(i).setX(this.snake.get(i - 1).getX());
            this.snake.get(i).setY(this.snake.get(i - 1).getY());
        }
        switch (direction) {
            case UP:
                this.snake.get(0).moveBodyPart(0, -1);
                break;
            case DOWN:
                this.snake.get(0).moveBodyPart(0, 1);
                break;
            case LEFT:
                this.snake.get(0).moveBodyPart(-1, 0);
                break;
            case RIGHT:
                this.snake.get(0).moveBodyPart(1, 0);
                break;
            default:
                break;
        }
    }

    /**
     * Getter for snake.
     *
     * @return snake
     */
    public ArrayList<BodyPart> getSnake() {
        return this.snake;
    }
}
