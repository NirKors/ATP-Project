package algorithms.search;

import java.util.ArrayList;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    @Override
    public Solution solve(ISearchable domain) {
        Solution v = new Solution();
        AState current_state = domain.getStart();
        ArrayList<AState> Q = new ArrayList<>();
        ArrayList<AState> visited = new ArrayList<>();

        Q.add(current_state);
        while (!Q.isEmpty()) {
            current_state = Q.remove(Q.size() - 1);
            v.addState(current_state);

            if (current_state == domain.getGoal())
                return v;

            boolean flag = false;
            for (AState state : domain.getAllPossibleStates(current_state)) {
                if (flag) {
                    flag = false;
                    continue;
                }
                if ((!visited.contains(state)) && domain.isIn(state)) { //TODO Handle walls.
                    visited.add(state);
                    Q.add(state);
                }
                flag = true;
            }
        }
        return v;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

}
