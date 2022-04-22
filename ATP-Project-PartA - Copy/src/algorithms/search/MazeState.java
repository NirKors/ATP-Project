package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {

    /**
     * MazeState constructor.
     *
     * @param pos Position to store as a State.
     */
    public MazeState(Position pos) {
        this.pos = pos;
    }
}
