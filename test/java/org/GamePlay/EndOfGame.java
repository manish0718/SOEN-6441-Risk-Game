package org.GamePlay;

import org.ObserverBasedLogging.LogWriter;
import org.StatePattern.ExecuteMain;
import org.StatePattern.GameEngine1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

/**
 * Test Class to check if the game is running properly or not.
 */
public class EndOfGame {

    GameEngine1 ge1;
    ConcurrentHashMap <String, Player> playerList;
    Player p1,p2;
    Country country,c1,c2,c3;
    ExecuteMain ex1;

    /**
     * Before Method to initialize the required variables before running the actual tests.
     */
    @Before
    public void beforeInit()
    {
        LogWriter log = new LogWriter();
        playerList = new ConcurrentHashMap<>();
        ge1 = new GameEngine1();
        p1 = new Player("Nitpreet");

        c1 = new Country("a","Z",5,2,new ArrayList(Arrays.asList("b")));
        p1.d_owned.add(c1);

        c2 = new Country("b","Z",5,2,new ArrayList(Arrays.asList("a")));
        p1.d_owned.add(c2);

        country.COUNTRIESLIST.put("a",c1);
        country.COUNTRIESLIST.put("b",c2);


        p2 = new Player("Taneja");

        p2.d_owned = new ArrayList<>();

        playerList.put("Nitpreet",p1);
        playerList.put("Taneja",p2);
        ex1 = new ExecuteMain(ge1,playerList,country);
        ge1.setPhase(ex1);
    }

    /**
     * Test method to check if the game still continues if only one player has all the countries
     * and other players has no countries at all. This clearly signifies that the first player is the winner of the Game.
     */
    @Test
    public void EndGametest()
    {
        ex1.next();
        assertEquals("Nitpreet is the winner of the game!!!!!!!!",ex1.d_message);
    }
}
