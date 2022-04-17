package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {
        int[][] EmpMaze = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                EmpMaze[i][j] = 0;
        }
        return new Maze(EmpMaze); //TODO

    }
}