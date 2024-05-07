package ru.nsu.palkin.model;

/**
 * Point class.
 */
public class Point {
    private final int xPosition;
    private final int yPosition;

    /**
     * Point constructor.
     *
     * @param xPosition - x
     * @param yPosition - y
     */
    public Point(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Getter for x position.
     *
     * @return x position
     */
    public int getX() {
        return this.xPosition;
    }

    /**
     * Getter for y position.
     *
     * @return y position
     */
    public int getY() {
        return this.yPosition;
    }
}
