package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {
        Maze maze = new Maze(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                maze.setVal(i, j, 0);
        }
        int start_row = 0, goal_row = 0, start_column = 0, goal_column = 0;


        // Random start / goal generation:
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
        }

        maze.setStartPosition(new Position(start_row, start_column));
        maze.setGoalPosition(new Position(goal_row, goal_column));

        return maze;
    }
}