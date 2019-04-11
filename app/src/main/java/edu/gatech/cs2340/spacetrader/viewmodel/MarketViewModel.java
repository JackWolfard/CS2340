package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Universe;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Planet;
import edu.gatech.cs2340.spacetrader.model.MarketPlace;
import edu.gatech.cs2340.spacetrader.model.Ship;

/**
 * ViewModel that supports the marketView
 */
public class MarketViewModel extends AndroidViewModel {

    private final Model model;

    /**
     * Constructor for MarketViewModel
     * @param application
     */
    public MarketViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    /**
     * refreshMarket method reloads the market data
     */
    public void refreshMarket() {
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();
        market.update();
    }

    /**
     * Initializes the data for market
     * @return
     */
    public List<List<String>> initMarket() {
        List<List<String>> info = new ArrayList<>(5);
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Player player = game.getPlayer();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();
        Ship ship = player.getShip();

        List<String> goodNames = new ArrayList<>();
        List<String> quantities = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        List<String> sellPrices = new ArrayList<>();
        List<String> amountOwned = new ArrayList<>();

        Map<Goods, Integer> inventory = market.getInventory();
        Map<Goods, Integer> cost = market.getCost();
        Map<Goods, Integer> sell = market.getSell();
        Map<Goods, Integer> shipCargo = ship.getCargoList();

        Set<Goods> goodsSet = inventory.keySet();
        SortedSet<Goods> sortedSet = new TreeSet<>(goodsSet);
        for (Goods good : sortedSet) {
            goodNames.add(good.getName());
            quantities.add(String.valueOf(inventory.get(good)));
            prices.add(String.valueOf(cost.get(good)));
            sellPrices.add(String.valueOf(sell.get(good)));
            amountOwned.add(String.valueOf(shipCargo.get(good)));
        }

        info.add(goodNames);
        info.add(quantities);
        info.add(prices);
        info.add(sellPrices);
        info.add(amountOwned);

        return info;

    }

    /**
     * Initializes the data for the credits text field
     * @return
     */
    public int initCredits() {
        Game game = model.getGame();
        Player player = game.getPlayer();
        return player.getCredits();
    }

    /**
     * Initializes data for the inventory text field
     * @return
     */
    public int[] initInventory() {
        int[] info = new int[2];
        Game game = model.getGame();
        Player player = game.getPlayer();
        Ship ship = player.getShip();
        info[0] = ship.getCargoHold();
        info[1] = ship.getCurrentSize();
        return info;
    }

    /**
     * Buy good function calls the market buy good function
     * @param good
     */
    public void buyGood(Goods good) {
        Game game = model.getGame();
        Player player = game.getPlayer();
        Universe universe = game.getUniverse();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();

        market.buyGoods(player, good);
    }

    /**
     * Sell good function calls the market sell good function
     * @param good
     */
    public void sellGood(Goods good) {
        Game game = model.getGame();
        Player player = game.getPlayer();
        Universe universe = game.getUniverse();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();

        market.sellGoods(player, good);

    }

}