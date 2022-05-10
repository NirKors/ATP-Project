package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {
        // in here, we will make sure that row and col are not null or smaller than 2. In case they are,
        // they will be given default values of 2
        row = (row<2)? 2 : row;
        column = (column<2)? 2 : column;
        System.out.println("Row " + row + " Column " + column);
        Maze maze = new Maze(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                maze.setVal(i, j, 0);
        }
        generateStartGoal(maze);
        return maze;
    }
}