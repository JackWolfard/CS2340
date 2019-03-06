package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class Ship {

    private String name;
    private ShipType shipType;
    private int cargoHold;
    private int currentsize;
    private HashMap<Goods, Integer> inventory = new HashMap<Goods, Integer>();

    public Ship(String name, ShipType shipType) {
        this.name = name;
        this.shipType = shipType;
        initializeCargoHold();
        currentsize = 0;
    }
    public void initializeCargoHold() {
        for (int i = 0; i < 10; i++) {
            Goods item = Goods.values()[i];
            inventory.put(item,0);
        }
    }
    public Ship(ShipType shipType) {
        this(shipType.getShipType(), shipType);
        cargoHold = 15;
    }

    public void addToCargo(Goods item) {
        int currentInv = inventory.get(item);
        inventory.put(item, currentInv + 1);
        currentsize++;
    }

    public void removeFromCargo(Goods item) {
        if (inventory.get(item) <= 0) {
            throw new java.lang.IndexOutOfBoundsException("Cargo Bay is empty!");
        } else {
            int currentInv = inventory.get(item);
            inventory.put(item, currentInv - 1);
        }
        currentsize--;
    }

    public HashMap<Goods, Integer> getCargoList() {
        return inventory;
    }

    public boolean isFull() {
        return currentsize == cargoHold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("%s of model %s", name, shipType);
    }
}
