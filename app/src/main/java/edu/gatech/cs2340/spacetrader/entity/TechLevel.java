package edu.gatech.cs2340.spacetrader.entity;

public enum TechLevel {

    PRE_AG("Pre-Agriculture",1),
    AGR("Agriculture", 2),
    MED("Medieval",3),
    REN("Renaissance", 4),
    EARLY_IND("Early Industrial", 5),
    IND("Industrial", 6),
    POST_IND("Post-Industrial", 7),
    HITECH("Hi-Tech",8);

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
    private TechLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return name; }
}
