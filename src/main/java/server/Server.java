package server;

import client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static Server server;
    private ServerSocket serverSocket;
    private Socket socket;
    private List<ClientHandler> clients = new ArrayList<>();

    //Initializes the server socket on port 8000.

    private Server() throws IOException {
        serverSocket = new ServerSocket(8000);
    }

    //uses the singleton design pattern and ensure only one instance creates.

    public static Server getInstance() throws IOException {
        return server != null ? server : (server = new Server());
    }

    //Accepts incoming client connections and handles them.

    public void makeSocket() {
        while (!serverSocket.isClosed()) {
            try {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, clients);
                clients.add(clientHandler);
                System.out.println("client socket accepted " + socket.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
