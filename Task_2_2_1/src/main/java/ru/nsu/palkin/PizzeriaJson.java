package ru.nsu.palkin;

/**
 * PizzeriaJson class.
 */
public class PizzeriaJson {
    private int bakerNumber;
    private int[] bakerSpeed;
    private int courierNumber;
    private int[] courierSpeed;
    private int[] courierCapacity;
    private int vaultCapacity;
    private long workingTime;

    /**
     * Class constructor.
     *
     * @param bakerNumber     - number of bakers
     * @param bakerSpeed      - speed of bakers
     * @param courierNumber   - number of couriers
     * @param courierSpeed    - speed of couriers
     * @param courierCapacity - capacity of couriers
     * @param vaultCapacity   - capacity of vault
     * @param workingTime     - working time
     */
    public PizzeriaJson(int bakerNumber, int[] bakerSpeed, int courierNumber, int[] courierSpeed,
                        int[] courierCapacity, int vaultCapacity, long workingTime) {
        this.bakerNumber = bakerNumber;
        this.bakerSpeed = bakerSpeed;
        this.courierNumber = courierNumber;
        this.courierSpeed = courierSpeed;
        this.courierCapacity = courierCapacity;
        this.vaultCapacity = vaultCapacity;
        this.workingTime = workingTime;
    }

    /**
     * Baker number getter.
     *
     * @return baker number
     */
    public int getBakerNumber() {
        return this.bakerNumber;
    }

    /**
     * Baker speed getter.
     *
     * @return baker speed
     */
    public int[] getBakerSpeed() {
        return this.bakerSpeed;
    }

    /**
     * Courier number getter.
     *
     * @return courier number
     */
    public int getCourierNumber() {
        return this.courierNumber;
    }

    /**
     * Courier speed getter.
     *
     * @return courier speed
     */
    public int[] getCourierSpeed() {
        return this.courierSpeed;
    }

    /**
     * Courier capacity getter.
     *
     * @return courier capacity
     */
    public int[] getCourierCapacity() {
        return this.courierCapacity;
    }

    /**
     * Vault capacity getter.
     *
     * @return vault capacity
     */
    public int getVaultCapacity() {
        return this.vaultCapacity;
    }

    /**
     * Working time getter.
     *
     * @return working time
     */
    public long getWorkingTime() {
        return this.workingTime;
    }
}
