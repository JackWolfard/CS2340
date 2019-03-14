package edu.gatech.cs2340.spacetrader.model;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.lang.Math.*;


/**
 * A Set of Solar Systems
 */

public class Universe {

    /**
     * The actual "Universe" used in the game, a HashMap with the Planets as Keys and their
     *      coordinates as values.
     */

    private HashMap<SolarSystem, int[]> starMap = new HashMap<SolarSystem, int[]>();
    /** the Linked List of created Solar Systems used to add them into the starMap */

    private LinkedList<SolarSystem> sysList = SolarSystem.generateSystem();
    private int[][] distanceArray = new int[sysList.size()][sysList.size()];
    private SolarSystem currentSolarsystem;
    private Planet currentPlanet;

    public SolarSystem getCurrentSolarsystem() {
        return currentSolarsystem;
    }

    public void setCurrentSolarsystem(SolarSystem currentSolarsystem) {
        this.currentSolarsystem = currentSolarsystem;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }
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
        currentSolarsystem = sysList.get(0);
        currentPlanet = sysList.get(0).getPlanet();
    }

    public void generateUniverse() {
        for (int j = 0; j < sysList.size(); j++) {
            Random rand = new Random();
            int[] coord = new int[]{rand.nextInt(151), rand.nextInt(101)};
            SolarSystem solSys = sysList.get(j);
            starMap.put(solSys, coord);
        }
        generateTravelDistances();
    }

    /**
     * Creates Array of All planet Travel Distances
     */
    public void generateTravelDistances() {
        for (int i = 0; i < distanceArray.length; i++) {
            SolarSystem xplanetKey = sysList.get(i);
            int[] xcoord = starMap.get(xplanetKey);
            double x1 = (double) xcoord[0];
            double x2 = (double) xcoord[1];
            for (int j = 0; j < distanceArray.length; j++) {
                SolarSystem yplanetKey = sysList.get(j);
                int[] ycoord = starMap.get(yplanetKey);
                double y1 = (double) ycoord[0];
                double y2 = (double) ycoord[1];

                int distance = (int) Math.floor(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
                distanceArray[i][j] = distance;
            }
        }
    }


    /**
     * Getter for starMap
     * @return a HashMap
     */
    public HashMap<SolarSystem, int[]> getStarMap() {
        return starMap;
    }

    @Override
    public String toString() {
        String backhalf = "";
        Set<SolarSystem> keys = starMap.keySet();
        Iterator<SolarSystem> iterator = keys.iterator();
        while (iterator.hasNext()) {
            SolarSystem ss = iterator.next();
            int[] coord = starMap.get(ss);
            backhalf = backhalf + ss.toString() + " at location: (" + coord[0] + ", " + coord[1] + ").\n";
        }
        return String.format("A universe with the solar systems: \n" + backhalf);
    }
}
