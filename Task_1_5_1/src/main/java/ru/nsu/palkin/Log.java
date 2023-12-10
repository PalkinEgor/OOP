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
    public void apply(Stack<Double> stack) throws InvalidBaseOfLogException,
            InvalidIndOfLogException {
        double first;
        double second;
        try {
            first = stack.pop();
            second = stack.pop();
        } catch (RuntimeException e) {
            throw new RuntimeException("Stack is empty");
        }
        if (second <= 0) {
            throw new InvalidBaseOfLogException("Base of log below zero");
        }
        if (first <= 0 || first == 1) {
            throw new InvalidIndOfLogException("Indicator of log below zero or equal to one");
        }
        stack.push(Math.log(second) / Math.log(first));
    }
}