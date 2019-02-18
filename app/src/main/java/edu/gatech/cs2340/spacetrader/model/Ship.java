package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class Ship {

    private String name;
    private ShipType shipType;

    public Ship(String name, ShipType shipType) {
        this.name = name;
        this.shipType = shipType;
    }

    public Ship(ShipType shipType) {
        this(shipType.getShipType(), shipType);
    }

    public String toString() {
        return String.format("%s of model %s", name, shipType);
    }
}
