package ru.nsu.palkin;

import java.util.Stack;

/**
 * Sin class.
 */
public class Sin implements Operator {
    /**
     * Override apply method.
     *
     * @param stack - stack with numbers
     */
    @Override
    public void apply(Stack<Double> stack) {
        try {
            double first = stack.pop();
            stack.push(Math.sin(first));
        } catch (RuntimeException e) {
            throw new RuntimeException("Stack is empty");
        }
    }
}
