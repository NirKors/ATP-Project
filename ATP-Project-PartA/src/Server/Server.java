package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private IServerStrategy strategy;
    private int listeningIntervalMS;
    private int port;
    private volatile boolean stop;
    private ExecutorService TP; //stands for ThreadPool

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.TP = Executors.newCachedThreadPool();
    }

    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            // Here is the
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    // This thread will handle the new Client
                    TP.submit(() -> {
                        handleClient(clientSocket);
                    });

                } catch (SocketTimeoutException e){
                    e.printStackTrace();
                }
            }

            serverSocket.close();
            TP.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try{
            strategy.applyStrategy(clientSocket.getInputStream(),clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop(){
        stop = true;
    }
}
