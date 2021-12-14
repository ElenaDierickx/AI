package be.odisee.search.hillclimbing;

import be.odisee.search.MyObjectiveFunction;
import be.odisee.search.MySolution;
import be.odisee.search.MySwapTwoRandomCitiesMove;
import be.odisee.search.framework.Move;
import be.odisee.search.framework.ObjectiveFunction;
import be.odisee.search.framework.SearchAlgorithm;
import be.odisee.search.framework.Solution;

public class SteepestDescent extends SearchAlgorithm {

    private MyObjectiveFunction function;
    private MySolution currentSolution;
    private MySolution bestSolution;
    private int numberOfCities;
    private double currentResult;
    private double bestResult;

    public SteepestDescent(int numberOfCities) {
        this.function = new MyObjectiveFunction();
        currentSolution = new MySolution(numberOfCities);
        bestSolution = currentSolution;
        bestResult = function.evaluate(bestSolution, null);
    }

    @Override
    public double execute(int numberOfIterations)  {
        currentResult = bestResult;
        Move move = new MySwapTwoRandomCitiesMove();
        for (int i = 0; i < numberOfIterations; i++) {
            if (currentResult <= bestResult) {
                bestResult = currentResult;
                bestSolution = (MySolution) currentSolution.clone();
                System.out.println(bestResult);
            }
            else {
                move.undoMove(currentSolution);
            }
            currentResult =  function.evaluate(currentSolution, move);
//            if (i % 100 == 0) {
//                System.out.println("Iteration #" + i + " " + bestResult + " " + currentResult);
//            }
        }
        System.out.println("bestSolution " + function.evaluate(bestSolution, null) + " " + bestResult);
        bestSolution.getTravel().forEach(x-> System.out.print(x+" "));
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
