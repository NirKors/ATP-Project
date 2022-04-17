package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        long time = System.currentTimeMillis();
        generate(row, column);
        return System.currentTimeMillis() - time;
    }

}
