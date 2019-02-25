package edu.gatech.cs2340.spacetrader.model;

import java.util.LinkedList;

public class Planet {

    /** The name of the Planet */

    private String name;

    /** the Linked List Representation of the 10 Planets used in the Solar System */

    private LinkedList<Planet> planetList = new LinkedList<>();

    /**
     * Constructor method to create a new Planet with a name
     * @param planetName The name given to the created Planet
     */

    public Planet(String planetName) {
        this.name = planetName;
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

    public LinkedList<Planet> getPlanetList() { return planetList; }

    /**
     * Returns the planet's Name
     * @return the Planet's Name
     */

    public String getName() { return name; }
}
