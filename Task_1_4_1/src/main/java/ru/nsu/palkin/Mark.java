package ru.nsu.palkin;

/**
 * Mark enum type.
 */
public enum Mark {
    TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int mark;

    Mark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }
}