package be.odisee.search;

import be.odisee.search.framework.SearchAlgorithm;
import be.odisee.search.hillclimbing.SteepestDescent;

public class Main {
    public static void main(String[] args) {
        SearchAlgorithm steepestDescent = new SteepestDescent(10);
        steepestDescent.execute(1000);
        MySolution bestSolution = (MySolution) steepestDescent.getBestSolution();
        System.out.println();
        for(int i=0;i<bestSolution.getTravel().size();i++){
            System.out.println(bestSolution.getTravel().get(i).getX()+ "\t" + bestSolution.getTravel().get(i).getY());
        }
        System.out.println(bestSolution.getObjectiveValue());
    }
}
