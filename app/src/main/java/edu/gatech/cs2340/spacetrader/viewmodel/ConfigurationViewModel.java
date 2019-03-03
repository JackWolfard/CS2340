package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Ship;
import edu.gatech.cs2340.spacetrader.model.Universe;


public class ConfigurationViewModel extends AndroidViewModel {

    private Model model;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

    public void initGame(GameDifficulty difficulty, String name, int pilotPt, int engPt,
                          int tradePt, int fightPt) {
        Game game = model.getGame();
        game.setDifficulty(difficulty);
        Player player = new Player(name, pilotPt, engPt, tradePt, fightPt);
        player.setShip(new Ship(ShipType.GN));
        Universe universe = new Universe();
        game.setPlayer(player);
        Log.d("Edit", player.toString());
        Log.d("Edit", game.toString());
        Log.d("Edit", universe.toString());
    }
}
