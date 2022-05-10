package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch best = new BestFirstSearch();

    @Test
    void solve() throws Exception{
        //TODO: How to test?

    }

    void givenNullArgs() throws Exception{
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(0, 5);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
    }

    @Test
    void getName() throws Exception{
        assertEquals("BestFirstSearch", best.getName());
    }
}