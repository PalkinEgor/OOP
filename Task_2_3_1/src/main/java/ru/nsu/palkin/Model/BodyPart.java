package ru.nsu.palkin.model;

/**
 * Body part class.
 */
public class BodyPart {
    private int xp;
    private int yp;
    private final int width;
    private final int height;

    /**
     * Body part constructor.
     *
     * @param xp     - x
     * @param yp     - y
     * @param width  - width of window
     * @param height - height of window
     */
    public BodyPart(int xp, int yp, int width, int height) {
        this.xp = xp;
        this.yp = yp;
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
        this.xp = this.xp + dx;
        this.yp = this.yp + dy;
        if (this.xp < 0) {
            this.xp = width - 1;
        }
        if (this.xp == width) {
            this.xp = 0;
        }
        if (this.yp < 0) {
            this.yp = height - 1;
        }
        if (this.yp == height) {
            this.yp = 0;
        }
    }

    /**
     * X getter.
     *
     * @return x
     */
    public int getX() {
        return xp;
    }

    /**
     * Y getter.
     *
     * @return y
     */
    public int getY() {
        return yp;
    }

    /**
     * X setter.
     *
     * @param xp - x
     */
    public void setX(int xp) {
        this.xp = xp;
    }

    /**
     * Y setter.
     *
     * @param yp - y
     */
    public void setY(int yp) {
        this.yp = yp;
    }
}