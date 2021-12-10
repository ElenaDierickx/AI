package be.odisee.search.simulatedannealing;

import be.odisee.search.MyObjectiveFunction;
import be.odisee.search.MySolution;
import be.odisee.search.MySwapTwoRandomCitiesMove;
import be.odisee.search.framework.*;

public class SimulatedAnnealing extends SearchAlgorithm {
    private ObjectiveFunction function;
    private Solution currentSolution;
    private Solution bestSolution;
    private int numberOfCities;
    private double currentResult;
    private double bestResult;

    public SimulatedAnnealing(int numberOfCities) {
        this.function = new MyObjectiveFunction();
        currentSolution = new MySolution(numberOfCities);
        bestSolution = currentSolution;
        bestResult = function.evaluate(bestSolution,null);
    }

    @Override
    public double execute(int numberOfIterations) {
        currentResult = bestResult;
        Move move = new MySwapTwoRandomCitiesMove();
        for (int i = 0; i <= numberOfIterations; i++) {
            if (currentResult < bestResult) {
                bestResult = currentResult;
                bestSolution = (MySolution)currentSolution.clone();
            } else if (Math.exp((bestResult - currentResult) / Math.sqrt(currentResult) / (1.0001 - ((i * 1.0) / numberOfIterations))) < RandomGenerator.random.nextDouble()) {
                move.undoMove(currentSolution);
            }
            currentResult = function.evaluate(currentSolution, move);

            if (i % 10000 == 0) {
                System.out.println("Iteration #" + i + " " + bestResult + " " + currentResult + " " + (1.0001 - ((1.0 * i) / numberOfIterations)));
            }
        }
        System.out.println("bestSolution " + function.evaluate(bestSolution,null) + " " + bestResult);
        return bestResult;
    }


    @Override
    public Solution getBestSolution() {
        return bestSolution;
    }


    @Override
    public Solution getCurrentSolution() {
        return currentSolution;
    }
}
