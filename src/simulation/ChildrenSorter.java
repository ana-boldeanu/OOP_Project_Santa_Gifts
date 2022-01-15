package simulation;

import database.Child;
import database.CityScore;
import enums.Cities;

import java.util.*;

public class ChildrenSorter {
    public List<Child> sortChildren(List<Child> childrenList, String distributionStrategy,
                             List<CityScore> cityScores) {
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

    public List<Child> sortById(List<Child> childrenList) {
        childrenList.sort(new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        return childrenList;
    }

    public List<Child> sortByChildScore(List<Child> childrenList) {
        childrenList.sort(new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
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

    public List<Child> sortByCityScore(List<Child> childrenList, List<CityScore> cityScores) {
        // First, sort the Cities
        cityScores.sort(new Comparator<CityScore>() {
            @Override
            public int compare(CityScore o1, CityScore o2) {
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
                public int compare(Child o1, Child o2) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
            });

            sortedChildren.addAll(childrenInThisCity);
        }

        return sortedChildren;
    }
}
