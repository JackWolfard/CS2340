package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Universe;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Planet;
import edu.gatech.cs2340.spacetrader.model.MarketPlace;

public class MarketViewModel extends AndroidViewModel {

    private Model model;

    public MarketViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public ArrayList<ArrayList<String>> initMarket() {
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>(4);
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Planet currentPlanet = universe.getCurrentPlanet();
        MarketPlace market = currentPlanet.getMarket();

        ArrayList<String> goodNames = new ArrayList<>();
        ArrayList<String> quantities = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> sellPrices = new ArrayList<>();

        HashMap<Goods, Integer> inventory = market.getInventory();
        HashMap<Goods, Integer> cost = market.getCost();
        HashMap<Goods, Integer> sell = market.getSell();

        Set<Goods> goodsSet = inventory.keySet();
        Iterator<Goods> iterator = goodsSet.iterator();
        while (iterator.hasNext()) {
            Goods good = iterator.next();
            goodNames.add(good.getName());
            quantities.add(inventory.get(good).toString());
            prices.add(cost.get(good).toString());
            sellPrices.add(sell.get(good).toString());
        }

        info.add(goodNames);
        info.add(quantities);
        info.add(prices);
        info.add(sellPrices);

        return info;

    }

    public int initCredits() {
        Game game = model.getGame();
        Player player = game.getPlayer();
        return player.getCredits();
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
