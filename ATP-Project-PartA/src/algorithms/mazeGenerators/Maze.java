package algorithms.mazeGenerators;

import java.util.Arrays;

/**
 * Every maze is built out of a 2-dimensional array of ints. 1 means it's a wall, 0 means it's a path.
 */
public class Maze {

    private int[][] maze;
    private Position startPos, goalPos;

    /**
     * Constructor of the maze
     */
    public Maze(int row_size, int column_size) {
        maze = new int[row_size][column_size];
    }

    /** Function that gets us the private array of the class*/
    public int[][] getMaze() {
        return maze;
    }

    /** Given the row and column numbers, return the current value within that spot.*/
    public int getVal(int row, int column) {
        return this.getMaze()[row][column];
    }

    /** Given the row and column numbers, sets the given value into that spot in the maze.*/
    public void setVal(int row, int column, int val){
        this.getMaze()[row][column]=val;
    }

    /** Gets the number of rows*/
    public int getRowNum(){
        return getMaze().length;
    }
    /** Gets the number of columns*/
    public int getColNum(){
        return getMaze()[0].length;
    }
    /** Prints the array to the system*/
    public void print(){
        for(int i=0;i<this.getRowNum()-1;i++)
            System.out.println(Arrays.toString(this.getMaze()[i]));
    }

    public Position getStartPosition() {
        return startPos;
    }

    public Position getGoalPosition() {
        return goalPos;
    }
}
