package ru.nsu.palkin;

/**
 * Student class.
 */
public class Student {
    private String name;
    private String surname;
    private String patronymic;

    /**
     * Class constructor.
     *
     * @param name       - name
     * @param surname    - surname
     * @param patronymic - patronymic(отчество)
     */
    Student(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    /**
     * Class constructor.
     *
     * @param name    - name
     * @param surname - surname
     */
    Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.patronymic = null;
    }

    /**
     * Getter for the name.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the surname.
     *
     * @return surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Getter for the patronymic.
     *
     * @return patronymic
     */
    public String getPatronymic() {
        return this.patronymic;
    }

    /**
     * Override toString method.
     *
     * @return string with info about the student
     */
    @Override
    public String toString() {
        if (this.patronymic != null) {
            return "Surname: " + this.surname + "; Name: "
                    + this.name + "; Patronymic: " + this.patronymic;
        }
        return "Surname: " + this.surname + "; Name: " + this.name;
    }
}
