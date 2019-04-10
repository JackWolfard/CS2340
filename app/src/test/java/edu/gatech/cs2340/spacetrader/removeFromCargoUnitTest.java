package edu.gatech.cs2340.spacetrader;

import org.junit.Test;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.model.Ship;

import static org.junit.Assert.assertEquals;

public class removeFromCargoUnitTest {
    Ship testShip = new Ship(ShipType.GN);

    @Test
    public void initialize() {
        assertEquals(0,testShip.getCurrentSize());
    }

    @Test
    public void removeSuccessful() {
        testShip.addToCargo(Goods.FOOD);
        testShip.removeFromCargo(Goods.FOOD);

        assertEquals(0,testShip.getCurrentSize());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void cargoFullRemove() {
        for (int i = 0; i < 16; i++) {
            testShip.addToCargo(Goods.FIREARMS);
        }
        assertEquals(15, testShip.getCurrentSize());
        for (int j = 0; j < 16; j++) {
            testShip.removeFromCargo(Goods.FIREARMS);
        }
        assertEquals(0, testShip.getCurrentSize());
        testShip.removeFromCargo(Goods.FIREARMS);
        assertEquals(0, testShip.getCurrentSize());
    }
}
