package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<ClientListener> clientListeners = new ArrayList<>();
    private List<String> lastMessages = new ArrayList<>();

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(55555);
            System.out.println("Server created");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected " + socket.getPort());

                ClientListener clientListener = new ClientListener(socket, this);
                new Thread(clientListener).start();

                lastMessages.forEach(clientListener::sendMessage);

                clientListeners.add(clientListener);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessageToOll(String message) {
        addMessageToList(message);
        clientListeners.forEach(clientListener -> clientListener.sendMessage(message));
    }

    public void addMessageToList(String message){
        lastMessages.add(message);
        if(lastMessages.size() > 10){
            lastMessages = lastMessages.subList(1, 11);
        }
    }
}
