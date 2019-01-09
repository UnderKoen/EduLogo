package nl.edulogo.acslogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Under_Koen on 2019-01-08.
 */
public class Server {
    private static double move_sensitivity = 100;
    private static double rotate_sensitivity = 1;
    private static double pen_amount = 1;

    private int port = 8070;
    private ServerSocket serverSocket;
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    onClientConnect(serverSocket.accept());
                } catch (IOException e) {
                }
            }
        }).start();
        System.out.println("Server started.");
    }

    private void onClientConnect(Socket socket) throws IOException {
        client = socket;
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("Connected");
        System.out.println("Client connected.");
    }

    public void forward(double amount) {
        if (client == null) return;
        out.println("Forward " + (amount / move_sensitivity));
        waitForInput();
    }

    public void back(double amount) {
        if (client == null) return;
        out.println("Back " + (amount / move_sensitivity));
        waitForInput();
    }

    public void left(double amount) {
        if (client == null) return;
        out.println("Left " + (amount / rotate_sensitivity));
        waitForInput();
    }

    public void right(double amount) {
        if (client == null) return;
        out.println("Right " + (amount / rotate_sensitivity));
        waitForInput();
    }

    public void penUp() {
        if (client == null) return;
        out.println("PenUp " + pen_amount);
        waitForInput();
    }

    public void penDown() {
        if (client == null) return;
        out.println("PenUp " + pen_amount);
        waitForInput();
    }

    private void waitForInput() {
        if (client == null) return;
        try {
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
