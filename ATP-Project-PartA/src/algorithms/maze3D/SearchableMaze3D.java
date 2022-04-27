package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

public class SearchableMaze3D implements ISearchable {

    Maze3D maze;
    private MazeState start, goal;

    @Override
    public AState getStart() {
        return start;
    }

    @Override
    public AState getGoal() {
        return goal;
    }

    /**
     * Returns a list of possible movements from a given state, including diagonally.
     * @param state
     * @return AState[] of new possible positions.
     */
    @Override
    public AState[] getAllPossibleStates(AState state) { //TODO: Consider different approach
        AState[] states = new AState[25];
    }

    /**
     * @param state Given State
     * @return True if given State is an empty space (0) in the maze, and is within its boundaries.
     */
    @Override
    public boolean isIn(AState state) {

    }
}
