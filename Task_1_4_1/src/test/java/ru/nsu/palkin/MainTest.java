package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void meanMarkTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, 1);
        myCreditBook.addRecord("exam", "math", 5, 1);
        myCreditBook.addRecord("exam", "C", 5, 1);
        myCreditBook.addRecord("credit", "history", 4, 1);
        myCreditBook.addRecord("exam", "english", 5, 1);
        double meanMark = myCreditBook.currentMeanMark();
        assertEquals(meanMark, 4.75);
    }

    @Test
    public void increasedStudentShipTrueTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, 1);
        myCreditBook.addRecord("exam", "math", 5, 1);
        myCreditBook.addRecord("exam", "C", 5, 1);
        myCreditBook.addRecord("credit", "history", 4, 1);
        myCreditBook.addRecord("exam", "english", 5, 1);
        myCreditBook.updateTerm();
        myCreditBook.addRecord("exam", "math", 3, 2);
        myCreditBook.addRecord("credit", "english", 4, 2);
        assertTrue(myCreditBook.increasedStudentShip());
    }

    @Test
    public void increasedStudentShipFalseTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, 1);
        myCreditBook.addRecord("exam", "math", 5, 1);
        myCreditBook.addRecord("exam", "C", 5, 1);
        myCreditBook.addRecord("credit", "history", 3, 1);
        myCreditBook.addRecord("exam", "english", 5, 1);
        myCreditBook.updateTerm();
        myCreditBook.addRecord("exam", "math", 3, 2);
        myCreditBook.addRecord("credit", "english", 4, 2);
        assertFalse(myCreditBook.increasedStudentShip());
    }

    @Test
    public void redCertificateTrueTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, 1);
        myCreditBook.addRecord("exam", "math", 5, 1);
        myCreditBook.addRecord("exam", "C", 5, 1);
        myCreditBook.addRecord("credit", "history", 4, 1);
        myCreditBook.addRecord("exam", "english", 5, 1);
        myCreditBook.setFinalWorkMark(5);
        assertTrue(myCreditBook.redCertificate());
    }

    @Test
    public void redCertificateFalseTest() {
        Student student = new Student("Egor", "Palkin", "Sergeevich");
        CreditBook myCreditBook = new CreditBook(student, 1);
        myCreditBook.addRecord("exam", "math", 4, 1);
        myCreditBook.addRecord("exam", "C", 5, 1);
        myCreditBook.addRecord("credit", "history", 4, 1);
        myCreditBook.addRecord("exam", "english", 5, 1);
        myCreditBook.setFinalWorkMark(5);
        assertFalse(myCreditBook.redCertificate());
    }

    @Test
    public void getFinalWorkMarkTest() {
        Student student = new Student("Egor", "Palkin");
        CreditBook myCreditBook = new CreditBook(student, 1);
        myCreditBook.setFinalWorkMark(3);
        assertEquals(3, myCreditBook.getFinalWorkMark());
    }

    @Test
    public void getCurrentTermTest() {
        Student student = new Student("Egor", "Palkin");
        CreditBook myCreditBook = new CreditBook(student);
        myCreditBook.updateTerm();
        assertEquals(2, myCreditBook.getCurrentTerm());
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

    @Test
    public void wrongInputTest() {
        Student student = new Student("Egor", "Palkin");
        CreditBook myCreditBook = new CreditBook(student, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            myCreditBook.addRecord("exam", "math", 6, 1);
        });
    }
}
