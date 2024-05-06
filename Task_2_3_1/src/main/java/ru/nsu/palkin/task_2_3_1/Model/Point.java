package ru.nsu.palkin.task_2_3_1.Model;

/**
 * Point class.
 */
public class Point {
    private final int x;
    private final int y;

    /**
     * Point constructor.
     *
     * @param x - x
     * @param y - y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x position.
     *
     * @return x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter for y position.
     *
     * @return y position
     */
    public int getY() {
        return this.y;
    }
}
