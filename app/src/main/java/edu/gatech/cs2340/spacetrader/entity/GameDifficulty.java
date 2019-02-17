package edu.gatech.cs2340.spacetrader.entity;

import android.icu.lang.UCharacter;

public enum GameDifficulty {
    B ("Beginner"),
    E ("Easy"),
    N ("Normal"),
    H ("Hard"),
    I ("Impossible");

    /** the full string representation of the difficulty */
    private final String difficulty;

    /**
     * Constructor for the enumeration
     *
     * @param difficulty   full name of the difficulty
     */
    GameDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     *
     * @return   the full difficulty name
     */
    public String getDifficulty() { return difficulty; }

    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return difficulty; }

}
