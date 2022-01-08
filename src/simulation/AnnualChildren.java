package simulation;

import java.util.List;

public final class AnnualChildren {
    private final List<ChildOutput> children;

    public AnnualChildren(final List<ChildOutput> children) {
        this.children = children;
    }

    public List<ChildOutput> getChildren() {
        return children;
    }
}
