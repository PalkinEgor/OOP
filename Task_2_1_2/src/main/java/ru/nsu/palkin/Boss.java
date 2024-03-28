package ru.nsu.palkin;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Boss class.
 */
public class Boss {
    private String host;
    private int broadcastPort;
    private int tcpPort;
    private int[] array;
    private int chunks;

    /**
     * Class constructor.
     *
     * @param host          - ip address
     * @param broadcastPort - broadcast port
     * @param tcpPort       - tcp port
     * @param array         - array
     * @param chunks        - chunks
     */
    public Boss(String host, int broadcastPort, int tcpPort, int[] array, int chunks) {
        this.host = host;
        this.broadcastPort = broadcastPort;
        this.tcpPort = tcpPort;
        this.array = array;
        this.chunks = chunks;
    }

    /**
     * Main method.
     *
     * @return true or false
     */
    public boolean hasNotPrime() throws IOException, InterruptedException {
        BlockingQueue<String> subArrays = DataPreprocessor.arraySeparator(this.array, this.chunks);
        DataTransporter transporter = new DataTransporter(this.host, this.broadcastPort,
                this.tcpPort, this.chunks, subArrays);
        transporter.sendSignal();
        Thread sendTaskThread = transporter.sendTask();
        Thread receiveResult = transporter.receiveResult();
        sendTaskThread.join();
        receiveResult.join();
        transporter.closeWorkers();
        return transporter.getResult();
    }
}