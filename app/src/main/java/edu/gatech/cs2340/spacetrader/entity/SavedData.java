package edu.gatech.cs2340.spacetrader.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SavedData implements Serializable {

    private List<SavedGame> savedGames;

    private static SavedData instance = new SavedData();

    private SavedData() {
        savedGames = new ArrayList<>();
    }

    public void addGame(SavedGame savedGame) {
        savedGames.add(savedGame);
    }

    /**
     * getter for instance
     *
     * @return the singleton instance
     */
    public static SavedData getInstance() {
        return instance;
    }

}
