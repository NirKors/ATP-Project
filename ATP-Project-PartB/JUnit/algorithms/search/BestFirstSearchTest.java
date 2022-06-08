package algorithms.search;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch best = new BestFirstSearch();

    @Test
    void solve(){
        Maze artificial_maze = new Maze(4, 4);
        int i, j;
        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++)
                artificial_maze.setVal(i, j, 1);
        j = 0;
        for (i = 0; i < 4; i++) {
            artificial_maze.setVal(i, j, 0);
            if (i != 3)
                artificial_maze.setVal(i, ++j, 0);
        }
        artificial_maze.setStartPosition(new Position(0, 0));
        artificial_maze.setGoalPosition(new Position(3, 3));

        SearchableMaze searchableMaze = new SearchableMaze(artificial_maze);
        ArrayList<AState> sol = solveProblem(searchableMaze, new BestFirstSearch());
        ArrayList<AState> expected = new ArrayList<>();
        for (i = 0; i < 4; i++)
            expected.add(new MazeState(new Position(i, i)));
        assertEquals(expected, sol);

    }



    @Test
    void InvalidInput() {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1, -10);
        assertEquals(2, maze.getColNum());
        assertEquals(2, maze.getRowNum());
    }

    @Test
    void InvalidInput3D() {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(1, -10,0);
        assertEquals(2, maze.getColNum());
        assertEquals(2, maze.getRowNum());
        assertEquals(2, maze.getDepthNum());
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
            int bfs = solveProblem(searchableMaze, new BreadthFirstSearch()).size();
            int dfs = solveProblem(searchableMaze, new DepthFirstSearch()).size();
            int best = solveProblem(searchableMaze, new BestFirstSearch()).size();
            assertFalse(best > dfs || best > bfs);
        }
    }

    @Test
    /**
     * Checks randomly created mazes and solves them while making sure Best's path is equal or shorter in length.
     */
    void IsBest3D(){
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        for (int i = 0; i < 10; i++) {
            Maze3D maze = mg.generate((int)(Math.random() * 18 + 2), (int)(Math.random() * 18 + 2),(int)(Math.random() * 18 + 2));
            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
            int bfs = solveProblem(searchableMaze, new BreadthFirstSearch()).size();
            int dfs = solveProblem(searchableMaze, new DepthFirstSearch()).size();
            int best = solveProblem(searchableMaze, new BestFirstSearch()).size();

            assertFalse(best > dfs || best > bfs);
        }
    }

    private static ArrayList<AState> solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        Solution solution = searcher.solve(domain);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        return solutionPath;
    }

    @Test
    void getName(){
        assertEquals("BestFirstSearch", best.getName());
    }
}