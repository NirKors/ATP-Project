package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        long time = System.currentTimeMillis();
        generate(row, column);
        return System.currentTimeMillis() - time;
    }

    /**
     * Generates a random Start position and a random Goal position at the edges where available.
     */
    protected void generateStartGoal(Maze maze) {

        // Random start / goal generation:
        Position start, goal;
        do {
            start = RandomPos(maze);
            goal = RandomPos(maze);
        } while (start.equals(goal) || maze.getVal(start) != 0 || maze.getVal(goal) != 0);
        maze.setStartPosition(start);
        maze.setGoalPosition(goal);
    }

    /**
     * Creates a position on a random wall with the given parameters.
     */
    private Position RandomPos(Maze maze){
        int row, col;
        if (Math.random() < 0.5) {
            row = (int) (Math.random() * maze.getRowNum());
            col = Math.random() < 0.5 ? 0 : maze.getColNum() - 1;
        } else {
            col = (int) (Math.random() * maze.getColNum());
            row = Math.random() < 0.5 ? 0 : maze.getRowNum() - 1;
        }
        return new Position(row, col);
    }

}
