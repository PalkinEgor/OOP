package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class CreditBook.
 */
public class CreditBook {
    private Student student;
    private int currentTerm;
    private ArrayList<Record> records;
    private Integer finalWorkMark;

    /**
     * Class constructor.
     *
     * @param student     - info about the student
     * @param currentTerm - term of the student
     */
    CreditBook(Student student, int currentTerm) {
        this.student = student;
        this.currentTerm = currentTerm;
        this.records = new ArrayList<>();
        this.finalWorkMark = null;
    }

    /**
     * Class constructor.
     *
     * @param student - info about the student
     */
    CreditBook(Student student) {
        this.student = student;
        this.currentTerm = 1;
        this.records = new ArrayList<>();
        this.finalWorkMark = null;
    }

    /**
     * Add a record to the credit book.
     *
     * @param typeOfCredit - credit or exam
     * @param subject      - subject
     * @param mark         - mark for the credit
     * @param term         - term when the credit is taking
     */
    public void addRecord(String typeOfCredit, String subject, int mark, int term) {
        this.records.add(new Record(typeOfCredit, subject, mark, term));
    }

    /**
     * Update value of the current term.
     */
    public void updateTerm() {
        this.currentTerm++;
    }

    /**
     * Get current mean mark.
     *
     * @return mean mark.
     */
    public double currentMeanMark() {
        double result = 0;
        int len = this.records.size();
        for (Record record : this.records) {
            result = result + record.mark;
        }
        return result / len;
    }

    /**
     * Checking for the increased student ship.
     *
     * @return true or false
     */
    public boolean increasedStudentShip() {
        int len = this.records.size();
        for (Record record : this.records) {
            if ((this.currentTerm - 1) == record.term) {
                if (record.mark < 4) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Set mark for the final work.
     *
     * @param mark - mark
     */
    public void setFinalWorkMark(Integer mark) {
        if (mark < 2 || mark > 5) {
            throw new IllegalArgumentException("wrong mark for the final work");
        }
        this.finalWorkMark = mark;
    }

    /**
     * Checking for a red certificate.
     *
     * @return true or false
     */
    public boolean redCertificate() {
        int excellentMarks = 0;
        int len = this.records.size();
        for (int i = 0; i < len; i++) {
            Record current = this.records.get(i);
            int lastMark = current.mark;
            int lastTerm = current.term;
            if (lastMark < 4) {
                return false;
            }
            for (int j = i; j < len; j++) {
                if (this.records.get(j).subject.equals(current.subject)
                        && this.records.get(j).term > lastTerm) {
                    lastTerm = this.records.get(j).term;
                    lastMark = this.records.get(j).mark;
                }
            }
            if (lastMark == 5) {
                excellentMarks++;
            }
        }
        Set<String> uniqueSubject = new HashSet<>();
        for (Record record : this.records) {
            uniqueSubject.add(record.subject);
        }
        return (((double) excellentMarks / uniqueSubject.size() >= 0.75)
                && (this.finalWorkMark == 5));
    }

    /**
     * Getter for student's info.
     *
     * @return string with student's info
     */
    public String getStudentInfo() {
        return this.student.toString();
    }

    /**
     * Getter for current term.
     *
     * @return current term
     */
    public int getCurrentTerm() {
        return this.currentTerm;
    }

    /**
     * Getter for final work mark.
     *
     * @return final work mark
     */
    public Integer getFinalWorkMark() {
        return this.finalWorkMark;
    }

    /**
     * Subclass for the record in the credit book.
     *
     * @param typeOfCredit - credit or exam
     * @param subject      - subject
     * @param mark         - mark for the credit
     * @param term         - term when the credit is taking
     */
    public record Record(String typeOfCredit, String subject, int mark, int term) {

        /**
         * Class constructor.
         *
         * @param typeOfCredit - credit or exam
         * @param subject      - subject
         * @param mark         - mark for the credit
         * @param term         - term when the credit is taking
         */
        public Record {
            if (!(typeOfCredit.equals("exam") || typeOfCredit.equals("credit"))) {
                throw new IllegalArgumentException("wrong type of credit");
            }
            if (mark < 2 || mark > 5) {
                throw new IllegalArgumentException("wrong mark");
            }
            if (term < 1 || term > 8) {
                throw new IllegalArgumentException("wrong term");
            }
        }
    }
}
