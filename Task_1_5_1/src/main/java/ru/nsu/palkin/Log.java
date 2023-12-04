package ru.nsu.palkin;

import java.util.Stack;

/**
 * Log class.
 */
public class Log implements Operator {
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
        if (!Double.isFinite(Math.log(second) / Math.log(first))) {
            throw new ArithmeticException("Invalid argument");
        }
        stack.push(Math.log(second) / Math.log(first));
    }
}