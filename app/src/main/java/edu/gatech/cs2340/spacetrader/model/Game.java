package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;

public class Game {

    private Player player;
    private GameDifficulty difficulty;

    /**
     * Default empty constructor which initializes everything to dummy
     * values before the player is created or loaded
     */
    public Game() {
        player = null;
        difficulty = GameDifficulty.B;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }
}
