package algorithms.mazeGenerators;

public class Maze {

    /** Every maze is built out of a 2-dimensional array of ints. 1 means it's a wall, 0 means it's a path.*/
    int[][] maze;

    public Maze(int[][] mz) {
        this.maze = mz;
    }

    /** Function that gets us the protected array of the class*/
    public int[][] getMaze() {
        return maze;
    }

    public int getVal(int row, int column) {
        return this.getMaze()[row][column];
    }

    public void setVal(int row, int column, int val){
        this.getMaze()[row][column]=val;
    }

    public int getRowNum(){
        return getMaze().length;
    }
    public int getColNum(){
        return getMaze()[0].length;
    }
    public void print(){
        for(int i=0;i<this.get)
        System.out.println(this.getMaze());
    }
}
