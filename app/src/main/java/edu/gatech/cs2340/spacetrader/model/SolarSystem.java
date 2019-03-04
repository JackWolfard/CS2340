package edu.gatech.cs2340.spacetrader.model;

import java.util.LinkedList;
import java.util.Random;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
public class SolarSystem {


    /** the name of the Solar System */

    private String name;

    /** a Linked List representation of the 10 different Solar Systems, with each containing
     * one planet of the same name */

    private static LinkedList<SolarSystem> sysStarMap = new LinkedList<SolarSystem>();

    /** the Tech Level of a Solar System */

    private int techLevel;

    /** the Resource Level of a Solar System */

    private int resourceLevel;


    /**
     * The Constructor Method for a Solar System with a random Tech and Resource Level
     * @param planet the Planet contained in the Solar System
     */

    public SolarSystem(Planet planet) {
        name = planet.getName();
        techLevel = planet.getTechLevel();
        resourceLevel = planet.getResourceLevel();
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

}
