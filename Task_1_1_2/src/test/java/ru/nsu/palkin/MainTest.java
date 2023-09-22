package ru.nsu.palkin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс с тестами.
 */
public class MainTest {
    @Test
    public void plusTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        assertEquals("7.0x^3 + 14.0x^2 + 5.0x + 7.0", p1.plus(p2).toStr());
    }

    @Test
    public void minusTest() {
        Polynomial p1 = new Polynomial(new double[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        assertEquals("7.0x^3 - 2.0x^2 + x + 1.0", p1.minus(p2).toStr());
    }

    @Test
    public void minus2Test() {
        Polynomial p1 = new Polynomial(new double[]{1, 2, 3, 4});
        Polynomial p2 = new Polynomial(new double[]{1, 2, 3, 4});
        assertEquals("0", p1.minus(p2).toStr());
    }

    @Test
    public void minus3Test() {
        Polynomial p1 = new Polynomial(new double[]{2, 3, 4, 5});
        Polynomial p2 = new Polynomial(new double[]{1, 3, 4, 4});
        assertEquals("x^3 + 1.0", p1.minus(p2).toStr());
    }

    @Test
    public void timesTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        assertEquals("56.0x^5 + 62.0x^4 + 57.0x^3 + 56.0x^2 + 17.0x + 12.0", p1.times(p2).toStr());
    }

    @Test
    public void differentiateTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        assertEquals("21.0x^2 + 12.0x + 3.0", p1.differentiate(1).toStr());
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
        assertTrue(p1.equality(p2));
    }

    @Test
    public void equalityFalseTest() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{1, 2, 3});
        assertFalse(p1.equality(p2));
    }
}
