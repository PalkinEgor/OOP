package ru.nsu.palkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

/**
 * Worker class.
 */
public class Worker {
    private String host;
    private int port1;
    private int port2;

    /**
     * Class constructor.
     *
     * @param host  - ip address
     * @param port1 - broadcast port
     */
    public Worker(String host, int port1) {
        this.host = host;
        this.port1 = port1;
    }

    /**
     * Method to get broadcast signal.
     */
    public void receiveSignal() throws IOException {
        MulticastSocket signalReceiverSocket = new MulticastSocket(this.port1);
        InetAddress group = InetAddress.getByName(this.host);
        signalReceiverSocket.joinGroup(group);
        byte[] buffer = new byte[1024];
        DatagramPacket pack = new DatagramPacket(buffer, buffer.length);
        signalReceiverSocket.receive(pack);
        String message = new String(pack.getData(), 0, pack.getLength());
        this.port2 = Integer.parseInt(message);
        signalReceiverSocket.leaveGroup(group);
        signalReceiverSocket.close();
    }

    /**
     * Main method.
     */
    public void calculateTask(boolean interrupt) {
        while (true) {
            try {
                Socket workerSocket = new Socket("localhost", this.port2);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(workerSocket.getInputStream()));
                PrintWriter out = new PrintWriter(workerSocket.getOutputStream(), true);
                if (interrupt) {
                    workerSocket.close();
                    break;
                }
                String bossMessage = in.readLine();
                int[] array = DataPreprocessor.makeArray(bossMessage);
                DataCalculator calculator = new DataCalculator(array);
                boolean result = calculator.getResult();
                if (result) {
                    out.println("true");
                } else {
                    out.println("false");
                }
                in.close();
                out.close();
                workerSocket.close();
            } catch (IOException e) {
                break;
            }
        }
    }
}