package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class AState {
    Position pos;

    public String toString() {
        return pos.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AState))
            return false;
        AState state = (AState) o;

        return pos.equals(state.pos);
    }
}
