package org.StatePattern;

import org.GamePlay.Country;
import org.GamePlay.Player;
import org.GamePlay.playGame;
import org.GamePlay.showMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Main class to issue the orders.
 */
public class OrderIssuePhase extends IssueOrderAbstract {

    ConcurrentHashMap<String, Player> d_playerList;
    Country d_country;
    playGame d_pg;
    GameEngine1 d_ge;

    /**
     * Constructor to initialize the variables.
     * @param p_playerList is the HashMap containing Name as the Key and Player Object as the value.
     * @param p_country is the Object of the class Country.
     * @param p_ge is the object of class gameEngine1.
     */
    OrderIssuePhase(ConcurrentHashMap<String, Player> p_playerList, Country p_country, GameEngine1 p_ge)
    {
        super(p_ge);
        this.d_playerList = p_playerList;
        this.d_country = p_country;
        this.d_ge = p_ge;
    }

    @Override
    public void gamePlay() {

    }

    /**
     * Method to start assigning the armies to the player.
     * @return the integer value containing the total number of armies of the particular player.
     */
    @Override
    public int playGameLoop() {
        d_pg = new playGame(d_playerList, d_country);
        d_pg.playGameLoop();
        mainGameLoop();
        return d_pg.d_temp;
    }

    /**
     * Method to show the map.
     */
    @Override
    public void mapshow() {
        showMap l_showmap = new showMap(d_playerList, d_country);
        l_showmap.check();
    }

    /**
     * Method to start asking for orders
     */
    @Override
    public void mainGameLoop() {
        d_pg.mainGameLoop();
    }

    /**
     * Method to change the phase to the Execute orders class.
     */
    @Override
    public void next()
    {
        d_ge.setPhase(new ExecuteMain(d_ge, d_playerList, d_country));
    }

}