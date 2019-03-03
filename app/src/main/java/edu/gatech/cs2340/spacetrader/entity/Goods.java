package edu.gatech.cs2340.spacetrader.entity;

public enum Goods {
    WATER("Water", 30, 0, 0, 2, 3, 4, "DROUGHT", "LOTSOFWATER", "DESERT", 30, 50),
    FURS("Furs",250, 0,0,0,10,10,"COLD","RICHFAUNA","LIFELESS",230,280),
    FOOD("Food",100, 1,0,1,5,5,"CROPFAIL","RICHSOIL","POORSOIL",90,160),
    ORE("Ore",350,2,2,3,20,10,"WAR","MINERALRICH","MINERALPOOR",350,420),
    GAMES("Games",250,3,1,6,-10,5,"BOREDOM","ARTISTIC",null,160,270),
    FIREARMS("Firearms", 1250, 3,1,5,-75,100, "WAR", "WARLIKE",null,600,1100),
    MEDICINE("Medicine", 650, 4, 1,6,-20, 10, "PLAGUE", "LOTSOFHERBS", null, 400,700),
    MACHINES("Machines", 900, 4,3,5, -30, 5, "LACKOFWORKERS", null, null, 600,800),
    NARCOTICS("Narcotics", 3500, 5,0,5,-125, 150, "BOREDOM", "WEIRDMUSHROOMS", null, 2000, 3000),
    ROBOTS("Robots", 5000, 6,4,7, -150, 100, "LACKOFWORKERS", null, null, 3500, 5000);

    /** the name of the good*/
    private final String name;

    /** the baseprice of the good*/
    private final int value;
    /** the minimum level to produce the good*/
    private final int minLevelProd;
    /** the minimum level to use the good*/
    private final int minLevelUse;
    /** the tech level that produces most of the good*/
    private final int highestTechFreq;
    /** the price increase of the good per Tech Level*/
    private final int priceIncreasePerLevel;
    /** the variance in price of the good*/
    private final int variance;
    /** the event that makes the price outrageous*/
    private final String priceIncreaseEvent;
    /** the the event that makes the price very low*/
    private final String priceDecreaseEvent;
    /** the the event that makes the price Expensive*/
    private final String expensiveEvent;
    /** the minimum price in a space trade*/
    private final int minSpaceTrade;
    /** the maximum price in a space trade*/
    private final int maxSpaceTrade;

    private Goods(String name, int value, int minLevelProd, int minLevelUse, int highestTechFreq, int priceIncreasePerLevel, int variance,
                  String priceIncreaseEvent, String priceDecreaseEvent, String expensiveEvent, int minSpaceTrade, int maxSpaceTrade) {
        this.name = name;
        this.value = value;
        this.minLevelProd = minLevelProd;
        this.minLevelUse = minLevelUse;
        this.highestTechFreq = highestTechFreq;
        this.priceIncreaseEvent = priceIncreaseEvent;
        this.priceIncreasePerLevel = priceIncreasePerLevel;
        this.variance = variance;
        this.priceDecreaseEvent = priceDecreaseEvent;
        this.expensiveEvent = expensiveEvent;
        this.minSpaceTrade = minSpaceTrade;
        this.maxSpaceTrade = maxSpaceTrade;
    }
    /**
     *
     * @return the display string representation of the good
     */
    public String toString() { return name; }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getMinLevelProd() {
        return minLevelProd;
    }

    public int getMinLevelUse() {
        return minLevelUse;
    }

    public int getHighestTechFreq() {
        return highestTechFreq;
    }

    public int getPriceIncreasePerLevel() {
        return priceIncreasePerLevel;
    }

    public int getVariance() {
        return variance;
    }

    public String getPriceIncreaseEvent() {
        return priceIncreaseEvent;
    }

    public String getPriceDecreaseEvent() {
        return priceDecreaseEvent;
    }

    public String getExpensiveEvent() {
        return expensiveEvent;
    }

    public int getMinSpaceTrade() {
        return minSpaceTrade;
    }

    public int getMaxSpaceTrade() {
        return maxSpaceTrade;
    }
}
