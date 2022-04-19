package algorithms.mazeGenerators;

/**
 * A class representing the position
 */
public class Position {
    private int row, column;

    /**
     * Position constructor using the current Row and Column
     */
    public Position(int row, int column) {
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
     * Overloads the toString method on object so that it prints position in a "{row,column}" format
     */
    public String toString() {
        return "{" + this.getRowIndex() + "," + this.getColumnIndex() + "}";
    }

    // To ease out movements and states, we will add functions that give a new position depending on where we want to go
    public Position Up() {
        return new Position(this.getRowIndex() + 1, this.getColumnIndex());
    }

    public Position Down() {
        return new Position(this.getRowIndex() - 1, this.getColumnIndex());
    }

    public Position Left() {
        return new Position(this.getRowIndex(), this.getColumnIndex() - 1);
    }

    public Position Right() {
        return new Position(this.getRowIndex(), this.getColumnIndex() + 1);
    }

    // now we will add horizontal movement
    public Position Up_Right() {
        return new Position(this.getRowIndex() + 1, this.getColumnIndex() + 1);
    }

    public Position Up_Left() {
        return new Position(this.getRowIndex() + 1, this.getColumnIndex() - 1);
    }

    public Position Down_Right() {
        return new Position(this.getRowIndex() - 1, this.getColumnIndex() + 1);
    }

    public Position Down_Left() {
        return new Position(this.getRowIndex() - 1, this.getColumnIndex() - 1);
    }
}
