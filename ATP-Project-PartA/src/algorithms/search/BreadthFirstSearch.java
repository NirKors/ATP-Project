package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;


public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected ISearchable domain;

    @Override
    public Solution solve(ISearchable domain) {
        this.domain = domain;
        Solution v = new Solution();
        v.setSolution(cleanPath(solve()));
        return v;
    }

    /**
     * Protected method to be used by inheritance (see: BestFirstSearch)
     * @return Solution to ISearchable domain
     */
    protected ArrayList<AState[]> solve() { //TODO doc
        ArrayList<AState[]> v = new ArrayList<>(); // {parent, current}

        AState current_state = domain.getStart();
        ArrayList<AState> Q = new ArrayList<>();
        ArrayList<AState> visited = new ArrayList<>();
        AState prev = null;

        Q.add(current_state);
        visited.add(current_state);
        while (!Q.isEmpty()) {
            current_state = Q.remove(0);
            v.add(new AState[]{prev, current_state});

            if (current_state.equals(domain.getGoal())) { // Reached goal.
                return v;
            }

            for (AState state : domain.getAllPossibleStates(current_state)) {
                if ((!visited.contains(state)) && domain.isIn(state)) { // Checks if a valid, unvisited node.
                    nodes_Evaluated++;
                    v.add(new AState[]{current_state, state});
                    if (state.equals(domain.getGoal())) {// Reached goal.
                        return v;
                    }
                    visited.add(state);
                    Q.add(state);
                }
            }
            prev = current_state;
        }
        return v;
    }

    /**
     * Makes sure Solution v contains a valid path, by removing unnecessary node traversals.
     *
     * @param v - Solution to filter
     * @return Filtered solution
     */
    protected ArrayList<AState> cleanPath(ArrayList<AState[]> path) {
        AState[] curr = path.get(path.size()-1);
        ArrayList<AState> sol = new ArrayList<>();
        sol.add(curr[1]);
        int counter = path.size() - 1;
        while (curr[0] != null){
            int i = path.size() - 1;
            while (path.get(i)[1] != curr[0])
                i--;
            curr = path.get(i);
            sol.add(curr[1]);
        }
        Collections.reverse(sol);
        return shortenPath(sol);
    }

    private ArrayList<AState> shortenPath(ArrayList<AState> path) {
        int top = path.size() - 1;
        AState curr, prev;
        while (top > 1){
            curr = path.get(top);
            for (int i = 0; i < top - 1; i++) {
                prev = path.get(i);
                if (domain.validTraversal(curr, prev, true)) {
                    int index = i + 1, counter = 0;
                    for (int j = i + 1; j < top; j++) {
                        path.remove(index);
                        counter++;
                    }
                    top -= counter;
                }
            }
            top--;

        }

        return path;
    }



    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
