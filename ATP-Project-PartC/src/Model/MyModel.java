package Model;

import Server.Server;
import Server.IServerStrategy;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;


import java.util.Observable;

public class MyModel extends Observable implements IModel{

    Position player;
    Maze curr_maze;
    Server generator, solver;

    // TODO: Implement server usage (make sure it reads from config file, if needed)

    @Override
    public void generateRandomMaze(int row, int col) {
        MyMazeGenerator gen = new MyMazeGenerator();
        curr_maze = gen.generate(row, col);
        player = curr_maze.getStartPosition();
    }

    @Override
    public Maze getMaze() {
        return curr_maze;
    }

    @Override
    public Position getPlayer() {
        return player;
    }

    @Override
    public Solution getSolution(int playerx, int playery) {
        Maze copy = curr_maze;
        copy.setStartPosition(new Position(playerx, playery));
        SearchableMaze searchableMaze = new SearchableMaze(copy);
        ISearchingAlgorithm algorithm = new BestFirstSearch();
        Solution solution = algorithm.solve(searchableMaze);
        return solution;
    }

    @Override
    public void updateCharacterLocation(int direction) {
        Position newpos;

        switch (direction) {
            case 1 -> // UP
                    newpos = player.Up();
            case 2 -> // DOWN
                    newpos = player.Down();
            case 3 -> // LEFT
                    newpos = player.Left();
            case 4 -> // RIGHT
                    newpos = player.Right();
            case 5 -> // DOWNLEFT
            {
                newpos = player.Down();
                newpos = newpos.Left();
            }
            case 6 -> // DOWNRIGHT
            {
                newpos = player.Down();
                newpos = newpos.Right();
            }
            case 7 -> // UPLEFT
            {
                newpos = player.Up();
                newpos = newpos.Left();
            }
            case 8 -> // UPRIGHT
            {
                newpos = player.Up();
                newpos = newpos.Right();
            }
            default ->
                throw new UnsupportedOperationException("Unable to move to given direction.");
        }
        if (validTraversal(newpos))
            player = newpos;
        else
            throw new UnsupportedOperationException("Unable to move to given direction.");

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
