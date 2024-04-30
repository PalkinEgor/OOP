package ru.nsu.palkin.task_2_3_1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends Application {
    private static final int CELL_SIZE = 20;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private ArrayList<Point> snake = new ArrayList<>();
    private Direction direction = Direction.LEFT;
    private int foodX;
    private int foodY;
    private boolean gameOverStatus = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < 1; i++) {
            this.snake.add(new Point(10, 10 + i));
        }
        generateFood();

        Canvas canvas = new Canvas(WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);
        GraphicsContext context = canvas.getGraphicsContext2D();

        Pane pane = new Pane();
        pane.getChildren().add(canvas);

        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    game(context);
                    return;
                }

                if (now - lastTick > 100000000) {
                    lastTick = now;
                    game(context);
                }
            }
        }.start();

        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W:
                    if (this.direction != Direction.DOWN) {
                        this.direction = Direction.UP;
                    }
                    break;
                case S:
                    if (this.direction != Direction.UP) {
                        this.direction = Direction.DOWN;
                    }
                    break;
                case A:
                    if (this.direction != Direction.RIGHT) {
                        this.direction = Direction.LEFT;
                    }
                    break;
                case D:
                    if (this.direction != Direction.LEFT) {
                        this.direction = Direction.RIGHT;
                    }
                    break;
                default:
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
    }

    private void game(GraphicsContext context) {
        if (this.gameOverStatus) {
            context.setFill(Color.RED);
            context.setFont(Font.font("Arial", 60));
            context.fillText("Game Over", 150, 200);
            return;
        }

        if (this.snake.get(0).getX() == this.foodX && this.snake.get(0).getY() == this.foodY) {
            this.snake.add(new Point(-1, -1));
            generateFood();
        }

        for (int i = this.snake.size() - 1; i >= 1; i--) {
            this.snake.get(i).setX(this.snake.get(i - 1).getX());
            this.snake.get(i).setY(this.snake.get(i - 1).getY());
        }

        switch (this.direction) {
            case UP:
                this.snake.get(0).setY(this.snake.get(0).getY() - 1);
                break;
            case DOWN:
                this.snake.get(0).setY(this.snake.get(0).getY() + 1);
                break;
            case LEFT:
                this.snake.get(0).setX(this.snake.get(0).getX() - 1);
                break;
            case RIGHT:
                this.snake.get(0).setX(snake.get(0).getX() + 1);
                break;
        }

        for (int i = 1; i < this.snake.size(); i++) {
            if (this.snake.get(0).getX() == this.snake.get(i).getX() &&
                    this.snake.get(0).getY() == this.snake.get(i).getY()) {
                this.gameOverStatus = true;
                break;
            }
        }

        context.clearRect(0, 0, WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);

        context.setFill(Color.RED);
        context.fillOval(foodX * CELL_SIZE, foodY * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        context.setFill(Color.GREEN);
        for (Point point : this.snake) {
            context.fillOval(point.getX() * CELL_SIZE, point.getY() *
                    CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    private void generateFood() {
        Random rand = new Random();
        this.foodX = rand.nextInt(WIDTH);
        this.foodY = rand.nextInt(HEIGHT);
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
