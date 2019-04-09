package edu.gatech.cs2340.spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;
import edu.gatech.cs2340.spacetrader.entity.Goods;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.entity.ResourceLevel;

public class MarketPlace implements Serializable {
    private final HashMap<Goods, Integer> inventory = new HashMap<>();
    private final HashMap<Goods, Integer> cost = new HashMap<>();
    private final HashMap<Goods, Integer> sell = new HashMap<>();
    private final TechLevel tech;
    private final ResourceLevel resource;
    private final boolean isTrader;

    /**
     * Constructor for the Market Place sets the market places tech level and resource level
     * @param tech The tech level of the Marketplace
     * @param resource The resource level of the Marketplace
     */
    public MarketPlace(TechLevel tech, ResourceLevel resource, boolean isTrader) {
        this.tech = tech;
        this.resource = resource;
        this.isTrader = isTrader;
        initialize();
    }

    /**
     * The initialization method for the market place
     * It randomly determines the quantity for all goods that the market place is allowed to sell
     * It also calls the calculate pricing method for each item so that the prices for the items
     * are made
     */
    private void initialize() {
        for (int i = 0; i < Goods.values().length; i++) {
            Random rand = new Random();
            Goods item = Goods.values()[i];
            //calculate quantity
            int quantity = 0;
            int planetTechLevel = tech.getTechLevel();
            //calculate quantity modifiers
            if (planetTechLevel >= item.getMinLevelProd()) {
                quantity = rand.nextInt(3) + 1;
            }
            if (planetTechLevel == item.getHighestTechFreq()) {
                quantity = quantity * 2;
            }
            if (quantity != 0) {
                inventory.put(item, quantity);
                calculatePricing(item);
            }
        }
    }

    /**
     * Clears out the current inventories and prices of the market place and recreates them to
     * ensure that every time you enter the marketplace it is different.
     */
    public void update(){
        inventory.clear();
        cost.clear();
        sell.clear();
        initialize();
    }

    /**
     * Calculates the buy and sell price for each good
     * @param item The good to calculate the price for and used as the key for the hash map
     */
    private void calculatePricing(Goods item) {
        Random rand = new Random();
        //calculate base price
        int variance = rand.nextInt(item.getVariance());
        int price = item.getValue() + (item.getPriceIncreasePerLevel() * (tech.getTechLevel()
                - item.getMinLevelProd())) + variance;
        //Grab strings needed for modifiers
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
        //calculate sell price
        if (isTrader) {
            int maxPrice = item.getMaxSpaceTrade();
            int minPrice = item.getMinSpaceTrade();

            if (price - minPrice < 0) {
                price = minPrice;
            }
            if (price - maxPrice > 0) {
                price = maxPrice;
            }
        }
        //calculate sell price
        Integer sellPrice = (int) java.lang.Math.floor(price*.9);
        cost.put(item, price);
        sell.put(item,sellPrice);
    }

    /**
     * Sell a players good to the marketplace
     * Update the current inventory of the marketplace
     * @param player The player selling their goods
     * @param item The good being sold
     */
    public void sellGoods(Player player, Goods item) {
        //get price of good
        int price = sell.get(item);

        //make sure player has good in inventory
        Ship ship = player.getShip();
        HashMap<Goods, Integer> cargoList = ship.getCargoList();
        if (cargoList.get(item) > 0) {
            player.setCredits(player.getCredits() + price);
            ship.removeFromCargo(item);
            //get current inventory if 0 need to added item and create pricing for it
            int currentInventory = inventory.get(item);
            if (currentInventory == 0) {
                inventory.put(item, 1);
                calculatePricing(item);
            } else {
                inventory.put(item, currentInventory + 1);
            }

        } else {
            //should turn into toast?
            throw new IndexOutOfBoundsException("You do not currently have that " +
                    "item in your Cargo Hold");
        }
     }
    /**
     * Method for a player buying goods from a marketplace
     * Update the current inventory of the marketplace
     * @param player The player buying the good
     * @param item The good being bought
     */
    public void buyGoods(Player player, Goods item) {
        //get cost of the good
        int price = cost.get(item);
        Ship ship = player.getShip();
        //check if their is space in cargo hold
        if (ship.isFull()) {
            throw new IndexOutOfBoundsException("You do not currently enough " +
                    "space in your cargo hold to purchase this item.");
        }
        // make sure player has enough credit for purchase
        if (player.getCredits() >= price) {
            int currentInventory = inventory.get(item);
            if (currentInventory <= 0) {
                throw new IndexOutOfBoundsException("This market is out of stock on this good.");
            }
            player.setCredits(player.getCredits() - price);
            ship.addToCargo(item);
            inventory.put(item, currentInventory - 1);
        } else {
            throw new IndexOutOfBoundsException("You do not currently enough " +
                    "credits to purchase this item.");
        }
    }

    public HashMap<Goods, Integer> getInventory() {
        return inventory;
    }

    public HashMap<Goods, Integer> getCost() {
        return cost;
    }

    public HashMap<Goods, Integer> getSell() {
        return sell;
    }

    public int getGoodAmount(Goods good) {
        return inventory.get(good);
    }
}
