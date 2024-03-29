package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Client implements IClientStrategy{

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }


    public void communicateWithServer() {
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * "Default" clientStrategy.
     */
    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        try {
            throw new UnsupportedOperationException("Strategy not overridden.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
