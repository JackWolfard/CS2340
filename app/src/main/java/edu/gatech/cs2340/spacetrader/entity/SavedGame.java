package edu.gatech.cs2340.spacetrader.entity;

import java.io.Serializable;
import java.util.Date;

import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Planet;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Universe;

public class SavedGame implements Serializable {

    private String playerName;
    private int credits;
    private String planetName;
    private Date timeSaved;

    public SavedGame(Game game) {
        Player player = game.getPlayer();
        Universe universe = game.getUniverse();
        Planet planet = universe.getCurrentPlanet();
        playerName = player.getName();
        credits = player.getCredits();
        planetName = planet.getName();
        timeSaved = new Date();
    }

}
