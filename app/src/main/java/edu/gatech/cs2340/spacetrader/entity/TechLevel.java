package edu.gatech.cs2340.spacetrader.entity;

import java.io.Serializable;

public enum TechLevel implements Serializable {

    PRE_AG("Pre-Agriculture",0),
    AGR("Agriculture", 1),
    MED("Medieval",2),
    REN("Renaissance", 3),
    EARLY_IND("Early Industrial", 4),
    IND("Industrial", 5),
    POST_IND("Post-Industrial", 6),
    HITECH("Hi-Tech",7);

    /** the full string representation of the difficulty */
    private final String name;

    /** the value associated with the tech level*/
    private final int value;

    /**
     * Constructor for the enumeration
     *
     * @param name   full name of the techLevel
     * @param value  value associated with tech level
     */
    TechLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return name; }

    /**
     *
     * @return the integer value of the tech level
     */
    public int getTechLevel() { return value; }
}
