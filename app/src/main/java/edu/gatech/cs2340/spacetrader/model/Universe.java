package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;


/**
 * A Set of Solar Systems
 */

public class Universe {

    /**
     * The actual "Universe" used in the game, a HashMap with the Planets as Keys and their
     *      coordinates as keys.
     */

    private HashMap<SolarSystem, int[]> starMap;

    /** the Linked List of created Solar Systems used to add them into the starMap */

    private LinkedList<SolarSystem> sysList = SolarSystem.generateSystem();

    /**
     * Creates a Universe containing Solar Systems to be used in the game
     */

    /**
     * generates a Universe based on the Linked List of Solar Systems
     */
    // This Method needs implementation of the x,y location
    // I used the constant of 1 for now just so it would compile
    public Universe() {
        generateUniverse();
    }
    public void generateUniverse() {
        if (sysList == null) {
            System.out.print("System is null");
        }
        for (int j = 0; j < sysList.size(); j++) {
            Random rand = new Random();
            int[] coord = new int[]{rand.nextInt(151), rand.nextInt(101)};
            SolarSystem solSys = sysList.get(j);
            starMap.put(solSys, coord);
        }
    }

    /**
     * Getter for starMap
     * @return a HashMap
     */
    public HashMap<SolarSystem, int[]> getStarMap() {
        return starMap;
    }

    // Not finished yet
    @Override
    public String toString() {
        return String.format("A universe with " + starMap.toString());
    }

}
