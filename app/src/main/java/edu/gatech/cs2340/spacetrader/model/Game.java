package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;

public class Game implements Serializable {

    private final Player player;
    private final GameDifficulty difficulty;
    private final Universe universe;

    /**
     * Default empty constructor which initializes everything to dummy
     * values before the player is created or loaded
     */
    public Game(GameDifficulty difficulty, String name, int pilotPt, int engPt, int tradePt,
                int fightPt) {
        this.difficulty = difficulty;
        this.universe = new Universe();
        this.player = new Player(name, pilotPt, engPt, tradePt, fightPt);
    }

    public Universe getUniverse() {
        return universe;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return String.format("Game with Player: %s, Difficulty: %s.", player.getName(),
                difficulty.getDifficulty());
    }
}
