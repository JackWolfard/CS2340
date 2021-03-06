package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;

public class SolarSystem implements Serializable {


    /** the name of the Solar System */

    private final String name;

    /** the First planet in the solar system */

    private final Planet planet;

    /** a Linked List representation of the 10 different Solar Systems, with each containing
     * one planet of the same name */

    private static final List<SolarSystem> sysStarMap = new LinkedList<>();

    /** the Tech Level of a Solar System */

    private final int techLevel;

    /** the Resource Level of a Solar System */

    private final int resourceLevel;


    /**
     * The Constructor Method for a Solar System with a random Tech and Resource Level
     * @param planet the Planet contained in the Solar System
     */

    private SolarSystem(Planet planet) {
        this.planet = planet;
        name = planet.getName();
        techLevel = planet.getTechLevel();
        resourceLevel = planet.getResourceLevel();
    }

    public String getName() {
        return name;
    }

    /**
     * Generates a List of Solar Systems used to represent the Universe
     * @return a List representation of the Solar Systems
     */

    public static List<SolarSystem> generateSystem() {
        Planet planet = new Planet();
        List<Planet> listOfPlanets = planet.getPlanetList();
        for (int i = 0; i < listOfPlanets.size(); i++) {
            sysStarMap.add(new SolarSystem(listOfPlanets.get(i)));
        }
        return sysStarMap;
    }

    /**
     * Generates a List of Solar Systems used to represent the Universe
     * @param size the number of solar systems to generate in the list
     * @return a List of the Solar Systems
     */
    public static List<SolarSystem> generateSystem(int size) {
        Planet planet = new Planet();
        List<Planet> listOfPlanets = planet.getPlanetList();
        if (size > listOfPlanets.size()) {
            throw new IllegalArgumentException("Size is larger than potential planets");
        }
        for (int i = 0; i < size; i++) {
            sysStarMap.add(new SolarSystem(listOfPlanets.get(i)));
        }
        return sysStarMap;
    }



    @Override
    public String toString() {
        return String.format("A Solar System called %s with technology level: %s and"
                        + " resource level: %s", name, TechLevel.values()[techLevel],
                        ResourceLevel.values()[resourceLevel]);
    }

    public Planet getPlanet() {
        return planet;
    }
}
