package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Universe;
import edu.gatech.cs2340.spacetrader.model.Planet;

public class PlanetViewModel extends AndroidViewModel {

    private final Model model;

    public PlanetViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public String[] initPlanetView() {
        String[] info = new String[3];
        Game game = model.getGame();
        Universe universe = game.getUniverse();
        Planet currentPlanet = universe.getCurrentPlanet();
        info[0] = currentPlanet.getName();
        info[1] = TechLevel.values()[currentPlanet.getTechLevel()].toString();
        info[2] = ResourceLevel.values()[currentPlanet.getResourceLevel()].toString();
        return info;
    }

}