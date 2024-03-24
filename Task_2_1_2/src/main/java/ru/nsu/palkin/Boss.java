package ru.nsu.palkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Boss class.
 */
public class Boss {
    private String host;
    private int port1;
    private int port2;
    private int[] array;
    private int chunks;
    private ServerSocket bossSocket;
    private BlockingQueue<Socket> workers;
    private ArrayList<Socket> closeWorkers;
    private BlockingQueue<Boolean> results;
    private Thread sendTask;
    private Thread receiveResult;

    /**
     * Class constructor.
     *
     * @param host   - ip address
     * @param port1  - broadcast port
     * @param port2  - tcp port
     * @param array  - array of numbers
     * @param chunks - chunks
     */
    public Boss(String host, int port1, int port2, int[] array, int chunks) {
        this.host = host;
        this.port1 = port1;
        this.port2 = port2;
        this.array = array;
        this.chunks = chunks;
        this.workers = new LinkedBlockingDeque<>();
        this.closeWorkers = new ArrayList<>();
        this.results = new LinkedBlockingDeque<>(this.chunks);
    }

    /**
     * Method to split an array into pieces.
     *
     * @return queue of arrays
     */
    private BlockingQueue<int[]> arraySeparator() throws InterruptedException {
        int subArrLen = this.array.length / this.chunks;
        int residual = this.array.length % this.chunks;
        BlockingQueue<int[]> result = new ArrayBlockingQueue<>(this.chunks);
        int previousPos = 0;
        for (int i = 0; i < this.chunks; i++) {
            if (i < residual) {
                result.put(Arrays.copyOfRange(this.array, previousPos, previousPos
                        + subArrLen + 1));
                previousPos = previousPos + subArrLen + 1;
            } else {
                result.put(Arrays.copyOfRange(this.array, previousPos, previousPos + subArrLen));
                previousPos = previousPos + subArrLen;
            }
        }
        return result;
    }

    /**
     * Method to make string from array.
     *
     * @param array - array of numbers
     * @return string
     */
    private String makeString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                result.append(array[i]);
            } else {
                result.append(array[i]).append(" ");
            }
        }
        return new String(result);
    }

    /**
     * Method to send broadcast signal.
     */
    private void sendSignal() throws IOException {
        DatagramSocket signalSenderSocket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(this.host);
        String message = Integer.toString(this.port2);
        byte[] buffer = message.getBytes();
        DatagramPacket pack = new DatagramPacket(buffer, buffer.length, group, this.port1);
        signalSenderSocket.send(pack);
        signalSenderSocket.close();
    }

    /**
     * Method to send task to the worker.
     */
    private void sendTask() throws IOException, InterruptedException {
        this.bossSocket = new ServerSocket(this.port2);
        BlockingQueue<int[]> subArrays = arraySeparator();
        Runnable task = () -> {
            int counter = 0;
            while (counter != this.chunks) {
                try {
                    Socket workerSocket = bossSocket.accept();
                    this.workers.put(workerSocket);
                    this.closeWorkers.add(workerSocket);
                    PrintWriter out = new PrintWriter(workerSocket.getOutputStream(), true);
                    String message = makeString(subArrays.take());
                    out.println(message);
                    counter++;
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        this.sendTask = new Thread(task);
        this.sendTask.start();
    }

    /**
     * Method to get result from the worker.
     */
    private void receiveResult() {
        Runnable task = () -> {
            int counter = 0;
            while (counter != this.chunks) {
                try {
                    Socket workerSocket = this.workers.take();
                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(workerSocket.getInputStream()));
                    String result = in.readLine();
                    if (Objects.equals(result, "true")) {
                        this.results.put(true);
                    } else {
                        this.results.put(false);
                    }
                    counter++;
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        this.receiveResult = new Thread(task);
        this.receiveResult.start();
    }

    /**
     * Main method.
     *
     * @return true or false
     */
    public boolean hasNotPrime() throws IOException, InterruptedException {
        sendSignal();
        sendTask();
        receiveResult();
        this.sendTask.join();
        this.receiveResult.join();

        this.bossSocket.close();
        for (Socket closeWorker : this.closeWorkers) {
            closeWorker.close();
        }
        for (int i = 0; i < this.results.size(); i++) {
            if (this.results.take()) {
                return true;
            }
        }
        return false;
    }
}