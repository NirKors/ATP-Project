package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D extends Position{
    private int depth;

    /**
     * Creates a Position3D with the given parameters.
     * @param depth
     * @param row
     * @param column
     */
    public Position3D(int depth, int row, int column) {
        super(row, column);
        this.depth = depth;
    }


    /**
     * Returns the depth number of this position
     */
    public int getDepthIndex() {
        return depth;
    }

    /**
     * Overrides the toString method on object so that it prints position in a "{depth, row, column}" format
     */
    @Override
    public String toString() {
        return "{" + this.getDepthIndex() + "," + this.getRowIndex() + "," + this.getColumnIndex() + "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o.getClass() == this.getClass()))
            return false;

        Position3D p = (Position3D) o;
        return p.getRowIndex() == this.getRowIndex() && p.getColumnIndex() == this.getColumnIndex() && p.getDepthIndex() == depth;
    }
}
