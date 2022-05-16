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
        String str;
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
