package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Pirate;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Ship;
import edu.gatech.cs2340.spacetrader.model.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Universe;

/**
 * TravelViewModel initializes travel screen information
 */
public class TravelViewModel extends AndroidViewModel {

    private final Model model;

    private final ArrayList<SolarSystem> solarSystems = new ArrayList<>();

//    private Random randomEvent = new Random();
//
//    private Pirate currentPirate = null;
//    private Trader currentTrader = null;

    /**
     * TravelViewModel constructor
     * @param application
     */
    public TravelViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    /**
     * Initializes information to fill recyclerView
     * @return
     */
    public List<List<String>> initTravel() {

        List<List<String>> info = new ArrayList<>(3);
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Player player = game.getPlayer();
        Ship ship = player.getShip();

        Map<SolarSystem, Integer> travelInfo = universe.aboutToTravel(ship);
        Set<SolarSystem> planetSet = travelInfo.keySet();

        List<String> planetNames = new ArrayList<>();
        List<String> fuelCosts = new ArrayList<>();
        List<String> fuelRemaining = new ArrayList<>();

        fuelRemaining.add(String.valueOf(ship.getCurrentMileage()));

        for (SolarSystem s : planetSet) {
            solarSystems.add(s);
            planetNames.add(s.getName());
            fuelCosts.add(String.valueOf(travelInfo.get(s)));
        }

        info.add(planetNames);
        info.add(fuelCosts);
        info.add(fuelRemaining);

        return info;

    }

    /**
     * Getter for solarSystem array lists
     * @return
     */
    public ArrayList<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    /**
     * Travel method calls the universe's travel method
     * @param s solarSystem
     */
    public void travel(SolarSystem s) {
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Player player = game.getPlayer();
        Ship ship = player.getShip();
        //boolean randomNum = randomEvent();
        universe.travel(s, ship);
    }

//    public boolean randomEvent() {
//        int eventNum = randomEvent.nextInt(4);
//        if (eventNum == 0) {
//            int pirateNum = randomEvent.nextInt(10);
//            currentPirate = model.getGame().getUniverse().getPirateList().get(pirateNum);
//            return true;
//        } else if (eventNum == 1) {
//            int traderNum = randomEvent.nextInt(8);
//            currentTrader = model.getGame().getUniverse().getTraderList().get(traderNum);
//            return true;
//        }
//        return false;
//    }
}
