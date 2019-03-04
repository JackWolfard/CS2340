package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Universe;

public class PlanetViewModel extends AndroidViewModel {

    private Model model;

    public PlanetViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public void initPlanetView() {

        Game game = model.getGame();
        Universe universe = game.getUniverse();
        HashMap<SolarSystem, int[]> starMap = universe.getStarMap();
        Set<SolarSystem> keySet = starMap.keySet();
        Iterator<SolarSystem> iterator = keySet.iterator();
        SolarSystem ss1 = iterator.next();

        //do something with ss1 to make the attributes of the solar system/planet show up on the planet screen
    }

}

