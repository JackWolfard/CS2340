package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * A Set of Solar Systems
 */

public class Universe {

    /**
     * The actual "Universe" used in the game, a HashMap with the Planets as Keys and their
     *      coordinates as keys.
     */

    private HashMap<SolarSystem,Integer> starMap;

    /** the Linked List of created Solar Systems used to add them into the starMap */

    private LinkedList<SolarSystem> sysList = SolarSystem.generateSystem();

    /**
     * Creates a Universe containing Solar Systems to be used in the game
     */

    public Universe() {
        generateUniverse();
    }

    /**
     * generates a Universe based on the Linked List of Solar Systems
     */

    // This Method needs implementation of the x,y location
    // I used the constant of 1 for now just so it would compile

    public void generateUniverse() {
        for (int j = 0; j < sysList.size(); j++) {
            SolarSystem solSys = sysList.get(j);
            starMap.put(solSys, 1);
        }
    }

}
