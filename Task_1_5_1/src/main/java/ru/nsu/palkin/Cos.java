package ru.nsu.palkin;

import java.util.Stack;

/**
 * Cos class.
 */
public class Cos implements Operator {
    /**
     * Override apply method.
     *
     * @param stack - stack with numbers
     */
    @Override
    public void apply(Stack<Double> stack) {
        try {
            double first = stack.pop();
            stack.push(Math.cos(first));
        } catch (RuntimeException e) {
            throw new RuntimeException("Stack is empty");
        }
    }
}
