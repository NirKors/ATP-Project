package algorithms.mazeGenerators;

import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    private Maze mazeGB;

    // This is an implementation of Randomized depth-first search.
    @Override
    public Maze generate(int row, int col) {
        mazeGB = new EmptyMazeGenerator().generate(row, col);
        Position cell = new Position(0, 0);
        mazeGB.setVal(cell, 1);
        Stack<Position> allCells = new Stack<>();
        allCells.push(cell);
        while (!allCells.isEmpty()) {
            Stack<Position> unvisited = unvisited(getNeighbors(cell));
            if (!unvisited.isEmpty()) {
                allCells.push(cell);
                Position temp = cell;
                cell = unvisited.elementAt((int) (Math.random() * unvisited.size()));
                connectWall(temp, cell);
            } else if (!allCells.isEmpty())
                cell = allCells.pop();
        }

        // Invert the maze
        if (!(row == 2 && col == 2))
            for (int i = 0; i < mazeGB.getRowNum(); i++) {
                for (int j = 0; j < mazeGB.getColNum(); j++) {
                    if (mazeGB.getVal(i, j) == 0)
                        mazeGB.setVal(i, j, 1);
                    else
                        mazeGB.setVal(i, j, 0);
                }
            }
        generateStartGoal(mazeGB);
        return mazeGB;
    }

    /**
     * Connects 'a' and 'b' with a wall, and changes the value of b to a wall as well.
     */
    private void connectWall(Position a, Position b) {
        mazeGB.setVal(b, 1);
        int rowa = a.getRowIndex(), rowb = b.getRowIndex(), cola = a.getColumnIndex(), colb = b.getColumnIndex();
        if (rowa == rowb)
            mazeGB.setVal(rowa, Math.max(cola, colb) - 1, 1);
        else
            mazeGB.setVal(Math.max(rowa, rowb) - 1, cola, 1);
    }

    /**
     * @param neighbors Array of neighbors to a cell
     * @return Stack of unvisited, valid cells, null if there are none.
     */
    private Stack<Position> unvisited(Position[] neighbors) {
        Stack<Position> unvisited = new Stack<>();

        for (Position neighbor : neighbors) {
            int row = neighbor.getRowIndex(), col = neighbor.getColumnIndex();
            if (row < 0 || row > mazeGB.getRowNum() || col < 0 || col > mazeGB.getColNum())
                continue;
            if (mazeGB.getVal(row, col) != 0)
                continue;
            unvisited.push(neighbor);
        }
        return unvisited;
    }

    /**
     * @param cell given cell
     * @return Array of Positions around the given cell
     */
    private Position[] getNeighbors(Position cell) {
        Position[] neighbors = new Position[4];
        int row = cell.getRowIndex(), col = cell.getColumnIndex();
        neighbors[0] = new Position(row, col + 2); // Up
        neighbors[1] = new Position(row, col - 2); // Down
        neighbors[2] = new Position(row + 2, col); // Right
        neighbors[3] = new Position(row - 2, col); // Left
        return neighbors;
    }
}