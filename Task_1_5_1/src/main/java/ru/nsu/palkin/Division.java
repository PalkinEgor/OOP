package ru.nsu.palkin;

import java.util.Stack;

/**
 * Division class.
 */
public class Division implements Operator {
    /**
     * Override apply method.
     *
     * @param stack - stack with numbers
     */
    @Override
    public void apply(Stack<Double> stack) {
        double first;
        double second;
        try {
            first = stack.pop();
            second = stack.pop();
        } catch (RuntimeException e) {
            throw new RuntimeException("Stack is empty");
        }
        if (!Double.isFinite(first / second)) {
            throw new ArithmeticException("Invalid argument");
        }
        stack.push(first / second);
    }
}