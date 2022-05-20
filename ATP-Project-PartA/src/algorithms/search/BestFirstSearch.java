package algorithms.search;

import java.util.ArrayList;

public class BestFirstSearch extends BreadthFirstSearch {
    @Override
    public Solution solve(ISearchable domain) {
        this.domain = domain;
        Solution v = new Solution();
        v.setSolution(cleanPath(solve()));
        v.setSolution(cleanBestPath(v));
        return v;
    }

    /**
     * Makes sure Solution v contains a valid path, by removing unnecessary node traversals.
     *
     * @param v - Solution to filter
     * @return Filtered solution
     */
    private ArrayList<AState> cleanBestPath(Solution v) { //TODO need to prefer straight lines or redo best
        ArrayList<AState> path = v.getSolutionPath();

        for (int i = 0; i < path.size() - 2; i++) {
            AState curr = path.get(i);
            AState next = path.get(i + 2);
            int row = Math.abs(curr.getState().getRowIndex() - next.getState().getRowIndex());
            int col = Math.abs(curr.getState().getColumnIndex() - next.getState().getColumnIndex());
            int distance = row + col;
            if (distance == 2 && (row == 2 || col == 2)){ // Able to form straight line between points

            }

        }
        return path;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
