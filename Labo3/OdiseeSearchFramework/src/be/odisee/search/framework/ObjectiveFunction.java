package be.odisee.search.framework;

public abstract class ObjectiveFunction {

    public abstract double evaluate(Solution solution, Move move);
}
