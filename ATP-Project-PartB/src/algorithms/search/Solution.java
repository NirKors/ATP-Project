package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {

    private ArrayList<AState> solution;

    public Solution() {
        solution = new ArrayList<>();
    }

    /**
     * getter method.
     *
     * @return ArrayList<AState>
     */
    public ArrayList<AState> getSolutionPath() {
        return solution;
    }

    /**
     * setter method.
     */
    public void setSolution(ArrayList<AState> solution) {
        this.solution = solution;
    }

    /**
     * Appends a state to the stored ArrayList<AState>
     */
    public void addState(AState state) {
        solution.add(state);
    }
}
