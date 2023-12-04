package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void exampleTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "sin + - 1 2 1";
        assertEquals(calc.calculate(expression), 0);
    }

    @Test
    public void AddSubMulDivTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "/ + 1 + 2 * 3 3 - 7 19";
        assertEquals(calc.calculate(expression), -1);
    }

    @Test
    public void basicTrigonometryIdentityTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "+ ^ sin 1 2 - ^ cos 1 2 1";
        assertEquals(String.format("%.7f", calc.calculate(expression)), "0,0000000");
    }

    @Test
    public void logTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "log 2 8";
        assertEquals(calc.calculate(expression), 3);
    }

    @Test
    public void sqrtTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "sqrt 64";
        assertEquals(calc.calculate(expression), 8);
    }

    @Test
    public void divisionByZeroTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "/ 2 0";
        assertThrows(ArithmeticException.class, () -> {
            calc.calculate(expression);
        });
    }

    @Test
    public void rootValueLessThenZeroTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "sqrt - 2 4";
        assertThrows(ArithmeticException.class, () -> {
            calc.calculate(expression);
        });
    }

    @Test
    public void logValueLessThanZeroTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "log 2 - 2 4";
        assertThrows(ArithmeticException.class, () -> {
            calc.calculate(expression);
        });
    }

    @Test
    public void incorrectExpressionTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "log - 2 4";
        assertThrows(RuntimeException.class, () -> {
            calc.calculate(expression);
        });
    }
}
