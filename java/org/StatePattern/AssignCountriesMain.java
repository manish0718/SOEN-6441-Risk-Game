package org.StatePattern;

import org.GamePlay.Assign;
import org.GamePlay.Country;
import org.GamePlay.Player;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Main class to assign the countries to the player.
 */
public class AssignCountriesMain extends AssignCountriesAbstract {
    ConcurrentHashMap<String, Player> d_playerList;
    Country d_country;
    File d_file;
    GameEngine1 d_ge1;

    /**
     * Constructor to initialize the values
     * @param p_ge is the object of GameEngine1 class.
     * @param p_playerList is the HashMap of the players.
     * @param p_country is the object of the class Country.
     * @param p_file is the file name which we want to read.
     */
    AssignCountriesMain(GameEngine1 p_ge, ConcurrentHashMap<String, Player> p_playerList, Country p_country, File p_file) {
        super(p_ge);
        this.d_country = p_country;
        this.d_playerList = p_playerList;
        this.d_file = p_file;
        this.d_ge1 = p_ge;
    }

    @Override
    public void gamePlay() {

    }

    /**
     * Method to start assigning countries to the player.
     */
    public void assignCountries()
    {
        Assign l_a = new Assign(d_playerList, d_country);
        l_a.assignCountries(d_file);
        d_country = l_a.d_country;
    }

    /**
     * Method to change the phase.
     */
    public void next()
    {
        int l_x=0;
        for(String l_player: d_playerList.keySet())
        {
            if (d_playerList.get(l_player).d_owned.size()==0)
            {
                l_x=1;
                break;
            }
        }
        if(l_x==1)
        {
            System.out.println("You can not go to the next step without assigning countries to the player");
        }
        else {
            d_ge1.setPhase(new OrderIssuePhase(d_playerList, d_country, d_ge1));
        }
    }
}