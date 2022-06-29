package Model;

import Server.IServerStrategy;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface IModel {

    public void generateRandomMaze(int row, int col);
    public Maze getMaze();
    public void updateCharacterLocation(int direction);

    public void connectSolver(int port, int listeningIntervalMS, IServerStrategy strategy);
    public void connectGenerator(int port, int listeningIntervalMS, IServerStrategy strategy);
    Position getPlayer();

    Solution getSolution();
}
