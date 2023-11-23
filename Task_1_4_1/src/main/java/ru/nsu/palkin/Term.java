package ru.nsu.palkin;

/**
 * Number of term enum type.
 */
public enum Term {
    FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5), SIXTH(6), SEVENTH(7), EIGHTH(8);

    private final int term;

    /**
     * Class constructor.
     *
     * @param term - term
     */
    Term(int term) {
        this.term = term;
    }

    /**
     * Getter for term.
     *
     * @return term
     */
    public int getTerm() {
        return term;
    }

    /**
     * Get enum value by number.
     *
     * @param n - number
     * @return enum value
     */
    public Term getTerm(int n) {
        for (Term term : Term.values()) {
            if (term.getTerm() == n) {
                return term;
            }
        }
        return null;
    }
}