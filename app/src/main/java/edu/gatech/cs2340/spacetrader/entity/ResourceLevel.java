package edu.gatech.cs2340.spacetrader.entity;

import java.io.Serializable;

public enum ResourceLevel implements Serializable {
    NONE("NOSPECIALRESOURCES"),
    M_RICH("MINERALRICH"),
    M_POOR("MINERALPOOR"),
    DESERT("DESERT"),
    WATER("LOTSOFWATER"),
    S_RICH("RICHSOIL"),
    S_POOR("POORSOIL"),
    F_RICH("RICHFAUNA"),
    LIFELESS("LIFELESS"),
    SHROOMS("WEIRDMUSHROOMS"),
    HERBS("LOTSOFHERBS"),
    ART("ARTISTIC"),
    WAR("WARLIKE");


    /** the full string representation of the difficulty */
    private final String name;

    /**
     * Constructor for the enumeration
     *
     * @param name   full name of the techLevel
     */
    ResourceLevel(String name) {
        this.name = name;
    }

    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return name; }
}
