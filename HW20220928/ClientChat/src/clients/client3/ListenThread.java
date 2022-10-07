package clients.client3;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ListenThread implements Runnable {
    private Socket socket;
    private Scanner scannerNetwork;

    public ListenThread(Socket socket) {
        this.socket = socket;
        try {
            scannerNetwork = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (scannerNetwork.hasNextLine()){
            System.out.println(scannerNetwork.nextLine());
        }
    }
}
