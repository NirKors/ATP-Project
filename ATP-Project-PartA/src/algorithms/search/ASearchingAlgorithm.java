package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int nodes_Evaluated = 1; // We always examine at least one state (Start state)

    public int getNumberOfNodesEvaluated() {
        return nodes_Evaluated;
    }
}
