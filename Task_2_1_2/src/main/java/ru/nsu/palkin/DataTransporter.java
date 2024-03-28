package ru.nsu.palkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Data transporter class.
 */
public class DataTransporter {
    private String host;
    private int broadcastPort;
    private int tcpPort;
    private int chunks;
    private BlockingQueue<String> subArrays;
    private ServerSocket bossSocket;
    private BlockingQueue<Socket> workers;
    private BlockingQueue<Boolean> results;
    private BlockingQueue<String> chunksQueue;
    private ArrayList<Socket> closeWorkers;

    /**
     * Class constructor.
     *
     * @param host          - ip address
     * @param broadcastPort - broadcast port
     * @param tcpPort       - tcp port
     * @param chunks        - chunks
     * @param subArrays     - queue with wtrings
     */
    public DataTransporter(String host, int broadcastPort, int tcpPort,
                           int chunks, BlockingQueue<String> subArrays) {
        this.host = host;
        this.broadcastPort = broadcastPort;
        this.tcpPort = tcpPort;
        this.chunks = chunks;
        this.subArrays = subArrays;
        this.workers = new LinkedBlockingDeque<>();
        this.results = new LinkedBlockingDeque<>(this.chunks);
        this.chunksQueue = new LinkedBlockingDeque<>(this.chunks);
        this.closeWorkers = new ArrayList<>();
    }

    /**
     * Send signal to the workers.
     */
    public void sendSignal() throws IOException {
        DatagramSocket signalSenderSocket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(this.host);
        String message = Integer.toString(this.tcpPort);
        byte[] buffer = message.getBytes();
        DatagramPacket pack = new DatagramPacket(buffer, buffer.length, group, broadcastPort);
        signalSenderSocket.send(pack);
        this.bossSocket = new ServerSocket(this.tcpPort);
        signalSenderSocket.close();
    }

    /**
     * Send tasks to the workers.
     *
     * @return thread
     */
    public Thread sendTask() {
        Runnable task = () -> {
            while (this.results.remainingCapacity() != 0) {
                try {
                    if (!this.subArrays.isEmpty()) {
                        Socket workerSocket = this.bossSocket.accept();
                        this.workers.put(workerSocket);
                        this.closeWorkers.add(workerSocket);
                        PrintWriter out = new PrintWriter(workerSocket.getOutputStream(),
                                true);
                        String message = this.subArrays.take();
                        out.println(message);
                        this.chunksQueue.put(message);
                    }
                } catch (IOException | InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        };
        Thread sendTask = new Thread(task);
        sendTask.start();
        return sendTask;
    }

    /**
     * Get result from workers.
     *
     * @return thread
     */
    public Thread receiveResult() {
        Runnable task = () -> {
            while (this.results.remainingCapacity() != 0) {
                String result = null;
                try {
                    Socket workerSocket = this.workers.take();
                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(workerSocket.getInputStream()));
                    result = in.readLine();
                } catch (InterruptedException | IOException e) {
                    try {
                        String message = this.chunksQueue.take();
                        this.subArrays.put(message);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                    continue;
                }
                try {
                    if (Objects.equals(result, "true")) {
                        this.results.put(true);
                    } else {
                        this.results.put(false);
                    }
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        };
        Thread receiveResult = new Thread(task);
        receiveResult.start();
        return receiveResult;
    }

    /**
     * Get result method.
     *
     * @return true or false
     */
    public boolean getResult() throws InterruptedException {
        for (int i = 0; i < this.results.size(); i++) {
            if (this.results.take()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Close all sockets.
     */
    public void closeWorkers() throws IOException {
        this.bossSocket.close();
        for (Socket closeWorker : this.closeWorkers) {
            closeWorker.close();
        }
    }
}
