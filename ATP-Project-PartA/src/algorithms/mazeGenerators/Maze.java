package algorithms.mazeGenerators;

public class Maze {

    int[][] maze;

    public Maze(int[][] mz) {
        this.maze = mz;
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getVal(int row, int column) {
        return this.getMaze()[row][column];
    }

    public void setVal(int row, int column, int val){
        this.getMaze()[row][column]=val;
    }
}
