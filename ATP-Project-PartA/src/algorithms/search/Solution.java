package algorithms.search;

import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> solution;

    public Solution() {
        solution = new ArrayList<>();
    }

    public ArrayList<AState> getSolutionPath() {
        return solution;
    }

    public void addState(AState state) {
        solution.add(state);
    }
}
