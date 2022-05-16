package algorithms.maze3D;

public class Maze3D {
    private  int[][][] maze;
    private Position3D startPos, goalPos;

    public Maze3D(int depth_size, int row_size, int column_size) {
        maze = new int[depth_size][row_size][column_size];
    }

    public int getVal(int depth, int row, int column) {
        if (depth < 0 || row < 0 || column < 0 || row >= this.getRowNum() || column >= this.getColNum() || depth >= this.getDepthNum())
            return -1;
        return this.maze[depth][row][column];
    }

    public int getVal(Position3D pos) {
        return getVal(pos.getDepthIndex(), pos.getRowIndex(), pos.getColumnIndex());
    }

    public int getDepthNum() {
        return maze.length;
    }

    public void setVal(Position3D pos, int val) {
        maze[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()] = val;
    }

    public void setVal(int depth, int row, int column, int val) {
        maze[depth][row][column] = val;
    }

    public int getRowNum() {
        return maze[0].length;
    }

    public int getColNum() {
        return maze[0][0].length;
    }

    public Position3D getStartPosition() {
        return startPos;
    }

    public void setStartPosition(Position3D startPos) {
        this.startPos = startPos;
    }

    public Position3D getGoalPosition() {
        return goalPos;
    }

    public void setGoalPosition(Position3D goalPos) {
        this.goalPos = goalPos;
    }

    public void print() {
        //Unable to use the "substring" method without initializing str
        StringBuilder str;
        for (int depth = 0; depth < this.getDepthNum(); depth++) {
            str = new StringBuilder("{\n");
            for (int row = 0; row < this.getRowNum(); row++) {
                str.append("\t{");
                for (int col = 0; col < this.getColNum(); col++) {
                    if (row == startPos.getRowIndex() && col == startPos.getColumnIndex() && depth == startPos.getDepthIndex()) {
                        str.append("S,");
                    } else if (row == this.getGoalPosition().getRowIndex() && col == this.getGoalPosition().getColumnIndex() && depth == this.getGoalPosition().getDepthIndex()) {
                        str.append("E,");
                    } else {
                        str.append(getVal(depth, row, col)).append(",");
                    }
                }
                str = new StringBuilder(str.substring(0, str.length() - 1));
                str.append("}\n");
            }

            str.append("}");
            System.out.println(str);
        }
    }
}
