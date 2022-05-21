package algorithms.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * Returns a list of possible neighboring states, by order of movement price.
     */
    @Override
    protected AState[] getPossibleStates(AState current_state) {
        ArrayList<AState> origin = new ArrayList<>(Arrays.asList(domain.getAllPossibleStates(current_state)));

        origin.sort(Comparator.comparing(AState::getPrice));
        AState[] buff = new AState[origin.size()];
        for (int i = 0; i < origin.size(); i++) {
            buff[i] = origin.get(i);
        }
        return buff;
    }


    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
