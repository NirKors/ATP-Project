package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        int[] clientInfo;
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

            clientInfo = (int[]) fromClient.readObject();
            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(clientInfo[0], clientInfo[1]);
            byte[] maze_b = maze.toByteArray();

            toClient.writeObject(MyCompressorOutputStream.compress(maze_b));

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}