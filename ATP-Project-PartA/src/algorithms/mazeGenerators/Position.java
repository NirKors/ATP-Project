package algorithms.mazeGenerators;

/** A class representing the position */
public class Position {
    private int row, column;

    /** Position constructor using the current Row and Column */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getStartPosition(){
        return 0;
    }

    public int getGoalPosition(){
        return 0;
    }
}
