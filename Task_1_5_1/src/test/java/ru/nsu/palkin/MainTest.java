package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void exampleTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "sin + - 1 2 1";
        assertEquals(calc.calculate(expression), 0);
    }

    @Test
    public void addSubMulDivTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "/ + 1 + 2 * 3 3 - 7 19";
        assertEquals(calc.calculate(expression), -1);
    }

    @Test
    public void addTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "+ 2 3";
        assertEquals(calc.calculate(expression), 5);
    }

    @Test
    public void subTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "- 4 6";
        assertEquals(calc.calculate(expression), -2);
    }

    @Test
    public void mulTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "* 10 1.5";
        assertEquals(calc.calculate(expression), 15);
    }

    @Test
    public void divTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "/ 6 1.5";
        assertEquals(calc.calculate(expression), 4);
    }

    @Test
    public void cosTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "cos 0";
        assertEquals(calc.calculate(expression), 1);
    }

    @Test
    public void sinTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "sin 0";
        assertEquals(calc.calculate(expression), 0);
    }

    @Test
    public void powerTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "^ 2 3";
        assertEquals(calc.calculate(expression), 8);
    }

    @Test
    public void logTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "log 2 8";
        assertEquals(calc.calculate(expression), 3);
    }

    @Test
    public void sqrtTest() throws InvalidPowArgException, InvalidBaseOfLogException,
            InvalidIndOfLogException, InvalidSqrtArgException, DivisionByZeroException {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "sqrt 64";
        assertEquals(calc.calculate(expression), 8);
    }

    @Test
    public void divisionByZeroTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "/ 2 0";
        assertThrows(DivisionByZeroException.class, () -> {
            calc.calculate(expression);
        });
    }

    @Test
    public void rootValueLessThenZeroTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "sqrt - 2 4";
        assertThrows(InvalidSqrtArgException.class, () -> {
            calc.calculate(expression);
        });
    }

    @Test
    public void logValueLessThanZeroTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "log 2 - 2 4";
        assertThrows(InvalidBaseOfLogException.class, () -> {
            calc.calculate(expression);
        });
    }

    @Test
    public void logBaseLessThanZeroTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "log - 2 4 4";
        assertThrows(InvalidIndOfLogException.class, () -> {
            calc.calculate(expression);
        });
    }

    @Test
    public void powerInvalidArgTest() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "^ -2.5 -2.5";
        assertThrows(InvalidPowArgException.class, () -> {
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

    @Test
    public void wrongOperator() {
        Calculator calc = new Calculator(new OperatorFactory());
        String expression = "llog 2 8";
        assertThrows(RuntimeException.class, () -> {
            calc.calculate(expression);
        });
    }
}
