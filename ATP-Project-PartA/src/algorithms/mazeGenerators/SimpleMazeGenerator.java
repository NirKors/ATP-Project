package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * Randomely puts numbers in different places
     */
    @Override
    public Maze generate(int row, int column) {
        // Start by creating a random
        // in here, we will make sure that row and col are not null. In case they are, they will be given default
        // values of 2
        row = (row<2)? 2 : row;
        column = (column<2)? 2 : column;
        Maze maze = new Maze(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                maze.setVal(i, j, (int) Math.round(Math.random()));
            }
        }
        generateStartGoal(maze);
        // We generated a maze with a start and end position, with random walls/paths.
        // The below function will make sure that there is at least a single path between these positions.
        int curr_row = maze.getStartPosition().getRowIndex(), curr_col = maze.getStartPosition().getColumnIndex(),
                goal_row = maze.getGoalPosition().getRowIndex(), goal_column = maze.getGoalPosition().getColumnIndex();
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
