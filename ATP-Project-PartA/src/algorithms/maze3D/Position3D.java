package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

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

    // To ease out movements and states, we will add functions that give a new position depending on where we want to go
    public Position3D Up() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex() + 1, this.getColumnIndex());
    }

    public Position3D Down() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex() - 1, this.getColumnIndex());
    }

    public Position3D Left() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex(), this.getColumnIndex() - 1);
    }

    public Position3D Right() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex(), this.getColumnIndex() + 1);
    }

    public Position3D Above() {
        return new Position3D(this.getDepthIndex() + 1, this.getRowIndex(), this.getColumnIndex());
    }

    public Position3D Below() {
        return new Position3D(this.getDepthIndex() - 1, this.getRowIndex(), this.getColumnIndex());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Position3D))
            return false;

        Position3D p = (Position3D) o;
        return p.getRowIndex() == row && p.getColumnIndex() == column && p.getDepthIndex() == depth;
    }
}
