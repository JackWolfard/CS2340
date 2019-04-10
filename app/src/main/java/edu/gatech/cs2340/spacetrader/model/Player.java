package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;

import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class Player implements Serializable {

    /** the player's name */
    private final String name;

    /** the player's Pilot Skill Points */
    private final int pilotSkill;

    /** the player's Engineer Skill Points */
    private final int engSkill;

    /** the player's Trade Skill Points*/
    private final int tradeSkill;

    /** the player's Fighting Skill Points*/
    private final int fightSkill;

    /** the player's current amount of credits */
    private int credits;

    /** the player's current ship */
    private final Ship ship;

    /**
     *
     * Constructor for a new Player Character
     *
     * @param charName The name of the player character
     * @param pilotPt the starting Pilot Skill points of the character
     * @param engPt the starting Engineer Skill points of the character
     * @param tradePt the starting Trading Skill points of the character
     * @param fightPt the starting Fighting Skill points of the character
     */

    public Player(String charName, int pilotPt, int engPt, int tradePt, int fightPt) {
        name = charName;
        pilotSkill = pilotPt;
        engSkill = engPt;
        tradeSkill = tradePt;
        fightSkill = fightPt;
        credits = 1000;
        ship = new Ship(ShipType.GN);
    }

    /**
     * Getters and Setters for all of the player values
     */

    public String getName() { return name; }


    public int getCredits() { return credits; }

    public Ship getShip() { return ship; }


    public void setCredits(int credits) { this.credits = credits; }

    /**
     * Basic toString method that could be useful in debugging
     * @return A String representation of the Player Character
     */

    @Override
    public String toString() {
        return String.format("Player %s: Pilot Skill: %d, Engineer Skill: %d, Trade Skill: %d, " +
                "Fight Skill: %d, Current Credits: %d, Current Ship: %s.", name, pilotSkill,
                engSkill, tradeSkill, fightSkill, credits, ship);
    }
}
