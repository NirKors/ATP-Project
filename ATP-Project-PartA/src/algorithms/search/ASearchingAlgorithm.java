package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int nodes_Evaluated = 0;

    public int getNumberOfNodesEvaluated() {
        return nodes_Evaluated;
    }
}
