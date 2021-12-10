package be.odisee.search;

import be.odisee.search.framework.Solution;

import java.util.List;

public class MySolution implements Solution {

    private List<City> travel;

    public MySolution(int numberOfCities) {
    }

    @Override
    public double getObjectiveValue() {
        return 0;
    }

    @Override
    public void setObjectiveValue(double value) {

    }

    @Override
    public Object clone() {
        return null;
    }

    public List<City> getTravel() {return null;}
}
