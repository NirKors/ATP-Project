package test;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    static int temp;
    public static void main(String[] args) {
        IMazeGenerator mg = new EmptyMazeGenerator();
        Maze maze;
        for (int i = 0; i < 1; i++){
            maze = mg.generate(4, 4);
            maze.print();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            int bfs = temp;
            solveProblem(searchableMaze, new DepthFirstSearch());
            int dfs = temp;
            solveProblem(searchableMaze, new BestFirstSearch());
            int best = temp;
            if (bfs != dfs || best > bfs){
                maze.print();
                throw new RuntimeException("It happened (2D)\nBFS path length: " + bfs + "\nDFS path length: " + dfs + "\nBest path length: " + best);

            }
        }
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        temp = solutionPath.size();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s", i, solutionPath.get(i)));
        }
    }
}