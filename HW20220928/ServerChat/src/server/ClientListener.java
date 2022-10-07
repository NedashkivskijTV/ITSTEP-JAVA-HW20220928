package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener implements Runnable {
    private Socket socket;
    private Scanner scannerNetwork;
    private PrintWriter printWriter;
    private Server server;

    public ClientListener(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;

        try {
            scannerNetwork = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (scannerNetwork.hasNextLine()){
            String message = scannerNetwork.nextLine();
            System.out.println(message);

            // відправка повідомлення усім клієнтам
            server.sendMessageToOll(message);
        }
    }

    public void sendMessage(String message){
        printWriter.println(message);
        printWriter.flush();
    }

}
