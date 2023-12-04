package ru.nsu.palkin;

import java.util.Stack;

/**
 * Addition class.
 */
public class Addition implements Operator {
    /**
     * Override apply method.
     *
     * @param stack - stack with numbers
     */
    @Override
    public void apply(Stack<Double> stack) {
        try {
            double first = stack.pop();
            double second = stack.pop();
            stack.push(first + second);
        } catch (RuntimeException e) {
            throw new RuntimeException("Stack is empty");
        }
    }
}
