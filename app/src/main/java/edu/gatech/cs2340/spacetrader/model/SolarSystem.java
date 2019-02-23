package edu.gatech.cs2340.spacetrader.model;

import java.util.LinkedList;
import java.util.Random;

public class SolarSystem {

    /** the name of the Solar System */

    private String name;

    /** a Linked List representation of the 10 different Solar Systems, with each containing
     * one planet of the same name */

    private static LinkedList<SolarSystem> sysStarMap;

    /** the Tech Level of a Solar System */

    private int techLevel;

    /** the Resource Level of a Solar System */

    private int resourceLevel;

    /**
     * The Constructor Method for a Solar System with a random Tech and Resource Level
     * @param planet the Planet contained in the Solar System
     */

    public SolarSystem(Planet planet) {
        Random rand = new Random();

        name = planet.getName();

        techLevel = rand.nextInt(8);

        resourceLevel = rand.nextInt(13);
    }

    /**
     * Generates a Linked List of Solar Systems used to represent the Universe
     * @return a Linked List representation of the Solar Systems
     */

    public static LinkedList generateSystem() {
        for (int i = 0; i < Planet.getPlanetList().size(); i++) {
            sysStarMap.add(new SolarSystem(Planet.getPlanetList().get(i)));
        }
        return sysStarMap;
    }



}
