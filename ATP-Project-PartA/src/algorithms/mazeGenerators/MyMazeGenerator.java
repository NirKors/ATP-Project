package algorithms.mazeGenerators;

/**
 * We'll be using Recursive division method
 */
public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int column) {

        Maze maze = new EmptyMazeGenerator().generate(row, column);
        divide(maze, 0, row, 0, column);
        return maze;
    }

    /**
     * Recursive division method used to build walls and necessary holes in those walls.
     *
     * @param maze         The maze we'll recursively divide.
     * @param row_start    Starting row position.
     * @param row_size     Row size.
     * @param column_start Starting Column Position.
     * @param column_size  Column size.
     */
    private void divide(Maze maze, int row_start, int row_size, int column_start, int column_size) {
        if (row_size <= 1 || column_size <= 1) return; // recursion condition

        int wall, hole;
        // Adds a vertical wall.
        if (column_size > row_size) {
            wall = (int) (Math.random() * column_size + column_start);
            hole = (int) (Math.random() * row_size + row_start);
            for (int row = row_start; row < column_size; row++) {
                maze.setVal(row, wall, 1);
            }
            maze.setVal(hole, wall, 0);
            divide(maze, row_start, row_size - wall, column_start, column_size);
            divide(maze, wall, row_size - wall, column_start, column_size);

        }
        // Adds a horizontal wall.
        else {
            wall = (int) (Math.random() * row_size + row_start);
            hole = (int) (Math.random() * column_size + column_start);
            for (int column = column_start; column < row_size; column++) {
                maze.setVal(wall, column, 1);
            }
            maze.setVal(wall, hole, 0);
            divide(maze, row_start, row_size, column_start, column_size - wall);
            divide(maze, row_start, row_size, wall, column_size - wall);
        }
    }
}
