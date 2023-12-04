package ru.nsu.palkin;

import java.util.Stack;

/**
 * Calculator class.
 */
public class Calculator {
    private OperatorFactory factory;
    private Stack<Double> stack;

    /**
     * Class constructor.
     *
     * @param factory - factory object
     */
    public Calculator(OperatorFactory factory) {
        this.factory = factory;
        this.stack = new Stack<>();
    }

    /**
     * Method for calculation.
     *
     * @param expression - expression
     * @return result of the expression
     */
    public double calculate(String expression) {
        String[] part = expression.split(" ");
        int len = part.length;
        for (int i = len - 1; i >= 0; i--) {
            if (isNumeric(part[i])) {
                this.stack.push(Double.parseDouble(part[i]));
            } else {
                Operator operator = this.factory.createOperator(part[i]);
                operator.apply(this.stack);
            }
        }
        return this.stack.pop();
    }

    /**
     * Method for checking is it number or not.
     *
     * @param str - the string being checked
     * @return true or false
     */
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
