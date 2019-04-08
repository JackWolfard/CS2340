package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;
import java.util.LinkedList;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;

public class SolarSystem implements Serializable {


    /** the name of the Solar System */

    private String name;

    /** the First planet in the solarsystem */

    private Planet planet;

    /** a Linked List representation of the 10 different Solar Systems, with each containing
     * one planet of the same name */

    private static LinkedList<SolarSystem> sysStarMap = new LinkedList<>();

    /** the Tech Level of a Solar System */

    private int techLevel;

    /** the Resource Level of a Solar System */

    private int resourceLevel;


    /**
     * The Constructor Method for a Solar System with a random Tech and Resource Level
     * @param planet the Planet contained in the Solar System
     */

    public SolarSystem(Planet planet) {
        this.planet = planet;
        name = planet.getName();
        techLevel = planet.getTechLevel();
        resourceLevel = planet.getResourceLevel();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Generates a Linked List of Solar Systems used to represent the Universe
     * @return a Linked List representation of the Solar Systems
     */

    public static LinkedList<SolarSystem> generateSystem() {
        Planet planet = new Planet();
        LinkedList<Planet> listOfPlanets = planet.getPlanetList();
        for (int i = 0; i < listOfPlanets.size(); i++) {
            sysStarMap.add(new SolarSystem(listOfPlanets.get(i)));
        }
        return sysStarMap;
    }


    @Override
    public String toString() {
        return String.format("A Solar System called %s with technology level: %s and resource level: %s",
                name, TechLevel.values()[techLevel], ResourceLevel.values()[resourceLevel]);
    }

    public Planet getPlanet() {
        return planet;
    }
}
