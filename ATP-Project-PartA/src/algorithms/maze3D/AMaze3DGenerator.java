package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long time = System.currentTimeMillis();
        generate(depth, row, column);
        return System.currentTimeMillis() - time;
    }

    protected void generateStartGoal(Maze3D maze) { //TODO: Understand how Start and Goal positions are created.
        int depth = maze.getDepthNum(), row = maze.getRowNum(), col = maze.getColNum();
        Position3D start;
        Position3D goal;

        while (true) {
            start = new Position3D((int) (Math.random() * depth), (int) (Math.random() * row), (int) (Math.random() * col));
            goal = new Position3D((int) (Math.random() * depth), (int) (Math.random() * row), (int) (Math.random() * col));
            if (testPosition(start, maze) && testPosition(goal, maze))
                if (!start.equals(goal))
                    break;
        }

        maze.setVal(start, 0);
        maze.setVal(goal, 0);
        maze.setStartPosition(start);
        maze.setGoalPosition(goal);
    }

    /**
     * Check whether this position has a path to another position.
     * @param pos Current position being checked
     * @param m Maze to check that position
     * @return True if there is a path from this position to another.
     */
    private boolean testPosition(Position3D pos, Maze3D m){
        int depth = pos.getDepthIndex(), row = pos.getRowIndex(), col = pos.getColumnIndex();

        if (depth > 0)
            if (m.getVal(depth - 1, row, col) == 0)
                return true;
        if (depth < m.getDepthNum())
            if (m.getVal(depth + 1, row, col) == 0)
                return true;

        if (row > 0)
            if (m.getVal(depth, row - 1, col) == 0)
                return true;
        if (row < m.getRowNum())
            if (m.getVal(depth, row + 1, col) == 0)
                return true;

        if (col > 0)
            if (m.getVal(depth, row, col - 1) == 0)
                return true;
        if (col < m.getRowNum())
            if (m.getVal(depth, row, col + 1) == 0)
                return true;

        return false;
    }

}


