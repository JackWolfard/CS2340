package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;

public class Planet implements Serializable {

    /** The name of the Planet */

    private String name;

    /** the Linked List Representation of the 10 Planets used in the Solar System */

    private List<Planet> planetList = new LinkedList<>();

    /** the Tech Level of a Solar System */

    private int techLevel;

    /** the Resource Level of a Solar System */

    private int resourceLevel;

    private MarketPlace market;

    /**
     * Constructor method to create a new Planet with a name
     * @param planetName The name given to the created Planet
     */

    public Planet(String planetName) {
        this.name = planetName;
        Random rand = new Random();
        techLevel = rand.nextInt(7);
        resourceLevel = rand.nextInt(13);
        market = new MarketPlace(TechLevel.values()[techLevel],
                ResourceLevel.values()[resourceLevel], false);
    }

    public Planet() {
        generatePlanets();
    }

    /**
     * Generates a Linked List representation of 10 different Planets
     */

    public void generatePlanets() {

        planetList.add(new Planet("Deshaan"));
        planetList.add(new Planet("Kumuro"));
        planetList.add(new Planet("Cho"));
        planetList.add(new Planet("Kepler"));
        planetList.add(new Planet("Seyda"));
        planetList.add(new Planet("Veela"));
        planetList.add(new Planet("Haru"));
        planetList.add(new Planet("Khor"));
        planetList.add(new Planet("Babka"));
        planetList.add(new Planet("Noi"));

    }

    /**
     * Returns the list of Planets Generated
     * @return A Linked List of Planets
     */

    public List<Planet> getPlanetList() { return planetList; }

    /**
     * Returns the planet's Name
     * @return the Planet's Name
     */

    public String getName() { return name; }
    /**
     * Returns the planet's Name
     * @return the Planet's Name
     */

    public int getTechLevel() { return techLevel; }
    /**
     * Returns the planet's Name
     * @return the Planet's Name
     */

    public int getResourceLevel() { return resourceLevel; }

    public MarketPlace getMarket() {
        return market;
    }
}
