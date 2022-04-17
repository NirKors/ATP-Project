package algorithms.mazeGenerators;


import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int column) {
        //TODO
        Maze maze = new EmptyMazeGenerator().generate(row, column);
        Position cell = new Position((int) (Math.random() * (row - 1) + 1), (int) (Math.random() * (column - 1) + 1));
        Stack<Position> cells = new Stack<Position>();
        cells.push(cell);
        while (!cells.empty()) {
            cell = cells.pop();
            maze.setVal(cell.getRowIndex(), cell.getColumnIndex(), 1);
        }


        return maze;
    }
}
