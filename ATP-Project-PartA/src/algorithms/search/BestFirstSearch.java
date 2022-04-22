package algorithms.search;

import java.util.ArrayList;

public class BestFirstSearch extends BreadthFirstSearch {
    @Override
    public Solution solve(ISearchable domain) {
        Solution v = new Solution();
        v.setSolution(cleanPath(solve(domain, false)));
        v.setSolution(cleanBestPath(v));
        return v;
    }

    /**
     * Makes sure Solution v contains a valid path, by removing unnecessary node traversals.
     *
     * @param v - Solution to filter
     * @return Filtered solution
     */
    private ArrayList<AState> cleanBestPath(Solution v) {
        ArrayList<AState> path = v.getSolutionPath();

        for (int i = 0; i < path.size() - 2; i++) {
            AState curr = path.get(i);
            AState next = path.get(i + 2);
            int diffRow = Math.abs(curr.pos.getRowIndex() - next.pos.getRowIndex());
            int diffCol = Math.abs(curr.pos.getColumnIndex() - next.pos.getColumnIndex());
            if (diffRow == 1 && diffCol == 1)
                path.remove(i + 1);

        }
        return path;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
