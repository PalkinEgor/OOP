package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Класс с тестами.
 */
public class MainTest {
    @Test
    public void plusTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial p3 = new Polynomial(new double[]{7.0, 5.0, 14.0, 7.0});
        assertEquals(p1.plus(p2), p3);
    }

    @Test
    public void minusTest() {
        Polynomial p1 = new Polynomial(new double[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial p3 = new Polynomial(new double[]{1.0, 1.0, -2.0, 7.0});
        assertEquals(p1.minus(p2), p3);
    }

    @Test
    public void minusZeroTest() {
        Polynomial p1 = new Polynomial(new double[]{1, 2, 3, 4});
        Polynomial p2 = new Polynomial(new double[]{1, 2, 3, 4});
        Polynomial p3 = new Polynomial(new double[]{0});
        assertEquals(p1.minus(p2), p3);
    }

    @Test
    public void minusInnerZeroTest() {
        Polynomial p1 = new Polynomial(new double[]{2, 3, 4, 5});
        Polynomial p2 = new Polynomial(new double[]{1, 3, 4, 4});
        Polynomial p3 = new Polynomial(new double[]{1.0, 0.0, 0.0, 1.0});
        assertEquals(p1.minus(p2), p3);
    }

    @Test
    public void timesTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial p3 = new Polynomial(new double[]{12.0, 17.0, 56.0, 57.0, 62.0, 56.0});
        assertEquals(p1.times(p2), p3);
    }

    @Test
    public void differentiateTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new double[]{3.0, 12.0, 21.0});
        assertEquals(p1.differentiate(1), p2);
    }

    @Test
    public void evaluateTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        assertEquals(90, p1.evaluate(2));
    }

    @Test
    public void equalityTrueTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{4, 3, 6, 7});
        assertEquals(p1, p2);
    }

    @Test
    public void equalityFalseTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{1, 2, 3});
        assertNotEquals(p1, p2);
    }

    @Test
    public void toStringInnerZeroTest() {
        Polynomial p1 = new Polynomial(new double[]{2, 3, 4, 5});
        Polynomial p2 = new Polynomial(new double[]{1, 3, 4, 4});
        assertEquals("x^3 + 1.0", p1.minus(p2).toString());
    }

    @Test
    public void toStringPlusTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        assertEquals("7.0x^3 + 14.0x^2 + 5.0x + 7.0", p1.plus(p2).toString());
    }

    @Test
    public void toStringZeroTest() {
        Polynomial p1 = new Polynomial(new double[]{1, 2, 3, 4});
        Polynomial p2 = new Polynomial(new double[]{1, 2, 3, 4});
        assertEquals("0", p1.minus(p2).toString());
    }

    @Test
    public void toStringMinusTest() {
        Polynomial p1 = new Polynomial(new double[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        assertEquals("7.0x^3 - 2.0x^2 + x + 1.0", p1.minus(p2).toString());
    }
}