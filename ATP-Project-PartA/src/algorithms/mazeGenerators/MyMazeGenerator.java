package algorithms.mazeGenerators;

/**
 * We'll be using Recursive division method
 */
public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {
        // Begin with the maze's space with no walls. Call this a chamber.
        Maze maze = null;
        // Divide the chamber with a randomly positioned wall (or multiple walls) where each wall
        // contains a randomly positioned passage opening within it.
        int row_d = (int) (Math.random() * row + 1);
        int column_d = (int) (Math.random() * column + 1);
        // Then recursively repeat the process on the sub-chambers until all chambers are minimum sized.

        return maze;
        // This is a test
    }
}
