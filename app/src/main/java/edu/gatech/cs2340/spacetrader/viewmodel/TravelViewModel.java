package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Pirate;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Ship;
import edu.gatech.cs2340.spacetrader.model.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Trader;
import edu.gatech.cs2340.spacetrader.model.Universe;

public class TravelViewModel extends AndroidViewModel {

    private Model model;

    private ArrayList<SolarSystem> solarSystems = new ArrayList<>();

//    private Random randomEvent = new Random();
//
//    private Pirate currentPirate = null;
//    private Trader currentTrader = null;

    public TravelViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public ArrayList<ArrayList<String>> initTravel() {

        ArrayList<ArrayList<String>> info = new ArrayList<>(3);
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Player player = game.getPlayer();
        Ship ship = player.getShip();

        HashMap<SolarSystem, Integer> travelInfo = universe.aboutToTravel(ship);
        Set<SolarSystem> planetSet = travelInfo.keySet();

        ArrayList<String> planetNames = new ArrayList<>();
        ArrayList<String> fuelCosts = new ArrayList<>();
        ArrayList<String> fuelRemaining = new ArrayList<>();

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

    public ArrayList<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

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
