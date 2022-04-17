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

    //TODO: Documentation and wall passage.
    private void divide(Maze maze, int row_start, int row_size, int column_start, int column_size) {
        if (row_size <= 1 || column_size <= 1) return; // recursion condition

        int wall;
        // Adds a vertical wall.
        if (column_size > row_size) {
            wall = (int) (Math.random() * column_size + column_start);
            for (int row = row_start; row < column_size; row++) {
                maze.setVal(row, wall, 1);
            }
            divide(maze, row_start, row_size - wall, column_start, column_size);
            divide(maze, wall, row_size - wall, column_start, column_size);

        }
        // Adds a horizontal wall.
        else {
            wall = (int) (Math.random() * row_size + row_start);
            for (int column = column_start; column < row_size; column++) {
                maze.setVal(wall, column, 1);
            }
            divide(maze, row_start, row_size, column_start, column_size - wall);
            divide(maze, row_start, row_size, wall, column_size - wall);
        }
    }
}
