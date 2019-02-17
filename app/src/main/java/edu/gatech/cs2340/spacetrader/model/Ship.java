package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class Ship {

    private String name;
    private ShipType shipType;

    Ship(String name, ShipType shipType) {
        this.name = name;
        this.shipType = shipType;
    }

}
