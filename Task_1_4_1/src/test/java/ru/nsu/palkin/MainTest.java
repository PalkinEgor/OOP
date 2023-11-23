package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void meanMarkTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "C", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "history", Mark.FOUR, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "english", Mark.FIVE, Term.FIRST);
        double meanMark = myCreditBook.currentMeanMark();
        assertEquals(meanMark, 4.75);
    }

    @Test
    public void increasedStudentShipTrueTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "C", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "history", Mark.FOUR, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "english", Mark.FIVE, Term.FIRST);
        myCreditBook.updateTerm();
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.THREE, Term.SECOND);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "english", Mark.FOUR, Term.SECOND);
        assertTrue(myCreditBook.increasedStudentShip());
    }

    @Test
    public void increasedStudentShipFalseTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "C", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "history", Mark.THREE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "english", Mark.FIVE, Term.FIRST);
        myCreditBook.updateTerm();
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.THREE, Term.SECOND);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "english", Mark.FOUR, Term.SECOND);
        assertFalse(myCreditBook.increasedStudentShip());
    }

    @Test
    public void redCertificateTrueTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "C", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "history", Mark.FOUR, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "english", Mark.FIVE, Term.FIRST);
        myCreditBook.setFinalWorkMark(5);
        assertTrue(myCreditBook.redCertificate());
    }

    @Test
    public void redCertificateFalseTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.FOUR, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "C", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "history", Mark.FOUR, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "english", Mark.FIVE, Term.FIRST);
        myCreditBook.setFinalWorkMark(5);
        assertFalse(myCreditBook.redCertificate());
    }

    @Test
    public void redCertificateFalseThreeMarkTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "math", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "C", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.CREDIT, "history", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "english", Mark.FIVE, Term.FIRST);
        myCreditBook.addRecord(TypeOfCredit.EXAM, "OSI", Mark.THREE, Term.FIRST);
        myCreditBook.setFinalWorkMark(5);
        assertFalse(myCreditBook.redCertificate());
    }

    @Test
    public void getFinalWorkMarkTest() {
        Student student = new Student("Egor", "Palkin");
        CreditBook myCreditBook = new CreditBook(student, Term.FIRST);
        myCreditBook.setFinalWorkMark(3);
        assertEquals(3, myCreditBook.getFinalWorkMark());
    }

    @Test
    public void getCurrentTermTest() {
        Student student = new Student("Egor", "Palkin");
        CreditBook myCreditBook = new CreditBook(student);
        myCreditBook.updateTerm();
        assertEquals(Term.SECOND, myCreditBook.getCurrentTerm());
    }

    @Test
    public void getStudentInfoTest() {
        Student student = new Student("Egor", "Palkin");
        CreditBook myCreditBook = new CreditBook(student);
        assertEquals(myCreditBook.getStudentInfo(), "Surname: Palkin; Name: Egor");
    }

    @Test
    public void getNameTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        assertEquals("Egor", student.getName());
    }

    @Test
    public void getSurnameTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        assertEquals("Palkin", student.getSurname());
    }

    @Test
    public void getPatronymicTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        assertEquals("Sergeevich", student.getPatronymic());
    }
}
