package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Planet;
import edu.gatech.cs2340.spacetrader.model.Player;
import edu.gatech.cs2340.spacetrader.model.Ship;
import edu.gatech.cs2340.spacetrader.model.Universe;


public class MainViewModel extends AndroidViewModel {

    private Model model;

    private boolean gameStarted;

    public MainViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
        gameStarted = false;
    }

    public void initGame(GameDifficulty difficulty, String name, int pilotPt, int engPt,
                          int tradePt, int fightPt) {
        Log.d("MainViewModel", "Initializing Game");
        Game game = model.getGame();
        game.setDifficulty(difficulty);
        Player player = new Player(name, pilotPt, engPt, tradePt, fightPt);
        player.setShip(new Ship(ShipType.GN));
        Universe universe = game.getUniverse();
        game.setPlayer(player);
        Log.d("Edit", player.toString());
        Log.d("Edit", game.toString());
        Log.d("Edit", universe.toString());
        gameStarted = true;
    }

    public void loadGame() {
        Log.d("MainViewModel", "Loading Game");
        gameStarted = false;
    }

    public boolean getGameStarted() {
        return gameStarted;
    }

    public boolean saveGame() {
        Log.d("MainViewModel", "Saving Game");
        if (gameStarted) {
            return true;
        }
        return false;
    }
}
