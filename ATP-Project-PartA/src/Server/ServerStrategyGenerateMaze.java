package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        int[] clientInfo;
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

            clientInfo = (int[]) fromClient.readObject();
            AMazeGenerator mazeGenerator = GeneratorSelector();
            Maze maze = mazeGenerator.generate(clientInfo[0], clientInfo[1]);
            byte[] maze_b = maze.toByteArray();

            toClient.writeObject(MyCompressorOutputStream.compress(maze_b));

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Usage of Configuration.
     */
    private AMazeGenerator GeneratorSelector() {
        String temp = Configurations.getProp().getProperty("mazeGeneratingAlgorithm");
        return switch (temp) {
            case "MyMazeGenerator" -> new MyMazeGenerator();
            case "EmptyMazeGenerator" -> new EmptyMazeGenerator();
            case "SimpleMazeGenerator" -> new SimpleMazeGenerator();
            default -> throw new RuntimeException("Unrecognized maze generator \"" + temp + "\" in config.properties.");
        };

    }
}