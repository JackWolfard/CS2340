package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;

import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;

class Trader implements Serializable {

    /** the Tech Level of a Space Trader */

    private final int techLevel;

    /** the Resource Level of a Space Trader is always NONE because they're in space*/

    private final int resourceLevel = 1;

    /** the Space Traders Market*/
    private final MarketPlace market;


    public Trader(int techLevel) {
        this.techLevel = techLevel;
        market = new MarketPlace(TechLevel.values()[techLevel],
                ResourceLevel.values()[resourceLevel], true);

    }
}
