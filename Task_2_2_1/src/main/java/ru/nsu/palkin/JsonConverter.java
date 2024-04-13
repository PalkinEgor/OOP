package ru.nsu.palkin;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Json converter class.
 */
public class JsonConverter {
    private PizzeriaJson pizzeriaJson = null;
    private String fileName;

    /**
     * Class constructor.
     *
     * @param pizzeriaJson - pizzeriaJson object
     * @param fileName     - file name
     */
    public JsonConverter(PizzeriaJson pizzeriaJson, String fileName) {
        this.pizzeriaJson = pizzeriaJson;
        this.fileName = fileName;
    }

    /**
     * Serialization method.
     */
    public void serialization() {
        Gson gson = new Gson();
        String result = gson.toJson(this.pizzeriaJson);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while writing: " + e.getMessage());
        }
    }

    /**
     * Deserialization method.
     *
     * @return pizzeria object
     */
    public Pizzeria deserialization() {
        Gson gson = new Gson();
        String json = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            json = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error while reading: " + e.getMessage());
        }
        PizzeriaJson result = gson.fromJson(json, PizzeriaJson.class);
        return new Pizzeria(result.getBakerNumber(), result.getBakerSpeed(),
                result.getCourierNumber(), result.getCourierCapacity(),
                result.getCourierSpeed(), result.getVaultCapacity(), result.getWorkingTime());
    }
}
