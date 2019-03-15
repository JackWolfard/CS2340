package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;

public class Trader {

    /** the Tech Level of a Space Trader */

    private int techLevel;

    /** the Resource Level of a Space Trader is always NONE because they're in space*/

    private int resourceLevel = 1;

    /** the Space Traders Market*/
    private MarketPlace market;


    public Trader(int techLevel) {
        this.techLevel = techLevel;
        market = new MarketPlace(TechLevel.values()[techLevel],
                ResourceLevel.values()[resourceLevel], true);

    }
}