package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

public class SearchableMaze implements ISearchable {
    Maze maze;
    private  MazeState start;
    private  MazeState goal;

    /**
     * SearchableMaze Constructor.
     *
     * @param maze Given maze to store along its starting and goal positions.
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        start = new MazeState(maze.getStartPosition());
        goal = new MazeState(maze.getGoalPosition());
    }


    /**
     * @return Stored starting State.
     */
    @Override
    public MazeState getStart() {
        return start;
    }

    /**
     * @return Stored goal State.
     */
    @Override
    public MazeState getGoal() {
        return goal;
    }

    /**
     * Returns an array of four neighboring States to a given State.
     *
     * @param state Given State.
     * @return array of neighbors to state, starting with its above neighbor, and following clockwise in eight directions in total.
     */
    @Override
    public AState[] getAllPossibleStates(AState state) {
        AState[] states = new AState[8];
        Position pos = state.getState();
        states[0] = new MazeState(pos.Up());
        states[1] = new MazeState(pos.Up().Right());
        states[2] = new MazeState(pos.Right());
        states[3] = new MazeState(pos.Right().Down());
        states[4] = new MazeState(pos.Down());
        states[5] = new MazeState(pos.Down().Left());
        states[6] = new MazeState(pos.Left());
        states[7] = new MazeState(pos.Left().Up());

        states[1].price = 15;
        states[3].price = 15;
        states[5].price = 15;
        states[7].price = 15;

        return states;
    }

    /**
     * @param state Given State
     * @return True if given State is an empty space (0) in the maze, and is within its boundaries.
     */
    @Override
    public boolean isIn(AState state) {
        int row = state.getState().getRowIndex(), col = state.getState().getColumnIndex();
        if (row < 0 || row >= maze.getRowNum() || col < 0 || col >= maze.getColNum()) return false;
        return maze.getVal(row, col) == 0;
    }

    /**
     * Checks if traversal from state prev to state curr is possible.
     *
     * @param removeDiagonal boolean whether to check diagonal movement.
     * @return True if there's a valid traversal, else false.
     */
    @Override
    public boolean validTraversal(AState curr, AState prev, boolean removeDiagonal) {
        int diffRow = Math.abs(curr.getState().getRowIndex() - prev.getState().getRowIndex());
        int diffCol = Math.abs(curr.getState().getColumnIndex() - prev.getState().getColumnIndex());

        return diffRow <= 1 && diffCol <= 1;
    }


}
