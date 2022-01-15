package distribution_strategies;

public class DistributionStrategyFactory {
    public DistributionStrategy createStrategy(String strategy) {
        return switch (strategy) {
            case "id" -> new IdDistribution();
            case "niceScore" -> new ChildScoreDistribution();
            case "niceScoreCity" -> new CityScoreDistribution();
            default -> null;
        };
    }
}
