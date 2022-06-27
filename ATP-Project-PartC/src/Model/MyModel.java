package Model;

import Server.Server;
import Server.IServerStrategy;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;


import java.util.Observable;

public class MyModel extends Observable implements IModel{

    Position curr_position; // TODO: Astate?
    Maze curr_maze;
    Server generator, solver;

    // TODO: Implement server usage (make sure it reads from config file, if needed)


    @Override
    public void generateRandomMaze(int row, int col) {
        MyMazeGenerator gen = new MyMazeGenerator();
        curr_maze = gen.generate(row, col);
    }

    @Override
    public Maze getMaze() {
        return curr_maze;
    }

    @Override
    public void updateCharacterLocation(int direction) {
        Position newpos;

        switch (direction) {
            case 1 -> //Up
                    newpos = curr_position.Up();
            case 2 -> //Down
                    newpos = curr_position.Down();
            case 3 -> //Left
                    newpos = curr_position.Left();
            case 4 -> //Right
                    newpos = curr_position.Right();
            default ->
                throw new IllegalArgumentException("Maybe remove this?"); //TODO
        }

        if (validTraversal(newpos))
            curr_position = newpos;
        else
            throw new IllegalArgumentException("Maybe remove this?"); //TODO

    }

    @Override
    public void connectGenerator(int port, int listeningIntervalMS, IServerStrategy strategy) {
        generator = new Server(port, listeningIntervalMS, strategy);
    }

    @Override
    public void connectSolver(int port, int listeningIntervalMS, IServerStrategy strategy) {
        solver = new Server(port, listeningIntervalMS, strategy);
    }

    private boolean validTraversal(Position pos){
        if (pos.getRowIndex() < 0 || pos.getColumnIndex() < 0 || pos.getRowIndex() >= curr_maze.getRowNum() || pos.getColumnIndex() >= curr_maze.getColNum())
            return false;
        if (curr_maze.getVal(pos) != 0)
            return false;
        return true;
    }
}
