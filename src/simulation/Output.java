package simulation;

import java.util.List;

/**
 * Class used to print the results of the Simulations (with Jackson)
 */
public final class Output {
    /**
     * The results of this simulation
     */
    private final List<AnnualChildren> annualChildren;

    public Output(final List<AnnualChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public List<AnnualChildren> getAnnualChildren() {
        return annualChildren;
    }
}
