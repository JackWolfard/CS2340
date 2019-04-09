package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.model.Model;


public class MainViewModel extends AndroidViewModel {

    private final Model model;

    private boolean gameStarted;

    public MainViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
        gameStarted = false;
    }

    public void initGame(GameDifficulty difficulty, String name, int pilotPt, int engPt,
                          int tradePt, int fightPt) {
        Log.d("MainViewModel", "Initializing Game");
        model.initGame(difficulty, name, pilotPt, engPt, tradePt, fightPt);
        gameStarted = true;
    }

    public boolean loadGame(Context context) {
        Log.d("MainViewModel", "Loading Game");
        File root = context.getFilesDir();
        File file = new File(root, "example.json");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Model.update((Model) in.readObject());
            in.close();
            gameStarted = true;
            return true;
        } catch (IOException e) {
            Log.e("MainViewModel", "Error reading an entry from binary file", e);
        } catch (ClassNotFoundException e) {
            Log.e("MainViewModel", "Error casting a class from the binary file", e);
        }
        return false;
    }

    public boolean getGameStarted() {
        return gameStarted;
    }

    public boolean saveGame(Context context) {
        Log.d("MainViewModel", "Saving Game");
        File root = context.getFilesDir();
        File file = new File(root, "example.json");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(model);
            out.close();
            return true;
        } catch (IOException e) {
            Log.e("MainViewModel", "Error writing an entry from binary file", e);
        }
        return false;
    }
}
