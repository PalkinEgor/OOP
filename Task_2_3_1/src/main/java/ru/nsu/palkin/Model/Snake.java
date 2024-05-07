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
     * Getter for snake.
     *
     * @return snake
     */
    public ArrayList<BodyPart> getSnake() {
        return this.snake;
    }
}
