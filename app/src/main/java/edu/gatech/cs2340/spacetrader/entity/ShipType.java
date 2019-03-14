package edu.gatech.cs2340.spacetrader.entity;

public enum ShipType {
    GN ("Gnat", 50),
    AS ("Asus", 75),
    NV ("Nova", 100);

    /** the full string representation of the difficulty */
    private final String name;
    private final int travelCapacity;

    /**
     * Constructor for the enumeration
     *
     * @param name   full name of the ship type
     * @param travelCapacity maximum travel distance without refueling
     */
    private ShipType(String name, int travelCapacity) {
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
