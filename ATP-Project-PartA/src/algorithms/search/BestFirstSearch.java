package algorithms.search;

import java.util.ArrayList;

public class BestFirstSearch extends ASearchingAlgorithm {
    @Override
    public Solution solve(ISearchable domain) {
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
                v.setSolution(cleanPath(v));
                return v;
            }

            for (AState state : domain.getAllPossibleStates(current_state)) {
                if ((!visited.contains(state)) && domain.isIn(state)) { // Checks if a valid, unvisited node.
                    if (state.equals(domain.getGoal())) {
                        v.addState(state);
                        v.setSolution(cleanPath(v));
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
    private ArrayList<AState> cleanPath(Solution v) {
        ArrayList<AState> path = v.getSolutionPath();
        int counter = path.size();
        while (counter > 2) {
            AState curr = path.get(--counter);
            AState prev = path.get(counter - 1);
            int diffRow = Math.abs(curr.pos.getRowIndex() - prev.pos.getRowIndex());
            int diffCol = Math.abs(curr.pos.getColumnIndex() - prev.pos.getColumnIndex());
            if (diffRow > 1 || diffCol > 1 || diffRow + diffCol == 2)
                path.remove(prev);

        }

        for (int i = 0; i < path.size() - 2; i++) {
            AState curr = path.get(i);
            AState next = path.get(i + 2);
            int diffRow = Math.abs(curr.pos.getRowIndex() - next.pos.getRowIndex());
            int diffCol = Math.abs(curr.pos.getColumnIndex() - next.pos.getColumnIndex());
            if (diffRow + diffCol == 2)
                path.remove(i + 1);

        }
        return path;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
