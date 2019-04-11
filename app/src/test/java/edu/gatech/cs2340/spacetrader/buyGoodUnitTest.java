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
 * @author Bradley Allnutt
 */
public class buyGoodUnitTest {
    private final Player player = new Player("TestPlayer", 3,4,4,4);
    private final MarketPlace testMarket = new MarketPlace(TechLevel.values()[4], ResourceLevel.NONE, false);
    private final Ship testShip = player.getShip();

    /**
     * Check that initialized players cargoSize starts empty.
     */
    @Test
    public void initialize() {
        assertEquals(0,testShip.getCurrentSize());
    }

    /**
     * Confirm that the buying one good decreases the players money, adds one to the ships cargoHold
     * and decreases one from the markets inventory
     */
    @Test
    public void purchaseSuccessful() {
        int originalAmount = testMarket.getGoodAmount(Goods.FOOD);
        testMarket.buyGoods(player, Goods.FOOD);
        boolean credits = player.getCredits() < 10000;

        assertTrue(credits);
        assertEquals(1, testShip.getCurrentSize());
        assertEquals(originalAmount-1, testMarket.getGoodAmount(Goods.FOOD));
    }

    /**
     * Checks to make sure that the cargoHold can be filled up to max size without throwing any
     * errors
     */
    @Test
    public void testMaxCargoSize() {
        //fill up ship to max cargoSize;
        for (int i = 0; i < testShip.getCargoHold(); i++) {
            player.setCredits(10000);
            testMarket.update();
            testMarket.buyGoods(player, Goods.FOOD);
            assertEquals(i + 1, testShip.getCurrentSize());
        }
        assertTrue(testShip.isFull());

    }

    /**
     * Checks to make sure that an error is thrown if the player attempts to buy a good when their
     * cargoHold is full
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceedsMaxCargoSize() {
        //fill up ship to max cargoSize;
        for (int i = 0; i < testShip.getCargoHold(); i++) {
            player.setCredits(10000);
            testMarket.update();
            testMarket.buyGoods(player, Goods.FOOD);
            assertEquals(i + 1, testShip.getCurrentSize());
        }
        player.setCredits(10000);
        testMarket.update();
        testMarket.buyGoods(player, Goods.FOOD);
    }

    /**
     * Checks to make sure that an error is thrown if the player attempts to buy a good when they
     * don't have enough money
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void playerDoesNotHaveEnoughMoney() {
        player.setCredits(0);
        testMarket.update();
        testMarket.buyGoods(player, Goods.FOOD);
    }
    /**
     * Checks to make sure that an error is thrown if the player attempts to buy a good when the
     * marketplace does not have it in stock
     */
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
    /**
     * Checks to make sure that buying all of the stock in the market place does not throw the same
     * error as above
     */
    @Test
    public void BuyMarketToZero() {
        int originalAmount = testMarket.getGoodAmount(Goods.FOOD);
        for (int i = 0; i < originalAmount; i++) {
            player.setCredits(10000);
            testMarket.buyGoods(player, Goods.FOOD);
        }
        assertEquals(0, testMarket.getGoodAmount(Goods.FOOD));
        assertEquals(originalAmount,testShip.getCurrentSize());
    }
}