package ru.nsu.palkin.Controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.palkin.Model.Direction;
import ru.nsu.palkin.Model.Game;

/**
 * Controller class.
 */
public class Controller {
    private final Game game;

    /**
     * Class constructor.
     *
     * @param game - game object
     */
    public Controller(Game game) {
        this.game = game;
    }

    /**
     * Key handler method.
     *
     * @param event - key event
     */
    public void keyHandler(KeyEvent event) {
        directionHandler(event.getCode());
    }

    /**
     * Change direction method.
     *
     * @param code - key code
     */
    private void directionHandler(KeyCode code) {
        switch (code) {
            case W:
                if (this.game.getDirection() != Direction.DOWN) {
                    this.game.setDirection(Direction.UP);
                }
                break;
            case S:
                if (this.game.getDirection() != Direction.UP) {
                    this.game.setDirection(Direction.DOWN);
                }
                break;
            case A:
                if (this.game.getDirection() != Direction.RIGHT) {
                    this.game.setDirection(Direction.LEFT);
                }
                break;
            case D:
                if (this.game.getDirection() != Direction.LEFT) {
                    this.game.setDirection(Direction.RIGHT);
                }
                break;
            default:
                break;
        }
    }
}
