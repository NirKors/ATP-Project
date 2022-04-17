package algorithms.mazeGenerators;

/** A class representing the position */
public class Position {
    private int row, column;

    /** Position constructor using the current Row and Column */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    /** Returns the row number of this positions */
    public int getRowIndex() {
        return row;
    }
    /** Returns the column number of this positions */
    public int getColumnIndex() {
        return column;
    }
    /** Overloads the toString method on object so that it prints position in a "{row,column}" format */
    public String toString(){
        return "{"+this.getRowIndex()+","+this.getColumnIndex()+"}";
    }
}
