package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;

public class Pirate implements Serializable {

    private final int power;
    private final String name;

    public Pirate(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

}
