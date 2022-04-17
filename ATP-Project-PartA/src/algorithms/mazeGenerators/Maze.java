package algorithms.mazeGenerators;

public class Maze {

    int[][] maze;

    public Maze(int[][] mz) {
        this.maze = mz;
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getVal(int row, int column){
        return this.getMaze()[row][column];
    }
}
