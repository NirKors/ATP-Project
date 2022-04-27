package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class AState { //TODO: Add support to 3d positions and abstract. Translate Position(3d) to AState
    Position pos;

    public String toString() {
        return pos.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one, by comparing positions.
     *
     * @param o - Object to compare to 'this'
     * @return boolean - True if equal, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AState))
            return false;
        AState state = (AState) o;

        return pos.equals(state.pos);
    }

    public Position getPos() {
        return pos;
    }
}
