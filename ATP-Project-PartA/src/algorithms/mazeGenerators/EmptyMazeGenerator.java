package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {
        Maze maze = new Maze(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                maze.setVal(i, j, 0);
        }
        int srow = 0, scolumn = 0, erow = 0, ecolumn = 0;

        while (erow == srow && scolumn == ecolumn) {
            srow = (int) (Math.random() * row + 1);
            scolumn = (int) (Math.random() * column + 1);
            srow = (int) (Math.random() * row + 1);
            scolumn = (int) (Math.random() * column + 1);
        }

        maze.setStartPosition(new Position(srow, scolumn));
        maze.setGoalPosition(new Position(erow, ecolumn));

        return maze;
    }
}