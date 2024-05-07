package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.model.BodyPart;

/**
 * Body part test class.
 */
public class BodyPartTest {
    @Test
    public void moveBodyPartTestX() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(1, 0);
        assertEquals(bodyPart.getX(), 1);
    }

    @Test
    public void moveBodyPartTestY() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(0, 1);
        assertEquals(bodyPart.getY(), 1);
    }

    @Test
    public void moveBodyPartExtraTestX() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(10, 0);
        assertEquals(bodyPart.getX(), 0);
    }

    @Test
    public void moveBodyPartExtraTestY() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(0, 10);
        assertEquals(bodyPart.getY(), 0);

    }

    @Test
    public void moveBodyPartSetTestX() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.setX(1);
        assertEquals(bodyPart.getX(), 1);
    }

    @Test
    public void moveBodyPartSetTestY() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.setY(1);
        assertEquals(bodyPart.getY(), 1);
    }
}
