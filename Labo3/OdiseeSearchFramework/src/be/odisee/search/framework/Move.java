package be.odisee.search.framework;

public abstract class Move {
    public abstract double doMove(Solution solution);

    public abstract void undoMove(Solution solution);

}
