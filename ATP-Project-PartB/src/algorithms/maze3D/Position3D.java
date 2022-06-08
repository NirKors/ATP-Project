package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D extends Position{
    private int depth;

    /**
     * Creates a Position3D with the given parameters.
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

    /** Changes the coordinate values
     *
     * @return new Position3D
     */
    @Override
    public Position3D Up() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex() + 1, this.getColumnIndex());
    }
        /** Changes the coordinate values
     *
     * @return new Position3D
     */
    @Override
    public Position3D Down() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex() - 1, this.getColumnIndex());
    }
        /** Changes the coordinate values
     *
     * @return new Position3D
     */
    @Override
    public Position3D Left() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex(), this.getColumnIndex() - 1);
    }
        /** Changes the coordinate values
     *
     * @return new Position3D
     */
    @Override
    public Position3D Right() {
        return new Position3D(this.getDepthIndex(), this.getRowIndex(), this.getColumnIndex() + 1);
    }
        /** Changes the coordinate values
     *
     * @return new Position3D
     */
    public Position3D Above() {
        return new Position3D(this.getDepthIndex() + 1, this.getRowIndex(), this.getColumnIndex());
    }
        /** Changes the coordinate values
     *
     * @return new Position3D
     */
    public Position3D Below() {
        return new Position3D(this.getDepthIndex() - 1, this.getRowIndex(), this.getColumnIndex());
    }

}
