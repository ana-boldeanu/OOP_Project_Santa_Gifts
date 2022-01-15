package simulation;

import database.Child;
import database.CityScore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class used to sort a childrenList
 */
public final class ChildrenSorter {
    /**
     * Sort the list given as parameter according to the sorting option, then return the sorted list
     */
    public List<Child> sortChildren(final List<Child> childrenList,
                                    final String distributionStrategy,
                                    final List<CityScore> cityScores) {
        switch (distributionStrategy) {
            case "id":
                return sortById(childrenList);

            case "niceScore":
                return sortByChildScore(childrenList);

            case "niceScoreCity":
                return sortByCityScore(childrenList, cityScores);

            default:
                return childrenList;
        }
    }

    /**
     * Sort the list by the ID of the Children, in ascending order (returns the same list, sorted)
     */
    public List<Child> sortById(final List<Child> childrenList) {
        childrenList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        return childrenList;
    }

    /**
     * Sort the list by the averageScore of the Children, in descending order
     * (returns the same list, sorted)
     */
    public List<Child> sortByChildScore(final List<Child> childrenList) {
        childrenList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                if (o1.getAverageScore().equals(o2.getAverageScore())) {
                    // If equal averageScores, the lower ID comes first
                    return Integer.compare(o1.getId(), o2.getId());
                }
                // Sort them by averageScore, from highest to lowest
                return Double.compare(o2.getAverageScore(), o1.getAverageScore());
            }
        });

        return childrenList;
    }

    /**
     * Sort the childrenList by the averageScore of the Cities they live in
     * (builds a new list containing the same Child objects in the new order and returns it)
     */
    public List<Child> sortByCityScore(final List<Child> childrenList,
                                       final List<CityScore> cityScores) {
        // First, sort the Cities
        cityScores.sort(new Comparator<CityScore>() {
            @Override
            public int compare(final CityScore o1, final CityScore o2) {
                if (o1.getAverageScore().equals(o2.getAverageScore())) {
                    // If equal averageScores, sort them by their name
                    return o1.getCityEnum().getValue().compareTo(o2.getCityEnum().getValue());
                }
                // Sort them by their averageScores, from highest to lowest
                return Double.compare(o2.getAverageScore(), o1.getAverageScore());
            }
        });

        // Then, sort the childrenList
        // To do this, break the childrenList into sublists according to City, then concatenate
        // them in the order that Cities appear in cityScores list

        List<Child> sortedChildren = new ArrayList<>();

        for (CityScore city : cityScores) {
            // Build the list of Children in this City
            List<Child> childrenInThisCity = new ArrayList<>();
            for (Child child : childrenList) {
                if (child.getCity().equals(city.getCityEnum().getValue())) {
                    childrenInThisCity.add(child);
                }
            }
            // Sort the Children in this City by their ID
            childrenInThisCity.sort(new Comparator<Child>() {
                @Override
                public int compare(final Child o1, final Child o2) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
            });

            sortedChildren.addAll(childrenInThisCity);
        }

        return sortedChildren;
    }
}
