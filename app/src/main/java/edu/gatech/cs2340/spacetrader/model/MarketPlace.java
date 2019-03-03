package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;
import java.util.Random;
import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;

public class MarketPlace
{
    private HashMap<Goods, Integer> inventory = new HashMap<Goods, Integer>();
    private HashMap<Goods, Integer> cost = new HashMap<Goods, Integer>();



    public MarketPlace(TechLevel tech, ResourceLevel resource) {
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            Goods item = Goods.values()[i];
            //calculate quantity
            Integer quantity = 0;
            int planetTechLevel = tech.getTechLevel();
            //calculate quantity modifiers
            if (planetTechLevel >= item.getMinLevelProd()) {
                quantity = rand.nextInt(3);
            }
            if (planetTechLevel == item.getHighestTechFreq()) {
                quantity = quantity * 2;
            }
            inventory.put(item, quantity);
            //calculate base price
            int variance = rand.nextInt(item.getVariance() + 1) - 1;
            Integer price = item.getValue() + (item.getPriceIncreasePerLevel() * (tech.getTechLevel() - item.getMinLevelProd())) + variance;

            String resourceLevel = resource.toString();
            String radicalInc = item.getPriceIncreaseEvent();
            String abundance = item.getPriceDecreaseEvent();
            String expensive = item.getExpensiveEvent();

            //calculate price modifiers
            if (resourceLevel.equals(radicalInc)) {
                price = price * 3;
            }
            if (abundance != null && abundance.equals(resourceLevel)) {
                price = (int) java.lang.Math.floor(price * .2);
            }
            if (expensive != null && expensive.equals(resourceLevel)) {
                price = (int) java.lang.Math.floor(price * 1.5);
            }

            cost.put(item, price);
        }
    }

    public void update(TechLevel tech, ResourceLevel resource){
        inventory.clear();
        cost.clear();
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            Goods item = Goods.values()[i];
            //calculate quantity
            Integer quantity = 0;
            int planetTechLevel = tech.getTechLevel();
            //calculate quantity modifiers
            if (planetTechLevel >= item.getMinLevelProd()) {
                quantity = rand.nextInt(3);
            }
            if (planetTechLevel == item.getHighestTechFreq()) {
                quantity = quantity*2;
            }
            inventory.put(item, quantity);
            //calculate base price
            int variance = rand.nextInt(item.getVariance() + 1) - 1;
            Integer price = item.getValue() + (item.getPriceIncreasePerLevel()*(tech.getTechLevel()-item.getMinLevelProd())) +variance;

            String resourceLevel = resource.toString();
            String radicalInc = item.getPriceIncreaseEvent();
            String abundance = item.getPriceDecreaseEvent();
            String expensive = item.getExpensiveEvent();

            //calculate price modifiers
            if (resourceLevel.equals(radicalInc)) {
                price = price * 3;
            }
            if (abundance != null && abundance.equals(resourceLevel)) {
                price = (int) java.lang.Math.floor(price * .2);
            }
            if (expensive != null && expensive.equals(resourceLevel)) {
                price = (int) java.lang.Math.floor(price *1.5);
            }

            cost.put(item, price);
        }
    }

    //The Sell Method needs a way to know what planet the Player is currently on

//    public void sellGoods(Player player, Goods item) {
//        int val = item.getValue();
//        int ipl = item.getPriceIncreasePerLevel();
//        //int techLevel = current planets tech level
//        int mtlp = item.getMinLevelProd();
//        int var = item.getVariance();
//        if (techLevel - mtlp < 0) {
//            throw new IndexOutOfBoundsException("That item cannot be sold on this planet.");
//        }
//        //int price = val + (ipl * (techLevel - mtlp)) + var;
//        if (player.getShip().getCargoList().contains(item)) {
//            player.setCredits(player.getCredits() + val);
//            player.getShip().removeFromCargo(item);
//        } else {
//            throw new IndexOutOfBoundsException("You do not currently have that " +
//                    "item in your Cargo Hold");
//        }
//    }
}
