package simulation.output;

import java.util.List;

/**
 * This class contains a list of ChildOutput, used to print the results of the simulation
 * via ObjectMapper
 */
public final class AnnualChildren {
    private final List<ChildOutput> children;

    public AnnualChildren(final List<ChildOutput> children) {
        this.children = children;
    }

    public List<ChildOutput> getChildren() {
        return children;
    }
}
