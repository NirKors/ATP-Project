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

    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        // TODO: Unsure of what to do here, technically here we do what the client sends.
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            // TODO: instead of $$$ input what needs to be put
            // toServer.writeObject($$$);
            toServer.flush();
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            // TODO: instead of $$$ input what needs to be put
            // serverResult = $$$;
            fromServer.close();
            toServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
