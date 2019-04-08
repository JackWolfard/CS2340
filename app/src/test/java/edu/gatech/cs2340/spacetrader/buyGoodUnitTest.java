package edu.gatech.cs2340.spacetrader;



import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.model.MarketPlace;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Ship;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class buyGoodUnitTest {
    Player player = new Player("TestPlayer", 3,4,4,4);
    MarketPlace testMarket = new MarketPlace(TechLevel.values()[4], ResourceLevel.NONE, false);
    Ship testShip = player.getShip();
    @Test
    public void initialize() {
        player.setCredits(10000);
        assertEquals(0,testShip.getCurrentsize());
    }
    @Test
    public void purchaseSuccessful() {
        int originalAmount = testMarket.getGoodAmount(Goods.FOOD);
        testMarket.buyGoods(player, Goods.FOOD);
        boolean credits = player.getCredits() < 10000;

        assertEquals(true, credits);
        assertEquals(1, testShip.getCurrentsize());
        assertEquals(originalAmount-1, testMarket.getGoodAmount(Goods.FOOD));
    }

    @Test
    public void testMaxCargoSize() {
        //fill up ship to max cargosize;
        for (int i = 0; i < testShip.getCargoHold(); i++) {
            player.setCredits(10000);
            testMarket.update();
            testMarket.buyGoods(player, Goods.FOOD);
            assertEquals(i + 1, testShip.getCurrentsize());
        }
        assertTrue(testShip.isFull());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceedsMaxCargoSize() {
        //fill up ship to max cargosize;
        for (int i = 0; i < testShip.getCargoHold(); i++) {
            player.setCredits(10000);
            testMarket.update();
            testMarket.buyGoods(player, Goods.FOOD);
            assertEquals(i + 1, testShip.getCurrentsize());
        }
        player.setCredits(10000);
        testMarket.update();
        testMarket.buyGoods(player, Goods.FOOD);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void playerDoesNotHaveEnoughMoney() {
        player.setCredits(0);
        testMarket.update();
        testMarket.buyGoods(player, Goods.FOOD);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void MarketOutOfStock() {
        int originalAmount = testMarket.getGoodAmount(Goods.FOOD);
        for (int i = 0; i < originalAmount; i++) {
            player.setCredits(10000);
            testMarket.buyGoods(player, Goods.FOOD);
        }
        assertEquals(0, testMarket.getGoodAmount(Goods.FOOD));
        testMarket.buyGoods(player, Goods.FOOD);
    }

    @Test
    public void BuyMarketToZero() {
        int originalAmount = testMarket.getGoodAmount(Goods.FOOD);
        for (int i = 0; i < originalAmount; i++) {
            player.setCredits(10000);
            testMarket.buyGoods(player, Goods.FOOD);
        }
        assertEquals(0, testMarket.getGoodAmount(Goods.FOOD));
        assertEquals(originalAmount,testShip.getCurrentsize());
    }
}