package ru.nsu.palkin;

/**
 * Division by zero exception.
 */
public class DivisionByZeroException extends InvalidInputException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}