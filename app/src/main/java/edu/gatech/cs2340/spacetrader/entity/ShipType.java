package edu.gatech.cs2340.spacetrader.entity;

public enum ShipType {
    GN ("Gnat"),
    AS ("Asus"),
    NV ("Nova");

    /** the full string representation of the difficulty */
    private final String name;

    /**
     * Constructor for the enumeration
     *
     * @param name   full name of the ship type
     */
    ShipType(String name) {
        this.name = name;
    }

    /**
     *
     * @return   the full ship type name
     */
    public String getShipType() { return name; }

    /**
     *
     * @return the display string representation of the course
     */
    public String toString() { return name; }

}
