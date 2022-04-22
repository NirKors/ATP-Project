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

    protected void generateStartGoal(Maze3D maze) {
        int depth = maze.getDepthNum(), row = maze.getRowNum(), col = maze.getColNum();
        Position3D start;
        Position3D goal;

        while (true) {
            start = new Position3D((int) (Math.random() * depth), (int) (Math.random() * row), (int) (Math.random() * col));
            goal = new Position3D((int) (Math.random() * depth), (int) (Math.random() * row), (int) (Math.random() * col));
            if (!start.equals(goal))
                break;
        }

        maze.setVal(start, 0);
        maze.setVal(goal, 0);
        maze.setStartPosition(start);
        maze.setGoalPosition(goal);
    }

}


