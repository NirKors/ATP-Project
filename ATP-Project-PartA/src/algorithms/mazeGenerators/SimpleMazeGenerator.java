package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * Randomely puts numbers in different places
     */
    @Override
    public Maze generate(int row, int column) {
        // Start by creating a random
        Maze maze = new Maze(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                maze.setVal(i, j, (int) Math.round(Math.random()));
            }
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
        maze.setVal(start_row, start_column, 0);
        maze.setVal(goal_row, goal_column, 0);
        maze.setStartPosition(new Position(start_row, start_column));
        maze.setGoalPosition(new Position(goal_row, goal_column));

        // We generated a maze with a start and end position, with random walls/paths.
        // The below function will make sure that there is at least a single path between these positions.
        int curr_row = start_row, curr_col = start_column;
        while (true) {
            if (curr_row == goal_row && curr_col == goal_column) {
                break;
            }
            // Using math.random, we will decide if to get closer by column or row
            if (Math.random() < 0.5) {
                // Checking by columns
                if (curr_col < goal_column) {
                    maze.setVal(curr_row, ++curr_col, 0);
                }
                if (curr_col > goal_column) {
                    maze.setVal(curr_row, --curr_col, 0);
                }
            } else {
                if (curr_row < goal_row) {
                    maze.setVal(++curr_row, curr_col, 0);
                }
                if (curr_row > goal_row) {
                    maze.setVal(--curr_row, curr_col, 0);
                }
            }
        }

        return maze;
    }
}
