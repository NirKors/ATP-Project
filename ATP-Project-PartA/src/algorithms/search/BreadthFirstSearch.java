package algorithms.search;

import java.util.ArrayList;


public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected ISearchable domain;

    @Override
    public Solution solve(ISearchable domain) {
        this.domain = domain;
        Solution v = new Solution();
        v.setSolution(cleanPath(solve()));
        return v;
    }

    protected Solution solve() {
        Solution v = new Solution();
        AState current_state = domain.getStart();
        ArrayList<AState> Q = new ArrayList<>();
        ArrayList<AState> visited = new ArrayList<>();

        Q.add(current_state);
        visited.add(current_state);
        while (!Q.isEmpty()) {
            current_state = Q.remove(0);
            v.addState(current_state);

            if (current_state.equals(domain.getGoal())) { // Reached goal.
                return v;
            }

            for (AState state : domain.getAllPossibleStates(current_state)) {
                if ((!visited.contains(state)) && domain.isIn(state)) { // Checks if a valid, unvisited node.
                    if (state.equals(domain.getGoal())) {// Reached goal.
                        nodes_Evaluated++;
                        v.addState(state);
                        return v;
                    }
                    nodes_Evaluated++;
                    visited.add(state);
                    Q.add(state);
                }
            }

        }
        return v;
    }

    /**
     * Makes sure Solution v contains a valid path, by removing unnecessary node traversals.
     *
     * @param v - Solution to filter
     * @return Filtered solution
     */
    protected ArrayList<AState> cleanPath(Solution v) {
        ArrayList<AState> path = v.getSolutionPath();
        int counter = path.size();
        while (counter > 2) {
            AState curr = path.get(--counter);
            AState prev = path.get(counter - 1);

            if (!domain.validTraversal(curr, prev, false)) {
                path.remove(prev);
            }
            else if (!domain.validTraversal(curr, prev, true)) {
                path.remove(prev);
                counter++;
            }
        }
        return path;
    }


    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
