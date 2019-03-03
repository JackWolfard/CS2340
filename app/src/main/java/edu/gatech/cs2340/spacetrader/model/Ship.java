package edu.gatech.cs2340.spacetrader.model;

import java.util.LinkedList;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class Ship {

    private String name;
    private ShipType shipType;
    private int cargoHold;
    private LinkedList<Goods> cargoList;

    public Ship(String name, ShipType shipType) {
        this.name = name;
        this.shipType = shipType;
    }

    public Ship(ShipType shipType) {
        this(shipType.getShipType(), shipType);
        cargoHold = 15;
    }

    public void addToCargo(Goods item) {
        cargoList.add(item);
    }

    public void removeFromCargo(Goods item) {
        if (cargoList.size() > 0) {
            cargoList.remove(item);
        } else {
            throw new java.lang.IndexOutOfBoundsException("Cargo Bay is empty!");
        }
    }

    public LinkedList getCargoList() {
        return cargoList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("%s of model %s", name, shipType);
    }
}
