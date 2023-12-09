package ru.nsu.palkin;

import java.util.Stack;

/**
 * Power class.
 */
public class Power implements Operator {
    /**
     * Override apply method.
     *
     * @param stack - stack with numbers
     */
    @Override
    public void apply(Stack<Double> stack) throws InvalidPowArgException {
        double first;
        double second;
        try {
            first = stack.pop();
            second = stack.pop();
        } catch (RuntimeException e) {
            throw new RuntimeException("Stack is empty");
        }
        if (!Double.isFinite(Math.pow(first, second))) {
            throw new InvalidPowArgException("Invalid argument");
        }
        stack.push(Math.pow(first, second));
    }
}
