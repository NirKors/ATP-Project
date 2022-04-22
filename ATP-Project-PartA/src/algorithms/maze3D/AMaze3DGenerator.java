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

        // Random start / goal generation:
        int start_depth = 0, goal_depth = 0, start_row = 0, goal_row = 0, start_column = 0, goal_column = 0;
        int targetStart = 0, targetGoal = 0;
        int depth = maze.getDepthNum(), row = maze.getRowNum(), column = maze.getColNum();
        while ((start_depth == goal_depth && start_row == goal_row && start_column == goal_column) || (targetStart != 0 || targetGoal != 0)) {

            if (Math.random() < 0.5) {
                start_row = (int) (Math.random() * row);
                start_column = Math.random() < 0.5 ? 0 : column - 1;
            } else {
                start_column = (int) (Math.random() * column);
                start_row = Math.random() < 0.5 ? 0 : row - 1;
            }
            start_depth = (int) (Math.random() * depth);

            if (Math.random() < 0.5) {
                goal_row = (int) (Math.random() * row);
                goal_column = Math.random() < 0.5 ? 0 : column - 1;
            } else {
                goal_column = (int) (Math.random() * column);
                goal_row = Math.random() < 0.5 ? 0 : row - 1;
            }
            goal_depth = (int) (Math.random() * depth);
            targetStart = maze.getVal(start_depth, start_row, start_column);
            targetGoal = maze.getVal(start_depth, goal_row, goal_column);
        }
        maze.setVal(start_depth, start_row, start_column, 0);
        maze.setVal(goal_depth, goal_row, goal_column, 0);
        maze.setStartPosition(new Position3D(start_depth, start_row, start_column));
        maze.setGoalPosition(new Position3D(goal_depth, goal_row, goal_column));
    }

}


