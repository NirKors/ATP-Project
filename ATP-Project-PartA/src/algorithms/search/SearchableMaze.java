package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

public class SearchableMaze implements ISearchable {
    Maze maze;
    private MazeState start, goal;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        start = new MazeState(maze.getStartPosition());
        goal = new MazeState(maze.getGoalPosition());
    }


    @Override
    public MazeState getStart() {
        return start;
    }

    @Override
    public MazeState getGoal() {
        return goal;
    }

    @Override
    public AState[] getAllPossibleStates(AState state) {
        AState[] states = new AState[8];
        Position pos = state.pos;
        states[0] = new MazeState(pos.Up());
        states[1] = new MazeState(pos.Up().Right());
        states[2] = new MazeState(pos.Right());
        states[3] = new MazeState(pos.Down().Right());
        states[4] = new MazeState(pos.Down());
        states[5] = new MazeState(pos.Down().Left());
        states[6] = new MazeState(pos.Left());
        states[7] = new MazeState(pos.Up());
        return states;
    }

    @Override
    public boolean isIn(AState state) {
        int row = state.pos.getRowIndex(), col = state.pos.getColumnIndex();
        if (row < 0 || row >= maze.getRowNum() || col < 0 || col >= maze.getColNum())
            return false;
        return maze.getVal(row, col) == 0;
    }


}
