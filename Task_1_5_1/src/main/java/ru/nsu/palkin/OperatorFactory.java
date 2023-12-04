package ru.nsu.palkin;

/**
 * Class Operator factory.
 */
public class OperatorFactory {
    /**
     * Method for crating correct operator.
     *
     * @param type - type of operator
     * @return object
     */
    public Operator createOperator(String type) {
        Operator operator = null;
        switch (type) {
            case "+":
                operator = new Addition();
                break;
            case "-":
                operator = new Subtraction();
                break;
            case "/":
                operator = new Division();
                break;
            case "*":
                operator = new Multiplication();
                break;
            case "log":
                operator = new Log();
                break;
            case "^":
                operator = new Power();
                break;
            case "sqrt":
                operator = new Sqrt();
                break;
            case "sin":
                operator = new Sin();
                break;
            case "cos":
                operator = new Cos();
                break;
            default:
                throw new RuntimeException("Wrong operator");
        }
        return operator;
    }
}