package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;

public class Game {

    private Player player;
    private GameDifficulty difficulty;
    private Universe universe;

    /**
     * Default empty constructor which initializes everything to dummy
     * values before the player is created or loaded
     */
    public Game() {
        player = null;
        difficulty = GameDifficulty.B;
        universe = new Universe();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public HashMap<SolarSystem, int[]> getUniverse() {
        return universe.getStarMap();
    }

    @Override
    public String toString() {
        return String.format("Game with Player: %s, Difficulty: %s.", player.getName(), difficulty.getDifficulty());
    }
}
