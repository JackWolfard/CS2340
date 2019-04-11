package edu.gatech.cs2340.spacetrader;

import org.junit.Test;

import java.util.Map;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.model.MarketPlace;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Ship;

import static org.junit.Assert.*;

/**
 * Unit test of MarketPlace.sellGoods(Player player, Goods item)
 *
 * @author Joseph Meredith (jmeredith37)
 */

//public void sellGoods(Player player, Goods item) {
//        //get price of good
//        int price = sell.get(item);
//
//        //make sure player has good in inventory
//        Ship ship = player.getShip();
//        Map<Goods, Integer> cargoList = ship.getCargoList();
//        if (cargoList.get(item) > 0) {
//        player.setCredits(player.getCredits() + price);
//        ship.removeFromCargo(item);
//        //get current inventory if 0 need to added item and create pricing for it
//        int currentInventory = inventory.get(item);
//        if (currentInventory == 0) {
//        inventory.put(item, 1);
//        calculatePricing(item);
//        } else {
//        inventory.put(item, currentInventory + 1);
//        }
//
//        } else {
//        //should turn into toast?
//        throw new IndexOutOfBoundsException("You do not currently have that " +
//        "item in your Cargo Hold");
//        }
//        }

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

    @Test
    public void buyToZero() {
        Map<Goods, Integer> inventory = testMarket.getInventory();
        while (inventory.get(item) > 0) {
            testMarket.buyGoods(player, item);
        }
        assertTrue(inventory.get(item) == 0);

        testMarket.sellGoods(player, item);
        assertTrue(inventory.get(item) == 1);
    }
}
