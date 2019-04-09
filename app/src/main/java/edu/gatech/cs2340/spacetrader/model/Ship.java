package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class Ship implements Serializable {

    private final String name;
    private final ShipType shipType;
    private int cargoHold;
    private int currentsize;
    private int currentMileage;
    private final HashMap<Goods, Integer> inventory = new HashMap<>();

    private Ship(String name, ShipType shipType) {
        this.name = name;
        this.shipType = shipType;
        this.currentMileage = shipType.getTravelDistance();
        initializeCargoHold();
        currentsize = 0;

    }
    private void initializeCargoHold() {
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
        if (currentsize >= cargoHold) {
            throw new IndexOutOfBoundsException("The cargo hold is full");
        } else {
            int currentInv = inventory.get(item);
            inventory.put(item, currentInv + 1);
            currentsize++;
        }
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

    public String toString() {
        return String.format("%s of model %s", name, shipType);
    }

    public int getCargoHold() {
        return cargoHold;
    }

    public int getCurrentsize() {
        return currentsize;
    }

    public int getCurrentMileage() {return currentMileage;}

    public void setCurrentMileage(int travel) {currentMileage = travel;}

}
