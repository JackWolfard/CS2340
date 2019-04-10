package edu.gatech.cs2340.spacetrader;

import org.junit.Test;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.model.MarketPlace;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Ship;

import static org.junit.Assert.*;

public class sellGoodsUnitTest {
    private final Player player = new Player("testPlayer", 4,4,4,4);
    private final MarketPlace testMarket = new MarketPlace(TechLevel.values()[4], ResourceLevel.NONE, false);
    private final Ship testShip = player.getShip();
    private final Goods item = Goods.FOOD;

    @Test
    public void initialize() {
        assertEquals(0, testShip.getCurrentSize());
        assertEquals(1000, player.getCredits());
    }

    @Test
    public void sellSuccessful() {
        int originalAmount = testMarket.getGoodAmount(item);
        testMarket.buyGoods(player, item);

        player.setCredits(0);
        testMarket.sellGoods(player, item);
        boolean postCredits = player.getCredits() > 0;

        assertTrue(postCredits);
        assertEquals(0, testShip.getCurrentSize());
        assertEquals(originalAmount, testMarket.getGoodAmount(item));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void noCargo() {
        testMarket.sellGoods(player, item);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void wrongCargo() {
        testMarket.buyGoods(player, Goods.WATER);
        testMarket.sellGoods(player, item);
    }

    @Test
    public void testInventory() {
        int currentInventory = testMarket.getInventory().get(item);
        //assertEquals(0, currentInventory);

        testShip.addToCargo(item);
        testMarket.sellGoods(player, item);
        int currentInventory2 = testMarket.getInventory().get(item);
        assertEquals(currentInventory + 1, currentInventory2);
    }
}
