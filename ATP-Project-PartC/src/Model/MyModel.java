package Model;

import Server.Server;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

import java.util.Observable;

public class MyModel extends Observable implements IModel{

    Position curr_position; // TODO: Astate?
    Maze curr_maze;


    // TODO: Implement server usage (make sure it reads from config file, if needed)
    Server server;
    public void connect(Server server){
        this.server = server;
    }
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

    private boolean validTraversal(Position pos){
        if (pos.getRowIndex() < 0 || pos.getColumnIndex() < 0 || pos.getRowIndex() >= curr_maze.getRowNum() || pos.getColumnIndex() >= curr_maze.getColNum())
            return false;
        if (curr_maze.getVal(pos) != 0)
            return false;
        return true;
    }

//    @Override
//    public Solution getSolution() {
//        return null;
//    }
}
