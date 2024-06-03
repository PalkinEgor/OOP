package ru.nsu.palkin.model;

/**
 * Point class.
 */
public class Point {
    private int xp;
    private int yp;

    /**
     * Point constructor.
     *
     * @param xp - x
     * @param yp - y
     */
    public Point(int xp, int yp) {
        this.xp = xp;
        this.yp = yp;
    }

    /**
     * Getter for x position.
     *
     * @return x position
     */
    public int getX() {
        return this.xp;
    }

    /**
     * Getter for y position.
     *
     * @return y position
     */
    public int getY() {
        return this.yp;
    }
}
