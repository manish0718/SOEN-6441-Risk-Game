package org.StatePattern;

import org.GamePlay.*;
import org.ObserverBasedLogging.LogEntryBuffer;
import org.ObserverBasedLogging.LogFile;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Main Class to start executing the orders.
 */
public class ExecuteMain extends ExecuteAbstract{

    ConcurrentHashMap<String, Player> d_playerList;
    GameEngine1 d_ge1;
    Country d_country;
    /**
     * d_message is the String variable to store the message to be displayed to the user.
     */
    public String d_message;

    /**
     * Constructor to initialize the values.
     * @param p_ge is the object of GameEngine1 class.
     * @param p_playerList is the HashMap where Player Name is the key and Player Object is the value.
     * @param p_country is the object of the country class.
     */
    public ExecuteMain(GameEngine1 p_ge, ConcurrentHashMap<String, Player> p_playerList, Country p_country)
    {
        super(p_ge);
        this.d_ge1 = p_ge;
        this.d_playerList = p_playerList;
        this.d_country = p_country;
    }

    /**
     * Method to call the ShowMap of the GamePlay Package which will display all the details including all the countries
     * a player owns, neighbouring countries of a particular country and the cards owned by the specific player.
     */
    @Override
    public void mapshow() {
        showMap l_showmap = new showMap(d_playerList, d_country);
        l_showmap.check();
    }

    @Override
    public void gamePlay() {

    }

    /**
     * Method to execute the orders issued by the player.
     */
    @Override
    public void execute() {
        Execute l_ex = new Execute(d_playerList);
        l_ex.execute();
    }

    /**
     * Method to change the phase of the game to the next phase. If there is no winner in the end then it will
     * change the phase to issueOrderPhase again and if there is a single winner in the end then this game halts.
     */
    @Override
    public void next() {

        LogEntryBuffer l_observable = new LogEntryBuffer();
        LogFile l_observer = new LogFile();
        l_observable.addObserver(l_observer);

        for(String l_player: d_playerList.keySet())
        {
            if (d_playerList.get(l_player).d_owned.size()==0)
            {
                d_playerList.remove(l_player);
                d_message = l_player+" you have lost the game. So you're out of the game!!!";
                System.out.println(d_message);
                l_observable.setMsg(d_message);
                break;
            }
        }
        if(d_playerList.size()==1)
        {
            for(String player: d_playerList.keySet()) {
                d_message = player + " is the winner of the game!!!!!!!!";
                System.out.println(d_message);
                l_observable.setMsg(d_message);
            }
            d_ge1.setPhase(new End(d_ge1));
        }
        else
        {
            d_ge1.setPhase(new OrderIssuePhase(d_playerList, d_country, d_ge1));
        }
    }
}
