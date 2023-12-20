package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void addTest() {
        Notebook note = new Notebook();
        note.addRecord("first", "some text for first note");
        String expectedText = "some text for first note";
        assertEquals(expectedText, note.getRecordText("first"));
    }

    @Test
    public void removeTest() {
        Notebook note = new Notebook();
        note.addRecord("first", "some text for first note");
        note.addRecord("second", "some text for second note");
        note.removeRecord("first");
        note.removeRecord("second");
        String expectedString = "";
        assertEquals(expectedString, note.printRecords());
    }

    @Test
    public void printRecordsInWindowTest() {
        Notebook note = new Notebook();
        note.addRecord("first", "some text for first note");
        note.addRecord("second", "some text for second note");
        LocalDateTime now = LocalDateTime.now();
        String year = Integer.toString(now.getYear());
        String month = Integer.toString(now.getMonthValue());
        String day = Integer.toString(now.getDayOfMonth());
        String hour = Integer.toString(now.getHour());
        String minute = Integer.toString(now.getMinute());
        String second = Integer.toString(now.getSecond());
        String expectedTime = day + "." + month + "." + year + " " + hour + ":"
                + minute + ":" + second;
        String expectedString = "Name:\nsecond\nTime:\n" + expectedTime
                + "\nText:\nsome text for second note\n";
        assertEquals(expectedString, note.printRecordsInWindow("10.10.2000 10:10:10",
                "10.10.2030 10:10:10", new String[]{"second"}));
    }
}
