package edu.gatech.cs2340.spacetrader.entity;

import java.io.Serializable;

public enum ResourceLevel implements Serializable {
    NONE("NOSPECIALRESOURCES", 1),
    M_RICH("MINERALRICH", 2),
    M_POOR("MINERALPOOR", 3),
    DESERT("DESERT", 4),
    WATER("LOTSOFWATER", 5),
    S_RICH("RICHSOIL", 6),
    S_POOR("POORSOIL",7),
    F_RICH("RICHFAUNA", 8),
    LIFELESS("LIFELESS", 9),
    SHROOMS("WEIRDMUSHROOMS", 10),
    HERBS("LOTSOFHERBS", 11),
    ART("ARTISTIC", 12),
    WAR("WARLIKE", 13);


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
    ResourceLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return name; }
}
