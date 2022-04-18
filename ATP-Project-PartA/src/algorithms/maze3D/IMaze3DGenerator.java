package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

public interface IMaze3DGenerator {
    Maze generate(int depth, int row, int column);

    long measureAlgorithmTimeMillis(int depth, int row, int column);
}
