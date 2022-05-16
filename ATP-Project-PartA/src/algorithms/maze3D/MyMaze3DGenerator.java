package algorithms.maze3D;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    private Maze3D mazeGB;

    // This is an implementation of Randomized depth-first search, altered to fit 3D mazes.
    @Override
    public Maze3D generate(int depth, int row, int col) {
        // Input validation:
        if (depth < 2) depth = 2;
        if (row < 2) row = 2;
        if (col < 2) col = 2;


        mazeGB = createEmpty(depth, row, col);
        Position3D cell = new Position3D(0, 0, 0);
        mazeGB.setVal(cell, 1);
        Stack<Position3D> allCells = new Stack<>();
        allCells.push(cell);
        while (!allCells.isEmpty()) {
            Stack<Position3D> unvisited = unvisited(getNeighbors(cell));
            if (!unvisited.isEmpty()) {
                allCells.push(cell);
                Position3D temp = cell;
                cell = unvisited.elementAt((int) (Math.random() * unvisited.size()));
                connectWall(temp, cell);
            } else if (!allCells.isEmpty())
                cell = allCells.pop();
        }

        generateStartGoal(mazeGB);
        return mazeGB;
    }

    private Maze3D createEmpty(int depth, int row, int col){
        Maze3D maze = new Maze3D(depth, row, col);
        for (int k = 0; k < depth; k++)
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++)
                    maze.setVal(k, i, j, 0);
        }
        return maze;
    }

    /**
     * @param cell given cell
     * @return Array of Positions around the given cell
     */
    private Position3D[] getNeighbors(Position3D cell) {
        Position3D[] neighbors = new Position3D[6];
        int row = cell.getRowIndex(), col = cell.getColumnIndex(), depth = cell.getDepthIndex();
        neighbors[0] = new Position3D(depth, row, col + 2); // Up
        neighbors[1] = new Position3D(depth, row, col - 2); // Down
        neighbors[2] = new Position3D(depth, row + 2, col); // Right
        neighbors[3] = new Position3D(depth, row - 2, col); // Left
        neighbors[4] = new Position3D(depth - 2, row, col); // In
        neighbors[5] = new Position3D(depth + 2, row, col); // Out
        return neighbors;
    }

    /**
     * @param neighbors Array of neighbors to a cell
     * @return Stack of unvisited, valid cells, null if there are none.
     */
    private Stack<Position3D> unvisited(Position3D[] neighbors) {
        Stack<Position3D> unvisited = new Stack<>();

        for (Position3D neighbor : neighbors) {
            int row = neighbor.getRowIndex(), col = neighbor.getColumnIndex(), depth = neighbor.getDepthIndex();
            if (row < 0 || row > mazeGB.getRowNum() || col < 0 || col > mazeGB.getColNum() || depth < 0 || depth > mazeGB.getDepthNum())
                continue;
            if (mazeGB.getVal(depth, row, col) != 0)
                continue;
            unvisited.push(neighbor);
        }
        return unvisited;
    }

    /**
     * Connects 'a' and 'b' with a wall, and changes the value of 'b' to a wall as well.
     */
    private void connectWall(Position3D a, Position3D b) {
        mazeGB.setVal(b, 1);
        int depa = a.getDepthIndex(), depb = b.getDepthIndex(), rowa = a.getRowIndex(), rowb = b.getRowIndex(), cola = a.getColumnIndex(), colb = b.getColumnIndex();
        if (rowa == rowb && depa == depb)
            mazeGB.setVal(depa, rowa, Math.max(cola, colb) - 1, 1);
        else if (cola == colb && depa == depb)
            mazeGB.setVal(depa, Math.max(rowa, rowb) - 1, cola, 1);
        else
            mazeGB.setVal(Math.max(depa, depb) - 1, rowa, cola, 1);
    }
}