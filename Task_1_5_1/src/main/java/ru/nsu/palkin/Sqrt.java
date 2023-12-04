package ru.nsu.palkin;

import java.util.Stack;

/**
 * Sqrt class.
 */
public class Sqrt implements Operator {
    /**
     * Override apply method.
     *
     * @param stack - stack with numbers
     */
    @Override
    public void apply(Stack<Double> stack) {
        double first;
        try {
            first = stack.pop();
        } catch (RuntimeException e) {
            throw new RuntimeException("Stack is empty");
        }
        if (first < 0) {
            throw new ArithmeticException("The root expression is less than zero");
        }
        stack.push(Math.sqrt(first));
    }
}
