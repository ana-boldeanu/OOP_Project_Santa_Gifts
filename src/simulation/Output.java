package simulation;

import java.util.List;

/**
 * Class used to print the results of the Simulations (with Jackson)
 */
public final class Output {
    /**
     * The results of this simulation
     */
    private final List<List<ChildOutput>> results;

    public Output(final List<List<ChildOutput>> results) {
        this.results = results;
    }

    public List<List<ChildOutput>> getResults() {
        return results;
    }
}
