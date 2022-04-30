package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

public class SearchableMaze3D implements ISearchable {

    Maze3D maze;
    private Maze3DState start, goal;

    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
        start = new Maze3DState(maze.getStartPosition());
        goal = new Maze3DState(maze.getGoalPosition());
    }
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
    public AState[] getAllPossibleStates(AState state) {

        AState[] states = new AState[6];
        Position3D pos = (Position3D) state.getState();
        states[0] = new MazeState(pos.Up());
        states[1] = new MazeState(pos.Right());
        states[2] = new MazeState(pos.Down());
        states[3] = new MazeState(pos.Left());
        states[4] = new MazeState(pos.Above());
        states[5] = new MazeState(pos.Below());
        return states;
    }

    /**
     * @param state Given State
     * @return True if given State is an empty space (0) in the maze, and is within its boundaries.
     */
    @Override
    public boolean isIn(AState state) {
        Position3D pos = (Position3D) state.getState();
        int row = pos.getRowIndex(), col = pos.getColumnIndex(), depth = pos.getDepthIndex();
        if (row < 0 || row >= maze.getRowNum() || col < 0 || col >= maze.getColNum() || depth < 0 || depth >= maze.getDepthNum())
            return false;
        return maze.getVal(depth, row, col) == 0;
    }

    @Override
    public boolean validTraversal(AState curr, AState prev, boolean removeDiagonal) {
        return false; // TODO: Implement.
    }
}
