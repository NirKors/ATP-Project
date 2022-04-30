package algorithms.maze3D;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    @Override
    public Maze3D generate(int depth, int row, int column) {
        Maze3D maze = new Maze3D(depth, row, column);
        for (int k = 0; k < depth; k++)
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++)
                    maze.setVal(k, i, j, (int) Math.round(Math.random()));
            }

        generateStartGoal(maze);

        Position3D curr_pos = maze.getStartPosition();
        Position3D goal_pos = maze.getGoalPosition();
        while (true) {
            if (curr_pos.equals(goal_pos))
                return maze;
            int select = (int)(Math.random()*3);
            if (select == 0 && curr_pos.getColumnIndex() != goal_pos.getColumnIndex()) {
                if (curr_pos.getColumnIndex() < goal_pos.getColumnIndex()) {
                    curr_pos = curr_pos.Right();
                }
                if (curr_pos.getColumnIndex() > goal_pos.getColumnIndex()) {
                    curr_pos = curr_pos.Left();
                }
            } else if (select == 1 && curr_pos.getRowIndex() != goal_pos.getRowIndex()) {
                if (curr_pos.getRowIndex() < goal_pos.getRowIndex()) {
                    curr_pos = curr_pos.Up();
                }
                if (curr_pos.getRowIndex() > goal_pos.getRowIndex()) {
                    curr_pos = curr_pos.Down();
                }
            } else {
                if ( curr_pos.getDepthIndex() == goal_pos.getDepthIndex())
                    continue;
                if (curr_pos.getDepthIndex() < goal_pos.getDepthIndex()) {
                    curr_pos = curr_pos.Above();
                }
                if (curr_pos.getDepthIndex() > goal_pos.getDepthIndex()) {
                    curr_pos = curr_pos.Below();
                }
            }

            maze.setVal(curr_pos, 0);
        }
    }
}