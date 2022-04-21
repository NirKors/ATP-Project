package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        long time = System.currentTimeMillis();
        generate(row, column);
        return System.currentTimeMillis() - time;
    }

    /**
     * Generates a random Start position and a random Goal position at the edges where available.
     *
     * @param maze
     */
    protected void generateStartGoal(Maze maze) {

        // Random start / goal generation:

        int start_row = 0, goal_row = 0, start_column = 0, goal_column = 0;
        int targetStart = 0, targetGoal = 0;
        int row = maze.getRowNum(), column = maze.getColNum();
        while (start_row == goal_row && start_column == goal_column) {

            if (Math.random() < 0.5) {
                start_row = (int) (Math.random() * row);
                start_column = Math.random() < 0.5 ? 0 : column - 1;
            } else {
                start_column = (int) (Math.random() * column);
                start_row = Math.random() < 0.5 ? 0 : row - 1;
            }

            if (Math.random() < 0.5) {
                goal_row = (int) (Math.random() * row);
                goal_column = Math.random() < 0.5 ? 0 : column - 1;
            } else {
                goal_column = (int) (Math.random() * column);
                goal_row = Math.random() < 0.5 ? 0 : row - 1;
            }
            targetStart = maze.getVal(start_row, start_column);
            targetGoal = maze.getVal(goal_row, goal_column);
        }
        maze.setVal(start_row, start_column, 0);
        maze.setVal(goal_row, goal_column, 0);
        maze.setStartPosition(new Position(start_row, start_column));
        maze.setGoalPosition(new Position(goal_row, goal_column));
    }

}
