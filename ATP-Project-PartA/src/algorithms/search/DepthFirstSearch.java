package algorithms.search;

import java.util.ArrayList;
import java.util.Hashtable;

public class DepthFirstSearch extends ASearchingAlgorithm {
    /** Solves the maze using a DFS algorithm */
    @Override
    public Solution solve(ISearchable domain) {
        Solution v = new Solution();
        AState current_state = domain.getStart();
        ArrayList<AState> S = new ArrayList<>();
        ArrayList<AState> visited = new ArrayList<>();
        /** prevDict is a dictionary where the keys are the states, and the values are the states they came from in the path */
        Hashtable<AState, AState> prevDict = new Hashtable<>();
        S.add(domain.getStart());
        //Here we do the dfs search itself
        while (!S.isEmpty()) {
            current_state = S.get(S.size() - 1);
            if (current_state == domain.getGoal()) {
                break;
            }
            S.remove(S.size() - 1);
            int flag = -1;
            for (AState state : domain.getAllPossibleStates(current_state)) {
                flag++;
                if (flag % 2 != 0)
                    continue;
                if (!(visited.contains(state)) && domain.isIn(state)) {
                    visited.add(state);
                    S.add(state);
                    prevDict.put(state, current_state);
                    nodes_Evaluated++;
                }
            }
        }
        ArrayList<AState> reverser = new ArrayList<>();
        reverser.add(domain.getGoal());
        current_state = domain.getGoal();
        AState prev_state= domain.getStart();
        // We created a new arraylist called reverser, in which we will add
        // new states in the reverse order, meaning we check the goal, from
        // there we go to whatever reached into the goal and so on, until
        // we reach the start position.
        while (!(current_state.equals(domain.getStart()))) {
            reverser.add(prev_state);
            prev_state = prevDict.get(current_state);
            current_state=prev_state;
            System.out.println("checkychecky");
        }
        reverser.add(domain.getStart());
        // now after we have the array, we go from beginning to end on the reverser,
        // and add it to our actual solution.
        while (!reverser.isEmpty()) {
            v.addState(reverser.remove(reverser.size() - 1));
        }
        return v;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
