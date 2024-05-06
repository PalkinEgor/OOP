package ru.nsu.palkin.task_2_3_1.Model;

/**
 * Body part class.
 */
public class BodyPart {
    private int x;
    private int y;
    private final int width;
    private final int height;

    /**
     * Body part constructor.
     *
     * @param x      - x
     * @param y      - y
     * @param width  - width of window
     * @param height - height of window
     */
    public BodyPart(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Move body part method.
     *
     * @param dx - x offset
     * @param dy - y offset
     */
    public void moveBodyPart(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
        if (this.x < 0) {
            this.x = width - 1;
        }
        if (this.x == width) {
            this.x = 0;
        }
        if (this.y < 0) {
            this.y = height - 1;
        }
        if (this.y == height) {
            this.y = 0;
        }
    }

    /**
     * X getter.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Y getter.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * X setter.
     *
     * @param x - x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Y setter.
     *
     * @param y - y
     */
    public void setY(int y) {
        this.y = y;
    }
}