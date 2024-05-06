package ru.nsu.palkin;

import org.junit.jupiter.api.Test;
import ru.nsu.palkin.Model.BodyPart;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodyPartTest {
    @Test
    public void moveBodyPartXTest() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(1, 0);
        assertEquals(bodyPart.getX(), 1);
    }

    @Test
    public void moveBodyPartYTest() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(0, 1);
        assertEquals(bodyPart.getY(), 1);
    }

    @Test
    public void moveBodyPartExtraXTest() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(10, 0);
        assertEquals(bodyPart.getX(), 0);
    }

    @Test
    public void moveBodyPartExtraYTest() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.moveBodyPart(0, 10);
        assertEquals(bodyPart.getY(), 0);

    }

    @Test
    public void moveBodyPartSetXTest() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.setX(1);
        assertEquals(bodyPart.getX(), 1);
    }

    @Test
    public void moveBodyPartSetYTest() {
        BodyPart bodyPart = new BodyPart(0, 0, 10, 10);
        bodyPart.setY(1);
        assertEquals(bodyPart.getY(), 1);
    }
}
