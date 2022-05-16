package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long time = System.currentTimeMillis();
        generate(depth, row, column);
        return System.currentTimeMillis() - time;
    }

    protected void generateStartGoal(Maze3D maze) { //TODO: Understand how Start and Goal positions are created.
        // Random start / goal generation:
        Position3D start, goal;
        do {
            start = RandomPos(maze);
            goal = RandomPos(maze);
        } while (start.equals(goal) || maze.getVal(start) != 0 || maze.getVal(goal) != 0);
        maze.setStartPosition(start);
        maze.setGoalPosition(goal);
    }

    private Position3D RandomPos(Maze3D maze){
        int depth, row, col;
        Position3D pos;
        while (true){
            switch ((int) (Math.random() * 3)) { // Chooses which axis to use to create a random wall.
                case 0 -> { // Depth
                    depth = Math.random() < 0.5 ? 0 : maze.getDepthNum();
                    row = (int) (Math.random() * maze.getRowNum());
                    col = (int) (Math.random() * maze.getColNum());
                }
                case 1 -> { // Row
                    depth = (int) (Math.random() * maze.getDepthNum());
                    row = Math.random() < 0.5 ? 0 : maze.getRowNum();
                    col = (int) (Math.random() * maze.getColNum());
                }
                default -> { // Col
                    depth = (int) (Math.random() * maze.getDepthNum());
                    row = (int) (Math.random() * maze.getRowNum());
                    col = Math.random() < 0.5 ? 0 : maze.getColNum();
                }
            }
            pos = new Position3D(depth, row, col);
            if (testPosition(pos, maze))
                return pos;

        }

    }

    /**
     * Check whether this position has a path to another position.
     * @param pos Current position being checked
     * @param m Maze to check that position
     * @return True if there is a path from this position to another.
     */
    private boolean testPosition(Position3D pos, Maze3D m){
        int depth = pos.getDepthIndex(), row = pos.getRowIndex(), col = pos.getColumnIndex();

        if (depth > 0)
            if (m.getVal(depth - 1, row, col) == 0)
                return true;
        if (depth < m.getDepthNum())
            if (m.getVal(depth + 1, row, col) == 0)
                return true;

        if (row > 0)
            if (m.getVal(depth, row - 1, col) == 0)
                return true;
        if (row < m.getRowNum())
            if (m.getVal(depth, row + 1, col) == 0)
                return true;

        if (col > 0)
            if (m.getVal(depth, row, col - 1) == 0)
                return true;
        if (col < m.getRowNum())
            return m.getVal(depth, row, col + 1) == 0;

        return false;
    }

}


