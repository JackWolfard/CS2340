package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.model.Game;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Player;


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
        game.setPlayer(player);
        Log.d("Edit", player.toString());
    }
}
