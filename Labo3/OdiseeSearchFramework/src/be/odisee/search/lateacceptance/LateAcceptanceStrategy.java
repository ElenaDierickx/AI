package be.odisee.search.lateacceptance;

import be.odisee.search.MyObjectiveFunction;
import be.odisee.search.MySolution;
import be.odisee.search.MySwapTwoRandomCitiesMove;
import be.odisee.search.framework.Move;
import be.odisee.search.framework.ObjectiveFunction;
import be.odisee.search.framework.SearchAlgorithm;
import be.odisee.search.framework.Solution;

public class LateAcceptanceStrategy extends SearchAlgorithm {

    private MyObjectiveFunction function;
    private MySolution currentSolution;
    private MySolution bestSolution;
    private int numberOfCities;
    private double currentResult;
    private double bestResult;
    private LAList lateAcceptanceList;
    private final int listLength = 5 ;

    public LateAcceptanceStrategy(int numberOfCities) {
        this.function = new MyObjectiveFunction();
        currentSolution = new MySolution(numberOfCities);
        bestSolution = currentSolution;
        bestResult = function.evaluate(bestSolution,null);
        this.lateAcceptanceList = new LAList(listLength);
    }

    @Override
    public double execute(int numberOfIterations)  {

        //om te beginnen in totaal evalueren
        currentResult = function.evaluate(currentSolution, null);
        this.lateAcceptanceList.fillList(currentResult);
        int teller = 0;
        Move move = new MySwapTwoRandomCitiesMove();
        for (int i = 0; i < numberOfIterations; i++) {
            if (currentResult <= bestResult) {
                bestResult = currentResult;
                lateAcceptanceList.addToBeginOfList(currentResult);
                bestSolution = (MySolution)currentSolution.clone();
            }
            else {
                if (currentResult <= lateAcceptanceList.getLastValueInTheList()) {
                    lateAcceptanceList.addToBeginOfList(currentResult);
                }
                else {
                    lateAcceptanceList.addToBeginOfList((currentResult+bestResult)/2);
                    move.undoMove(currentSolution);
                }
            }
            currentResult =  function.evaluate(currentSolution, move);
            if (i % 100 == 0) {
                System.out.println("Iteration #" + i + " " + bestResult + " " + currentResult);
                for(int j=0;j<this.lateAcceptanceList.getSize();j++){
                    System.out.print(this.lateAcceptanceList.getList()[j] + " ");
                }
                System.out.println();
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
