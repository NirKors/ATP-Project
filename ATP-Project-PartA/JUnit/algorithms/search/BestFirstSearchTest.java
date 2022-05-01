package algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void solve() {

    }

    @Test
    /** Test to make sure the naming works correctly */
    void getName() {
        ISearchingAlgorithm Best = new BestFirstSearch();
        assertEquals("BestFirstSearch",Best.getName());
    }
}