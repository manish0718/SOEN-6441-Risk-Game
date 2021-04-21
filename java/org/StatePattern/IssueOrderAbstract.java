package org.StatePattern;

import org.GamePlay.Country;
import org.GamePlay.Player;
import org.GamePlay.playGame;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract class to issue order.
 */
public abstract class IssueOrderAbstract extends Phase{
    /**
     * Constructor to call the Phase class constructor.
     * @param p_ge Object of the GameEngine1 class.
     */
    IssueOrderAbstract(GameEngine1 p_ge)
    {
        super(p_ge);
    }

    /**
     * Method to print the invalid command error if player enters assignCountries command while issuing orders.
     */
    public void assignCountries()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print the invalid command error if player enters the execute command
     * before issuing the orders.
     */
    public void execute()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print the invalid command error if player wants to start the GameEngine
     * in between issuing the orders.
     */
    public void startGameEngine(){
        printInvalidCommandMessage();
}

    /**
     * Method to print the invalid command error if player enters playGame command
     * while issuing the orders.
     */
    public void playGame(){
        printInvalidCommandMessage();
    }

    /**
     * Method to print the invalid command error if player enters EditMap command while
     * issuing the orders.
     */
    public void EditMap(){
        printInvalidCommandMessage();
    }

    /**
     * Method to print the invalid command error if player enters the showMap
     * while issuing the orders.
     */
    public void ShowMaps(){
        printInvalidCommandMessage();
    }

}

