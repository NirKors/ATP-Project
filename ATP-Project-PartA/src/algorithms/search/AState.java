package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class AState {
    protected Position pos;
    int price = 10; // Default price


    @Override
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
        if (o == this) return true;
        if (!(o instanceof AState)) return false;
        AState state = (AState) o;

        return pos.equals(state.pos);
    }

    public Position getState() {
        return pos;
    }

    public int getPrice() {
        return price;
    }
}
