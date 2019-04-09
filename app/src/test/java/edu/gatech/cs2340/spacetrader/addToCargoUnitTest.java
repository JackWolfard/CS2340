package edu.gatech.cs2340.spacetrader;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.model.Ship;

import org.junit.Test;

import static org.junit.Assert.*;


public class addToCargoUnitTest {

    private final Ship testShip = new Ship(ShipType.GN);

    @Test
    public void initialize() {
        assertEquals(0,testShip.getCurrentsize());
    }

    @Test
    public void addSuccessful() {
        testShip.addToCargo(Goods.FOOD);

        assertEquals(1,testShip.getCurrentsize());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void cargoFullAdd() {
        for (int i = 0; i < 16; i++) {
            testShip.addToCargo(Goods.FIREARMS);
        }
        assertEquals(15, testShip.getCurrentsize());
    }

}
