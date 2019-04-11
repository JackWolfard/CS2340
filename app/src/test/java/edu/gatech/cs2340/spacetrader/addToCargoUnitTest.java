package edu.gatech.cs2340.spacetrader;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.model.Ship;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Patrick Ufer
 * JUnit Test for addToCargo method in Ship.java
 * Achieves branch coverage because it has tests cases for when the if statement is true and false
 */
public class addToCargoUnitTest {

    private final Ship testShip = new Ship(ShipType.GN);

    @Test
    public void initialize() {
        assertEquals(0,testShip.getCurrentSize());
    }

    @Test
    public void addSuccessful() {
        testShip.addToCargo(Goods.FOOD);

        assertEquals(1,testShip.getCurrentSize());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void cargoFullAdd() {
        for (int i = 0; i < 16; i++) {
            testShip.addToCargo(Goods.FIREARMS);
        }
        assertEquals(15, testShip.getCurrentSize());
    }

}
