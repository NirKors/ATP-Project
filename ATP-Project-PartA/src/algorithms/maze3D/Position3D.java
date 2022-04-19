package algorithms.maze3D;

public class Position3D {
    private int depth, row, column;

    /**
     * Position constructor using the current Row and Column
     */
    public Position3D(int depth, int row, int column) {
        this.depth = depth;
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row number of this positions
     */
    public int getRowIndex() {
        return row;
    }

    /**
     * Returns the column number of this positions
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     * Returns the depth number of this position
     */
    public int getDepthIndex() {
        return depth;
    }

    /**
     * Overloads the toString method on object so that it prints position in a "{row,column}" format
     */
    public String toString() {
        return "{" + this.getDepthIndex() + "," + this.getRowIndex() + "," + this.getColumnIndex() + "}";
    }
}
