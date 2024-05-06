package ru.nsu.palkin.View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ru.nsu.palkin.Model.BodyPart;
import ru.nsu.palkin.Model.Food;
import ru.nsu.palkin.Model.Snake;

import java.util.ArrayList;

/**
 * View renderer class.
 */
public class ViewRenderer {
    private final int width;
    private final int height;
    private final int cellSize;
    private final GraphicsContext context;

    /**
     * Class constructor.
     *
     * @param width    - width of window
     * @param height   - height of window
     * @param cellSize - cell size
     * @param context  - graphic field
     */
    public ViewRenderer(int width, int height, int cellSize, GraphicsContext context) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.context = context;
    }

    /**
     * Draw field method.
     */
    public void drawField() {
        this.context.setFill(Color.rgb(144, 238, 144));
        this.context.fillRect(0, 0, this.width * this.cellSize, this.height * this.cellSize);
    }

    /**
     * Update field method.
     */
    public void clearField() {
        this.context.clearRect(0, 0, this.width * this.cellSize, this.height * this.cellSize);
    }

    /**
     * Draw food method.
     *
     * @param foods - array of food
     */
    public void drawFood(ArrayList<Food> foods) {
        this.context.setFill(Color.RED);
        for (Food food : foods) {
            this.context.fillOval(food.getFoodX() * this.cellSize,
                    food.getFoodY() * this.cellSize, this.cellSize, this.cellSize);
        }
    }

    /**
     * Draw snake method.
     *
     * @param snake - snake object
     */
    public void drawSnake(Snake snake) {
        this.context.setFill(Color.GREEN);
        ArrayList<BodyPart> bodyParts = snake.getSnake();
        for (BodyPart bodyPart : bodyParts) {
            this.context.fillOval(bodyPart.getX() * this.cellSize,
                    bodyPart.getY() * this.cellSize, this.cellSize, this.cellSize);
        }
    }

    /**
     * Draw word method.
     *
     * @param message - message
     */
    public void drawWord(String message) {
        this.context.setFill(Color.RED);
        this.context.setFont(Font.font("Arial", 60));
        this.context.fillText(message, 150, 200);
    }
}
