package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Every maze is built out of a 2-dimensional array of ints. 1 means it's a wall, 0 means it's a path.
 */
public class Maze implements Serializable {

    private int[][] maze;
    private Position startPos, goalPos;

    /**
     * Constructor of the maze
     */
    public Maze(int row_size, int column_size) {
        maze = new int[row_size][column_size];
    }

    public Maze(byte[] b) {
        setSize(b); // Calculate size.
        int row, col, counter = 0;
        for (row = 0; row < maze.length; row++) {
            for (col = 0; col < maze[0].length; col++) {
                switch (b[counter]) {
                    case 1 -> maze[row][col] = b[counter];
                    case 3 -> startPos = new Position(row, col);
                    case 4 -> goalPos = new Position(row, col);
                    case 2 -> col--;
                }
                counter++;
            }
        }
    }


    private void setSize(byte[] b) {
        int row_amount, col_amount = 0;
        while (b[col_amount] != 2)
            col_amount++;
        row_amount = (b.length - 1) / (col_amount);
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
     * Returns byte array containing maze information. 0 - Empty space, 1 - wall, 2 - new row,
     * 3 - start position, 4 - goal position.
     * <p>
     * Byte 2 is only used once and the entire size is calculated via its location and the length of the array.
     * </p>
     * <p>
     * Size will always be row length times column length plus one.
     * </p>
     * <pre>
     * Example:
     *     For the maze [[1, E], [0, S]]
     *     return array = [1, 4, 2, 0, 3, 2].
     * </pre>
     *
     * @return byte array.
     */
    public byte[] toByteArray() {
        ArrayList<Byte> maze_b = new ArrayList<>();

        boolean flag = false;
        for (int[] row : maze) {
            for (int col : row) {
                maze_b.add((byte) col);
            }
            if (!flag) {
                maze_b.add((byte) 2);
                flag = true;
            }
        }

        int index = startPos.getRowIndex() * maze[0].length; // Row * column amount
        index = index > 0 ? index + 1 : 0;
        index += startPos.getColumnIndex(); // + col
        maze_b.set(index, (byte) 3);

        index = goalPos.getRowIndex() * maze[0].length; // Row * column amount
        index = index > 0 ? index + 1 : 0;
        index += goalPos.getColumnIndex(); // + col
        maze_b.set(index, (byte) 4);

        byte[] maze = new byte[maze_b.size()];
        for (int i = 0; i < maze_b.size(); i++) {
            maze[i] = maze_b.get(i);
        }
        return maze;
    }


}
