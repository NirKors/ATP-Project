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
                start_row = (int) (Math.random() * row + 1);
                start_column = Math.random() < 0.5 ? 0 : column;
            } else {
                start_column = (int) (Math.random() * column + 1);
                start_row = Math.random() < 0.5 ? 0 : row;
            }

            if (Math.random() < 0.5) {
                goal_row = (int) (Math.random() * row + 1);
                goal_column = Math.random() < 0.5 ? 0 : column;
            } else {
                goal_column = (int) (Math.random() * column + 1);
                goal_row = Math.random() < 0.5 ? 0 : row;
            }
        }

        maze.setStartPosition(new Position(start_row, start_column));
        maze.setGoalPosition(new Position(goal_row, goal_column));

        return maze;
    }
}