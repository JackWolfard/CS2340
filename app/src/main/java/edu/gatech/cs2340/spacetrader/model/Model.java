package edu.gatech.cs2340.spacetrader.model;

/**
 *  Similar to M3, this is a Singleton Pattern which allows for us to have only one instance
 *  of the game at a time and can access allow our VM to access the backend.
 */
public class Model {

    private Game game;

    private static Model instance = new Model();

    /**
     * getter for instance
     *
     * @return the singleton instance
     */
    public static Model getInstance() {
        return instance;
    }

    private Model() {
        game = new Game();
    }

    public Game getGame() {
        return game;
    }

}
