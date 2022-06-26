package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public interface IModel {
    public void generateRandomMaze(int row, int col);
    public Maze getMaze();
    public void updateCharacterLocation(int direction);
//    public Solution solveMaze(Searching);

}
