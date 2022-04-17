package algorithms.mazeGenerators;

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

    /**
     * Function that gets us the array of the class
     */
    public int[][] getMaze() {
        return maze;
    }

    /**
     * Given the row and column numbers, return the current value within that spot.
     */
    public int getVal(int row, int column) {
        return this.getMaze()[row][column];
    }

    /**
     * Given the row and column numbers, sets the given value into that spot in the maze.
     */
    public void setVal(int row, int column, int val) {
        this.getMaze()[row][column] = val;
    }

    /**
     * Gets the number of rows
     */
    public int getRowNum() {
        return getMaze().length;
    }

    /**
     * Gets the number of columns
     */
    public int getColNum() {
        return getMaze()[0].length;
    }

    /**
     * Prints the array to the system
     */
    public void print() {
        //Unable to use the "substring" method without initializing str
        String str = null;
        for (int row = 0; row < this.getRowNum(); row++) {
            str = "{";
            for (int col = 0; col < this.getColNum(); col++) {
                if (row == startPos.getRowIndex() && col == startPos.getColumnIndex()) {
                    str += "S,";
                } else if (row == this.getGoalPosition().getRowIndex() && col == this.getGoalPosition().getColumnIndex()) {
                    str += "E,";
                } else {
                    str += getVal(row, col) + ",";
                }
            }
            str = str.substring(0, str.length() - 1);
            str += "}";
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
     * Gets the goal position
     */
    public Position getGoalPosition() {
        return goalPos;
    }

    /**
     * Sets the starting position
     */
    public void setStartPosition(Position p) {
        startPos = p;
    }

    /**
     * Sets the goal position
     */
    public void setGoalPosition(Position p) {
        goalPos = p;
    }
}
