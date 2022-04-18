package algorithms.mazeGenerators;


public class MyMazeGenerator extends AMazeGenerator {

    private Maze mazeGB;

    //TODO: Current problem is that the maze isn't guaranteed to be solvable. Possibly because the way the holes are formed and then blocked again.
    @Override
    public Maze generate(int row, int col) {
        mazeGB = new EmptyMazeGenerator().generate(row, col);
        System.out.println("\n\n\n***************STARTING MAZE BUILDING\n");

        recursiveBuilder(0, row - 1, 0, col - 1);
        return mazeGB;
    }

    private void recursiveBuilder(int rowBottom, int rowUpper, int colBottom, int colUpper) {
        if (rowUpper - rowBottom < 1 || colUpper - colBottom < 1) return;

        int row = ((int) ((Math.random() * (rowUpper - rowBottom) + rowBottom)));
        int col = ((int) ((Math.random() * (colUpper - colBottom) + colBottom)));

        if (rowUpper - rowBottom > colUpper - colBottom) {
            int[] limit = buildHorizontalWall(row, col);
            int hole = (int) (Math.random() * (limit[1] - limit[0]) + limit[0]);
            mazeGB.setVal(row, hole, 0); // Punches a hole in the wall
            recursiveBuilder(rowBottom, row - 1, colBottom, hole - 1);
            recursiveBuilder(rowBottom, row - 1, hole + 1, colUpper);
            recursiveBuilder(row + 1, rowUpper, colBottom, hole - 1);
            recursiveBuilder(row + 1, rowUpper, hole + 1, colUpper);

        } else {
            int[] limit = buildVerticalWall(row, col);
            int hole = (int) (Math.random() * (limit[1] - limit[0]) + limit[0]);
            mazeGB.setVal(hole, col, 0); // Punches a hole in the wall
            recursiveBuilder(rowBottom, hole - 1, colBottom, col - 1);
            recursiveBuilder(rowBottom, hole - 1, col + 1, colUpper);
            recursiveBuilder(hole + 1, rowUpper, colBottom, col - 1);
            recursiveBuilder(hole + 1, rowUpper, col + 1, colUpper);
        }
    }


    private int[] buildHorizontalWall(int row, int col) {
        int temp = col, upper, bottom;
        while (mazeGB.getVal(row, ++col) == 0)
            mazeGB.setVal(row, col, 1);
        upper = col - 1;
        col = temp + 1;
        while (mazeGB.getVal(row, --col) == 0)
            mazeGB.setVal(row, col, 1);
        bottom = col + 1;
        return new int[]{bottom, upper};
    }

    private int[] buildVerticalWall(int row, int col) {
        int temp = row, upper, bottom;
        while (mazeGB.getVal(++row, col) == 0)
            mazeGB.setVal(row, col, 1);
        upper = row - 1;
        row = temp + 1;
        while (mazeGB.getVal(--row, col) == 0)
            mazeGB.setVal(row, col, 1);
        bottom = row + 1;
        return new int[]{bottom, upper};
    }
}
