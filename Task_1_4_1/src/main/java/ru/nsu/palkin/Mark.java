package ru.nsu.palkin;

/**
 * Mark enum type.
 */
public enum Mark {
    TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int mark;

    /**
     * Class constructor.
     *
     * @param mark - mark
     */
    Mark(int mark) {
        this.mark = mark;
    }

    /**
     * Getter for mark.
     *
     * @return mark
     */
    public int getMark() {
        return mark;
    }
}