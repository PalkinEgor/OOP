package ru.nsu.palkin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

/**
 * Class Notebook.
 */
public class Notebook {
    private LinkedHashMap<String, Record> allRecords;

    /**
     * Class constructor.
     */
    public Notebook() {
        this.allRecords = new LinkedHashMap<>();
    }

    /**
     * Add record method.
     *
     * @param name - name of the record
     * @param text - text of the record
     */
    public void addRecord(String name, String text) {
        Record current = new Record(name, text);
        this.allRecords.put(name, current);
    }

    /**
     * Remove record method.
     *
     * @param name - name of the record
     */
    public void removeRecord(String name) {
        this.allRecords.remove(name);
    }

    /**
     * Get record's text by name.
     *
     * @param name - name of the record
     * @return text of the record
     */
    public String getRecordText(String name) {
        return this.allRecords.get(name).text;
    }

    /**
     * Print all records method.
     *
     * @return string with info about all records
     */
    public String printRecords() {
        String result = "";
        for (String x : this.allRecords.keySet()) {
            result = result + "Name:\n" + x;
            result = result + "\nTime:\n" + this.allRecords.get(x).toString();
            result = result + "\nText:\n" + this.allRecords.get(x).text + "\n";
        }
        return result;
    }

    /**
     * Print all records in the time interval with special words.
     *
     * @param time1    - lower bound
     * @param time2    - upper bound
     * @param keyWords - special words
     * @return
     */
    public String printRecordsInWindow(String time1, String time2, String[] keyWords) {
        String result = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse(time1, dtf);
        LocalDateTime date2 = LocalDateTime.parse(time2, dtf);
        for (String x : this.allRecords.keySet()) {
            boolean status = true;
            for (String i : keyWords) {
                if (!this.allRecords.get(x).text.contains(i)) {
                    status = false;
                    break;
                }
            }
            if (this.allRecords.get(x).time.isAfter(date1)
                    && this.allRecords.get(x).time.isBefore(date2) && status) {
                result = result + "Name:\n" + x;
                result = result + "\nTime:\n" + this.allRecords.get(x).toString();
                result = result + "\nText:\n" + this.allRecords.get(x).text + "\n";
            }
        }
        return result;
    }

    /**
     * Class record.
     */
    private static class Record {
        private String name;
        private String text;
        private LocalDateTime time;

        /**
         * Class constructor.
         *
         * @param name - record's name
         * @param text - record's text
         */
        private Record(String name, String text) {
            this.name = name;
            this.text = text;
            this.time = LocalDateTime.now();
        }

        /**
         * Override toString method.
         *
         * @return string with time info about record
         */
        @Override
        public String toString() {
            String year = Integer.toString(this.time.getYear());
            String month = Integer.toString(this.time.getMonthValue());
            String day = Integer.toString(this.time.getDayOfMonth());
            String hour = Integer.toString(this.time.getHour());
            String minute = Integer.toString(this.time.getMinute());
            String second = Integer.toString(this.time.getSecond());
            return day + "." + month + "." + year + " " + hour + ":" + minute + ":" + second;
        }
    }
}