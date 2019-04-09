package edu.gatech.cs2340.spacetrader.model;

import android.util.Log;

import java.io.Serializable;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;

/**
 *  Similar to M3, this is a Singleton Pattern which allows for us to have only one instance
 *  of the game at a time and can access allow our VM to access the backend.
 */
public class Model implements Serializable {

    private Game game;

    private static final Model instance = new Model();

    /**
     * getter for instance
     *
     * @return the singleton instance
     */
    public static Model getInstance() {
        return instance;
    }

    public static void update(Model inst) { instance.game = inst.game; }

    public Game getGame() {
        return game;
    }

    public void initGame(GameDifficulty difficulty, String name, int pilotPt, int engPt,
                         int tradePt, int fightPt) {
        game = new Game(difficulty, name, pilotPt, engPt, tradePt, fightPt);
        Player player = game.getPlayer();
        Universe universe = game.getUniverse();
        Log.d("Model", player.toString());
        Log.d("Model", game.toString());
        Log.d("Model", universe.toString());
    }

}
