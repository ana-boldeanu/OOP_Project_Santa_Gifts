package simulation;

import database.AnnualChange;
import simulation.output.AnnualChildren;

import java.util.ArrayList;
import java.util.List;

public final class Simulation {
    /**
     * The number of years for which we will run the simulation
     */
    private final int numberOfYears;
    /**
     * A round from the simulation
     */
    private final Round round;
    /**
     * The results of this simulation
     */
    private final List<AnnualChildren> results = new ArrayList<>();

    public Simulation(final int numberOfYears, final Round round) {
        this.numberOfYears = numberOfYears;
        this.round = round;
    }

    /**
     * Run a simulation of (numberOfYears + 1) rounds and save the results in "results" field
     */
    public void run() {
        // Round 0
        round.computeAverageScores();
        round.computeCityScores();
        round.computeBudgets();
        round.distributeGifts("id");
        results.add(round.getResults());

        // The rest of the rounds
        int currYear = 1;
        while (currYear <= numberOfYears) {
            AnnualChange annualChange =
                    round.getDatabase().getAnnualChangesList().get(currYear - 1);
            round.updateRound(annualChange);
            round.computeAverageScores();
            round.computeCityScores();
            round.computeBudgets();
            round.distributeGifts(annualChange.getDistributionStrategy());
            results.add(round.getResults());
            currYear++;
        }
    }

    public List<AnnualChildren> getResults() {
        return results;
    }
}
