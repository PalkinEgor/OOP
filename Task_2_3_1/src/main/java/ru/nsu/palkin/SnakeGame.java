package ru.nsu.palkin;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.nsu.palkin.controller.Controller;
import ru.nsu.palkin.model.Game;
import ru.nsu.palkin.view.ViewRenderer;

/**
 * Main class.
 */
public class SnakeGame extends Application {
    private static final int width = 30;
    private static final int height = 20;
    private static final int cellSize = 20;
    private static final int foodCount = 3;
    private static final int conditionOfVictory = 50;
    private static final int startX = 15;
    private static final int startY = 10;
    private static final long speed = 100000000;

    /**
     * Start game method.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(width * cellSize, height * cellSize);
        GraphicsContext context = canvas.getGraphicsContext2D();
        Pane root = new Pane();
        root.getChildren().add(canvas);
        ViewRenderer viewRenderer = new ViewRenderer(width, height, cellSize, context);
        Game game = new Game(width, height, foodCount, conditionOfVictory,
                startX, startY, viewRenderer);
        Controller controller = new Controller(game);
        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    game.loopGame();
                    return;
                }
                if (now - lastTick > speed) {
                    lastTick = now;
                    game.loopGame();
                }
            }
        }.start();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(controller::keyHandler);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
    }

    /**
     * Entry point method.
     *
     * @param args - args
     */
    public static void main(String[] args) {
        launch();
    }
}
