package algorithms.search;

public interface ISearchable {
    AState getStart();

    AState getGoal();

    AState[] getAllPossibleStates(AState state);

    boolean isIn(AState state);

    boolean validTraversal(AState curr, AState prev, boolean removeDiagonal);
}
