package edu.gatech.cs2340.spacetrader.entity;
import java.io.Serializable;
import java.util.Comparator;

public enum Goods implements Serializable {
    FIREARMS("Firearms", 1250, 3,5,-75,100, "WAR", "WARLIKE",null,600,1100),
    FOOD("Food",100, 1,1,5,5,"CROPFAIL","RICHSOIL","POORSOIL",90,160),
    FURS("Furs",250, 0,0,10,10,"COLD","RICHFAUNA","LIFELESS",230,280),
    GAMES("Games",250,3,6,-10,5,"BOREDOM","ARTISTIC",null,160,270),
    ORE("Ore",350,2,3,20,10,"WAR","MINERALRICH","MINERALPOOR",350,420),
    NARCOTICS("Narcotics", 3500, 5,5,-125, 150, "BOREDOM", "WEIRDMUSHROOMS", null, 2000, 3000),
    MACHINES("Machines", 900, 4,5, -30, 5, "LACKOFWORKERS", null, null, 600,800),
    MEDICINE("Medicine", 650, 4,6,-20, 10, "PLAGUE", "LOTSOFHERBS", null, 400,700),
    ROBOTS("Robots", 5000, 6,7, -150, 100, "LACKOFWORKERS", null, null, 3500, 5000),
    WATER("Water", 30, 0, 2, 3, 4, "DROUGHT", "LOTSOFWATER", "DESERT", 30, 50);

    /** the name of the good*/
    private final String name;

    /** the baseprice of the good*/
    private final int value;
    /** the minimum level to produce the good*/
    private final int minLevelProd;
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

    Goods(String name, int value, int minLevelProd, int highestTechFreq,
          int priceIncreasePerLevel, int variance, String priceIncreaseEvent,
          String priceDecreaseEvent, String expensiveEvent, int minSpaceTrade, int maxSpaceTrade) {
        this.name = name;
        this.value = value;
        this.minLevelProd = minLevelProd;
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

    public GoodsComparator getComp() {
        return new GoodsComparator();
    }
}

class GoodsComparator implements Comparator<Goods>  {

    public int compare(Goods g1, Goods g2)
    {
        if (g1.getName().compareTo(g2.getName()) == 0) {
            return 0;
        } else if (g1.getName().compareTo(g2.getName()) < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}