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
    void solve() throws Exception{
        //TODO: How to test?

    }

    @Test
    void givenLessThenTwo() throws Exception{
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1, -10);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s", i, solutionPath.get(i)));
        }

    }

    @Test
    void IsBest(){
        IMazeGenerator mg = new MyMazeGenerator();
        for (int i = 0; i < 10; i++) {
            Maze maze = mg.generate((int)Math.random() * 18 + 2, (int)Math.random() * 18 + 2);
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
    void getName() throws Exception{
        assertEquals("BestFirstSearch", best.getName());
    }
}