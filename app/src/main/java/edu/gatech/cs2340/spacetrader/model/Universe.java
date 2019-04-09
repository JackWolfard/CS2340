package edu.gatech.cs2340.spacetrader.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;


/**
 * A Set of Solar Systems
 */

public class Universe implements Serializable {

    /**
     * The actual "Universe" used in the game, a HashMap with the Planets as Keys and their
     *      coordinates as values.
     */

    private HashMap<SolarSystem, int[]> starMap = new HashMap<>();
    /** the Linked List of created Solar Systems used to add them into the starMap */
    private List<SolarSystem> sysList = SolarSystem.generateSystem();
    /** HashMap that is used for the travel UI*/
    private HashMap<SolarSystem, Integer> travelMap = new HashMap<>();
    private int[][] distanceArray = new int[sysList.size()][sysList.size()];
    private SolarSystem currentSolarSystem;
    private Planet currentPlanet;
    private Random randomEvent = new Random();
    private ArrayList<Pirate> pirateList = new ArrayList<>();
    private ArrayList<Trader> traderList = new ArrayList<>();
//    private Pirate currentPirate = null;
//    private Trader currentTrader = null;

    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
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
        currentSolarSystem = sysList.get(0);
        currentPlanet = sysList.get(0).getPlanet();
    }

    public void generateUniverse() {
        for (int j = 0; j < sysList.size(); j++) {
            Random rand = new Random();
            int[] coordinate = new int[]{rand.nextInt(151), rand.nextInt(101)};
            SolarSystem solSys = sysList.get(j);
            starMap.put(solSys, coordinate);
        }
        generateTravelDistances();
        generatePirateList();
        generateTraderList();

    }

    /**
     * updates a HashMap to contain all the planets that can be reached from current planet.
     * @param ship with specific remaining fuel left
     * @return HashMap of reachable planets
     */
    public HashMap<SolarSystem, Integer> aboutToTravel(Ship ship) {
        if (ship != null) {
            travelMap.clear();
            int index = sysList.indexOf(currentSolarSystem);
            for (int i=0; i<sysList.size(); i++) {
                int distance = distanceArray[index][i];
                if (distance <= ship.getCurrentMileage() && distance > 0) {
                    travelMap.put(sysList.get(i),distance);
                }
            }
            return travelMap;
        } else {
            throw new IllegalArgumentException("There is no valid ship to travel with");
        }
    }

    /**
     * Travels to new planet
     * Sets ships proper fuel tank
     * Sets current SolarSystem/planet
     * @param SolarSystem is new solar system to travel to
     * @param ship users current ship
     */
    public void travel(SolarSystem SolarSystem, Ship ship) {
        int distance = travelMap.get(SolarSystem);
//        int eventNum = randomEvent.nextInt(4);
//        if (eventNum == 0) {
//            int pirateNum = randomEvent.nextInt(10);
//            currentPirate = pirateList.get(pirateNum);
//
//        } else if (eventNum == 1) {
//            int traderNum = randomEvent.nextInt(8);
//            currentTrader = traderList.get(traderNum);
//        }
        ship.setCurrentMileage(ship.getCurrentMileage() - distance);
        currentSolarSystem = SolarSystem;
        currentPlanet = SolarSystem.getPlanet();
    }
    /**
     * Creates Array of All planet Travel Distances
     * X is the planet you are on
     * Y is the distance to that planet
     */
    public void generateTravelDistances() {
        for (int i = 0; i < distanceArray.length; i++) {
            SolarSystem xPlanetKey = sysList.get(i);
            int[] xCoordinate = starMap.get(xPlanetKey);
            double x1 = (double) xCoordinate[0];
            double y1 = (double) xCoordinate[1];
            for (int j = 0; j < distanceArray.length; j++) {
                SolarSystem yPlanetKey = sysList.get(j);
                int[] yCoordinate = starMap.get(yPlanetKey);
                double x2 = (double) yCoordinate[0];
                double y2 = (double) yCoordinate[1];

                int distance = (int) Math.floor(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
                distanceArray[i][j] = distance;
            }
        }
    }

    private void generatePirateList() {
        pirateList.add(new Pirate("Bubblebeard", 10));
        pirateList.add(new Pirate("'Crazy Eyes' Buxton", 20));
        pirateList.add(new Pirate("Unwin Lexx", 30));
        pirateList.add(new Pirate("Abigail 'Four-Teeth' Granger", 50));
        pirateList.add(new Pirate("Sewell 'The Cook' Shearman", 50));
        pirateList.add(new Pirate("'Big Boi' Blythe", 50));
        pirateList.add(new Pirate("Darlene 'Slayer' Soren", 75));
        pirateList.add(new Pirate("'Blunderbuss' Uberto", 75));
        pirateList.add(new Pirate("Tennie 'Intrepid' Tyndall", 100));
        pirateList.add(new Pirate("Blackbeard", 500));
    }

    private void generateTraderList() {
        traderList.add(new Trader(0));
        traderList.add(new Trader(1));
        traderList.add(new Trader(2));
        traderList.add(new Trader(3));
        traderList.add(new Trader(4));
        traderList.add(new Trader(5));
        traderList.add(new Trader(6));
        traderList.add(new Trader(7));
    }

    /**
     * Getter for starMap
     * @return a HashMap
     */
    public HashMap<SolarSystem, int[]> getStarMap() {
        return starMap;
    }

    public ArrayList<Pirate> getPirateList() {
        return pirateList;
    }

    public ArrayList<Trader> getTraderList() {
        return traderList;
    }

    @Override
    public String toString() {
        String backHalf = "";
        Set<SolarSystem> keys = starMap.keySet();
        Iterator<SolarSystem> iterator = keys.iterator();
        while (iterator.hasNext()) {
            SolarSystem ss = iterator.next();
            int[] coordinate = starMap.get(ss);
            backHalf = backHalf + ss.toString() + " at location: (" + coordinate[0] + ", " + coordinate[1] + ").\n";
        }
        return String.format("A universe with the solar systems: \n" + backHalf);
    }
}
