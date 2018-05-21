package ui;

/**
 * Created by user on 21.05.2018.
 */
public class Statistics {
    private final int numberOfTrees;
    private final int numberOfSpecies;

    Statistics(int numT, int numS){
        numberOfTrees = numT;
        numberOfSpecies = numS;
    }

    public int getNumberOfTrees() {
        return numberOfTrees;
    }

    public int getNumberOfSpecies() {
        return numberOfSpecies;
    }
}
