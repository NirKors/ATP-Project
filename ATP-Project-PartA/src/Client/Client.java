package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

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
    }

    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {

    }
}
