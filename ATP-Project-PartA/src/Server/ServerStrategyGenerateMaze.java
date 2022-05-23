package Server;

import IO.MyCompressorOutputStream;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        // TODO are valid input checks required?
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
        BufferedWriter toClient = new BufferedWriter(new PrintWriter(outToClient));

        try{
            int row = fromClient.read();
            int col = fromClient.read();
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(row, col);
//            MyCompressorOutputStream compressed = new MyCompressorOutputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
