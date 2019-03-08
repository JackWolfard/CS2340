package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Universe;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Planet;
import edu.gatech.cs2340.spacetrader.model.MarketPlace;
import edu.gatech.cs2340.spacetrader.model.Ship;

public class MarketViewModel extends AndroidViewModel {

    private static final String TAG = "MarketViewModel";

    private Model model;

    public MarketViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public ArrayList<ArrayList<String>> initMarket() {
        ArrayList<ArrayList<String>> info = new ArrayList<>(5);
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Player player = game.getPlayer();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();
        Ship ship = player.getShip();

        ArrayList<String> goodNames = new ArrayList<>();
        ArrayList<String> quantities = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> sellPrices = new ArrayList<>();
        ArrayList<String> amountOwned = new ArrayList<>();

        HashMap<Goods, Integer> inventory = market.getInventory();
        HashMap<Goods, Integer> cost = market.getCost();
        HashMap<Goods, Integer> sell = market.getSell();
        HashMap<Goods, Integer> shipCargo = ship.getCargoList();

        Set<Goods> goodsSet = inventory.keySet();
        ArrayList<Goods> sortedSet = new ArrayList<>(goodsSet);
        Goods getComp = sortedSet.get(0);
        Comparator<Goods> goodsComp = getComp.getComp();
        sortedSet.sort(goodsComp);
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


    public int initCredits() {
        Game game = model.getGame();
        Player player = game.getPlayer();
        return player.getCredits();
    }

    public int[] initInventory() {
        int[] info = new int[2];
        Game game = model.getGame();
        Player player = game.getPlayer();
        Ship ship = player.getShip();
        info[0] = ship.getCargoHold();
        info[1] = ship.getCurrentsize();
        return info;
    }

    public void buyGood(Goods good) {
        Game game = model.getGame();
        Player player = game.getPlayer();
        Universe universe = game.getUniverse();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();

        market.buyGoods(player, good);
    }
    public void sellGood(Goods good) {
        Game game = model.getGame();
        Player player = game.getPlayer();
        Universe universe = game.getUniverse();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();

        market.sellGoods(player, good);

    }

}
