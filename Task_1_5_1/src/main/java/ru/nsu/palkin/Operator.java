package ru.nsu.palkin;

import java.util.Stack;

/**
 * Operator class.
 */
public interface Operator {
    /**
     * Method for calculation.
     *
     * @param stack - stack with numbers
     */
    void apply(Stack<Double> stack) throws DivisionByZeroException,
            InvalidBaseOfLogException, InvalidIndOfLogException,
            InvalidSqrtArgException, InvalidPowArgException;
}
