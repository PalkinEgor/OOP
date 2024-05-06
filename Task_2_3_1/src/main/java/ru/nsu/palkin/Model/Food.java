package ru.nsu.palkin.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Food class.
 */
public class Food {
    private int foodX;
    private int foodY;
    private int width;
    private int height;

    /**
     * Class constructor.
     *
     * @param width  - width of window
     * @param height - height of window
     */
    public Food(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Generate food position method.
     *
     * @param badPoints - bad points
     */
    public void generateFood(ArrayList<Point> badPoints) {
        Random rand = new Random();
        while (true) {
            this.foodX = rand.nextInt(this.width);
            this.foodY = rand.nextInt(this.height);
            boolean status = true;
            for (Point badPoint : badPoints) {
                if (this.foodX == badPoint.getX() && this.foodY == badPoint.getY()) {
                    status = false;
                    break;
                }
            }
            if (status) {
                break;
            }
        }
    }

    /**
     * Food x position getter.
     *
     * @return food x position
     */
    public int getFoodX() {
        return foodX;
    }

    /**
     * Food y position getter.
     *
     * @return food y position
     */
    public int getFoodY() {
        return foodY;
    }
}
