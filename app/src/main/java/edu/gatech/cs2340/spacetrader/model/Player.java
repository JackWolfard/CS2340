package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class Player {

    /** the player's name */
    private String name;

    /** the player's Pilot Skill Points */
    private int pilotSkill;

    /** the player's Engineer Skill Points */
    private int engSkill;

    /** the player's Trade Skill Points*/
    private int tradeSkill;

    /** the player's Fighting Skill Points*/
    private int fightSkill;

    /** the player's current amount of credits */
    private int credits;

    /** the player's current ship */
    private Ship ship;

    /**
     *
     * Constructor for a new Player Character
     *
     * @param charName The name of the player character
     * @param pilotPt the starting Pilot Skill points of the character
     * @param engPt the starting Engineer Skill points of the character
     * @param tradePt the starting Trading Skill points of the character
     * @param fightPt the starting Fighting Skill points of the character
     * @param initCredit the initial amount of Credits the character starts the game with
     */

    public Player (String charName, int pilotPt, int engPt, int tradePt, int fightPt,
                   int initCredit) {
    name = charName;
    pilotSkill = pilotPt;
    engSkill = engPt;
    tradeSkill = tradePt;
    fightSkill = fightPt;
    credits = initCredit;
    ship = new Ship("Gnat", ShipType.GN);
    }

    /**
     * Getters and Setters for all of the player values
     */

    public String getName() { return name; }

    public int getPilotSkill() { return pilotSkill; }

    public int getEngSkill() { return engSkill; }

    public int getTradeSkill() { return tradeSkill; }

    public int getFightSkill() { return fightSkill; }

    public int getCredits() { return credits; }

    public Ship getShip() { return ship; }

    public void setName(String name) { this.name = name; }

    public void setPilotSkill(int pilotSkill) { this.pilotSkill = pilotSkill; }

    public void setEngSkill(int engSkill) { this.engSkill = engSkill; }

    public void setTradeSkill(int tradeSkill) { this.tradeSkill = tradeSkill; }

    public void setFightSkill(int fightSkill) { this.fightSkill = fightSkill; }

    public void setCredits(int credits) { this.credits = credits; }

    public void setShip(Ship ship) { this.ship = ship; }

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
