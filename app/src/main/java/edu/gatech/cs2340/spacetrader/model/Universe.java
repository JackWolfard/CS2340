package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.model.Ship;
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
    /** Hashmap that is used for the travel UI*/
    private HashMap<SolarSystem, Integer> travelMap = new HashMap<SolarSystem, Integer>();
    private int[][] distanceArray = new int[sysList.size()][sysList.size()];
    private SolarSystem currentSolarsystem;
    private Planet currentPlanet;
    private Random randomEvent = new Random();
    private Pirate[] pirateList;
    private Trader[] traderList;
    private Pirate currentPirate = null;
    private Trader currentTrader = null;

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
        generatePirateList(pirateList);
        generateTraderList(traderList);

    }

    /**
     * updates a hashmap to contain all the planets that can be reached from current planet.
     * @param ship with specific remaining fuel left
     * @return hashmap of reachable planets
     */
    public HashMap<SolarSystem, Integer> aboutToTravel(Ship ship) {
        travelMap.clear();
        int index = sysList.indexOf(currentSolarsystem);
        for (int i=0; i<sysList.size(); i++) {
            int distance = distanceArray[index][i];
            if (distance <= ship.getCurrentMileage() && distance > 0) {
                travelMap.put(sysList.get(i),distance);
            }
        }
        return travelMap;
    }

    /**
     * Travels to new planet
     * Sets ships proper fuel tank
     * Sets current SolarSystem/planet
     * @param solarSystem is new solar system to travel to
     * @param ship users current ship
     */
    public void travel(SolarSystem solarSystem, Ship ship) {
        int distance = travelMap.get(solarSystem);

        int eventNum = randomEvent.nextInt(4);
        if (eventNum == 0) {
            int pirateNum = randomEvent.nextInt(10);
            currentPirate = pirateList[pirateNum];

        } else if (eventNum == 1) {
            int traderNum = randomEvent.nextInt(8);
            currentTrader = traderList[traderNum];
        }

        ship.setCurrentMileage(ship.getCurrentMileage() - distance);
        currentSolarsystem = solarSystem;
        currentPlanet = solarSystem.getPlanet();
    }
    /**
     * Creates Array of All planet Travel Distances
     * X is the planet you are on
     * Y is the distance to that planet
     */
    public void generateTravelDistances() {
        for (int i = 0; i < distanceArray.length; i++) {
            SolarSystem xplanetKey = sysList.get(i);
            int[] xcoord = starMap.get(xplanetKey);
            double x1 = (double) xcoord[0];
            double y1 = (double) xcoord[1];
            for (int j = 0; j < distanceArray.length; j++) {
                SolarSystem yplanetKey = sysList.get(j);
                int[] ycoord = starMap.get(yplanetKey);
                double x2 = (double) ycoord[0];
                double y2 = (double) ycoord[1];

                int distance = (int) Math.floor(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
                distanceArray[i][j] = distance;
            }
        }
    }

    private void generatePirateList(Pirate[] list) {
        Pirate pirate1 = new Pirate("Bubblebeard", 10);
        Pirate pirate2 = new Pirate("'Crazy Eyes' Buxton", 20);
        Pirate pirate3 = new Pirate("Unwin Lexx", 30);
        Pirate pirate4 = new Pirate("Abigail 'Four-Teeth' Granger", 50);
        Pirate pirate5 = new Pirate("Sewell 'The Cook' Shearman", 50);
        Pirate pirate6 = new Pirate("'Big Boi' Blythe", 50);
        Pirate pirate7 = new Pirate("Darlene 'Slayer' Soren", 75);
        Pirate pirate8 = new Pirate("'Blunderbuss' Uberto", 75);
        Pirate pirate9 = new Pirate("Tennie 'Intrepid' Tyndall", 100);
        Pirate pirate10 = new Pirate("Blackbeard", 500);

        list = new Pirate[] {pirate1, pirate2, pirate3, pirate4, pirate5, pirate6, pirate7, pirate8, pirate9, pirate10};
    }

    private void generateTraderList(Trader[] list) {
        Trader trader1 = new Trader(0);
        Trader trader2 = new Trader(1);
        Trader trader3 = new Trader(2);
        Trader trader4 = new Trader(3);
        Trader trader5 = new Trader(4);
        Trader trader6 = new Trader(5);
        Trader trader7 = new Trader(6);
        Trader trader8 = new Trader(7);

        list = new Trader[] {trader1, trader2, trader3, trader4, trader5, trader6, trader7, trader8};
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
