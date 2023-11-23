package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class CreditBook.
 */
public class CreditBook {
    private Student student;
    private Term currentTerm;
    private ArrayList<Record> records;
    private Integer finalWorkMark;

    /**
     * Class constructor.
     *
     * @param student     - info about the student
     * @param currentTerm - term of the student
     */
    CreditBook(Student student, Term currentTerm) {
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
        this.currentTerm = Term.FIRST;
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
    public void addRecord(TypeOfCredit typeOfCredit, String subject, Mark mark, Term term) {
        this.records.add(new Record(typeOfCredit, subject, mark, term));
    }

    /**
     * Update value of the current term.
     */
    public void updateTerm() {
        this.currentTerm = this.currentTerm.getTerm(this.currentTerm.getTerm() + 1);
    }

    /**
     * Get current mean mark.
     *
     * @return mean mark.
     */
    public double currentMeanMark() {
        return this.records.stream().mapToInt(item -> item.mark.getMark()).average().orElse(0);
    }

    /**
     * Checking for the increased student ship.
     *
     * @return true or false
     */
    public boolean increasedStudentShip() {
        int len = this.records.size();
        for (Record record : this.records) {
            if ((this.currentTerm.getTerm() - 1) == record.term.getTerm()) {
                if (record.mark.getMark() < 4) {
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
        if (this.records.stream().anyMatch(item -> item.mark.getMark() < 4)) {
            return false;
        }
        Map<String, Optional<Record>> groupedRecords = this.records.stream().collect(Collectors
                .groupingBy(Record::getSubjectMark, Collectors.maxBy((o1, o2) -> Integer
                        .compare(o1.term.getTerm(), o2.term.getTerm()))));
        int excellentMarks = (int) groupedRecords.values().stream().map(Optional::get)
                .map(item -> item.mark.getMark()).filter(item -> item == 5).count();
        int allMarks = groupedRecords.size();
        return (((double) excellentMarks / allMarks) >= 0.75) && (this.finalWorkMark == 5);
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
    public Term getCurrentTerm() {
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
     */
    private static class Record {
        private TypeOfCredit typeOfCredit;
        private String subject;
        private Mark mark;
        private Term term;

        /**
         * Class constructor.
         *
         * @param typeOfCredit - credit or exam
         * @param subject      - subject
         * @param mark         - mark for the credit
         * @param term         - term when the credit is taking
         */
        private Record(TypeOfCredit typeOfCredit, String subject, Mark mark, Term term) {
            this.typeOfCredit = typeOfCredit;
            this.subject = subject;
            this.mark = mark;
            this.term = term;
        }

        /**
         * Method for 'group by' by two fields.
         *
         * @return string containing subject and mark
         */
        public String getSubjectMark() {
            return this.subject + this.mark.getMark();
        }
    }
}