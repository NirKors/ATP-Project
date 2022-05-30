package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * Every maze is built out of a 2-dimensional array of ints. 1 means it's a wall, 0 means it's a path.
 */
public class Maze {

    private  int[][] maze;
    private Position startPos, goalPos;

    /**
     * Constructor of the maze
     */
    public Maze(int row_size, int column_size) {
        maze = new int[row_size][column_size];
    }

    public Maze(byte[] b) { // TODO: Test if works.
        setSize(b);
        int row = 0, col = 0;
        for (byte cell : b) {
            if (cell == 2) {
                row++;
                col = 0;
                continue;
            }
            switch (cell) {
                case 1 -> maze[row][col] = cell;
                case 3 -> startPos = new Position(row, col);
                case 4 -> goalPos = new Position(row, col);
            }
            col++;
        }
    }


    private void setSize(byte[] b){
        int row_amount = 0, col_amount = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 2) {
                row_amount++;
                if (row_amount == 1)
                    col_amount = i;
            }
        }
        maze = new int[row_amount][col_amount];
    }

    /**
     * Given the row and column numbers, return the current value within that spot. Returns -1 if value is off limits.
     */
    public int getVal(int row, int column) {
        if (row < 0 || column < 0 || row >= this.getRowNum() || column >= this.getColNum())
            return -1;
        return this.maze[row][column];
    }

    public int getVal(Position pos) {
        return getVal(pos.getRowIndex(), pos.getColumnIndex());
    }

    /**
     * Given the row and column numbers or a position, sets the given value into that spot in the maze.
     */
    public void setVal(int row, int column, int val) {
        maze[row][column] = val;
    }

    public void setVal(Position pos, int val) {
        maze[pos.getRowIndex()][pos.getColumnIndex()] = val;
    }

    /**
     * Gets the number of rows
     */
    public int getRowNum() {
        return maze.length;
    }

    /**
     * Gets the number of columns
     */
    public int getColNum() {
        return maze[0].length;
    }

    /**
     * Prints the array to the system
     */
    public void print() {
        //Unable to use the "substring" method without initializing str
        StringBuilder str;
        for (int row = 0; row < this.getRowNum(); row++) {
            str = new StringBuilder("{");
            for (int col = 0; col < this.getColNum(); col++) {
                if (row == startPos.getRowIndex() && col == startPos.getColumnIndex()) {
                    str.append("S,");
                } else if (row == this.getGoalPosition().getRowIndex() && col == this.getGoalPosition().getColumnIndex()) {
                    str.append("E,");
                } else {
                    str.append(getVal(row, col)).append(",");
                }
            }
            str = new StringBuilder(str.substring(0, str.length() - 1));
            str.append("}");
            System.out.println(str);
        }

    }

    /**
     * Gets the starting position
     */
    public Position getStartPosition() {
        return startPos;
    }

    /**
     * Sets the starting position
     */
    public void setStartPosition(Position p) {
        startPos = p;
    }

    /**
     * Gets the goal position
     */
    public Position getGoalPosition() {
        return goalPos;
    }

    /**
     * Sets the goal position
     */
    public void setGoalPosition(Position p) {
        goalPos = p;
    }

    /**
     * Maze will be represented using flags.
     * <p>
     *     Returns byte array containing maze information. 0 - Empty space, 1 - wall, 2 - new row,
     *     3 - start position, 4 - goal position.
     * </p>
     *
     *<pre>
     * Example:
     *     For the maze [[1, E], [0, S]]
     *     return array = [1, 4, 2, 0, 3, 2].
     *</pre>
     * @return byte array.
     */
    public byte[] toByteArray() {
        //TODO can improve by only adding one '2'
        byte[] maze_b = new byte[maze.length * (1 + maze[0].length)];
        int counter = 0;
        for(int[] row : maze) {
            for (int col : row){
                maze_b[counter++] = (byte)col;
            }
            maze_b[counter++] = 2; // new row
        }

        maze_b[startPos.getRowIndex() * (maze[0].length + 1) + startPos.getColumnIndex()] = 3; // start pos
        maze_b[goalPos.getRowIndex() * (maze[0].length + 1) + goalPos.getColumnIndex()] = 4; // goal pos

        return maze_b;
    }




}
