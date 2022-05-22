package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {

    private Server.Strategy.IServerStrategy strategy;
    private int listeningIntervalMS;
    private int port;
    private volatile boolean stop;

    public Server(int port, int listeningIntervalMS, Server.Strategy.IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
    }

    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    // This thread will handle the new Client
                    new Thread(() -> {
                        handleClient(clientSocket);
                    }).start();

                } catch (SocketTimeoutException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        //TODO
    }

    public void stop(){
        stop = true;
    }
}
