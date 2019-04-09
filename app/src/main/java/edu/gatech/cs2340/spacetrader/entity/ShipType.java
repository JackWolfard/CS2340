package edu.gatech.cs2340.spacetrader.entity;

import java.io.Serializable;

public enum ShipType implements Serializable {
    GN ("Gnat", 150);

    /** the full string representation of the difficulty */
    private final String name;
    private final int travelCapacity;

    /**
     * Constructor for the enumeration
     *
     * @param name   full name of the ship type
     * @param travelCapacity maximum travel distance without refueling
     */
    ShipType(String name, int travelCapacity) {
        this.name = name;
        this.travelCapacity = travelCapacity;
    }

    /**
     *
     * @return   the full ship type name
     */
    public String getShipType() { return name; }

    /**
     *
     * @return   the full max gas tank
     */
    public int getTravelDistance() { return travelCapacity; }


    /**
     *
     * @return the display string representation of the course
     */
    public String toString() { return name; }

}
