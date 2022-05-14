package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch best = new BestFirstSearch();

    @Test
    void solve(){
        int[][] artificial = new int[5][5];

    }


    @Test
    /**
     * Used to check that BestFirstSearch algorithm always returns a path equal or shorter in length compared to other algorithms.
     */
    void IsBest(){
        IMazeGenerator mg = new MyMazeGenerator();
        for (int i = 0; i < 10; i++) {
            Maze maze = mg.generate((int)(Math.random() * 18 + 2), (int)(Math.random() * 18 + 2));
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            int bfs = solveProblem(searchableMaze, new BreadthFirstSearch());
            int dfs = solveProblem(searchableMaze, new DepthFirstSearch());
            int best = solveProblem(searchableMaze, new BestFirstSearch());
            assertFalse(best > dfs || best > bfs);
        }
    }

    private static int solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        Solution solution = searcher.solve(domain);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        return solutionPath.size();
    }

    @Test
    void getName(){
        assertEquals("BestFirstSearch", best.getName());
    }
}