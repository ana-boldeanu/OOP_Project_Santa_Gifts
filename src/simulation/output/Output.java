package simulation.output;

import java.util.List;

/**
 * Class used to print the results of the Simulation via ObjectMapper
 */
public final class Output {
    /**
     * The results of this simulation (a list of lists of ChildrenOutput)
     */
    private final List<AnnualChildren> annualChildren;

    public Output(final List<AnnualChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public List<AnnualChildren> getAnnualChildren() {
        return annualChildren;
    }
}
