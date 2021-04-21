package org.StatePattern;

import org.GamePlay.GameEngine;

/**
 * Main Class to initialize the Players.
 */
public class PlayerInitializationMain extends PlayerInitializationAbstract{

    GameEngine d_ge;
    GameEngine1 d_ge1;

    /**
     * Constructor to initialize the values.
     * @param p_ge is the Object of the class GameEngine1.
     * @param p_ge1 is the Object of the class GameEngine
     */
    public PlayerInitializationMain(GameEngine1 p_ge,GameEngine p_ge1) {
        super(p_ge);
        this.d_ge1 = p_ge;
        this.d_ge = p_ge1;
    }

    /**
     * Method to start the GameEngine.
     */
    @Override
    public void startGameEngine() {
        d_ge.startGameEngine(d_ge.d_FILE);
    }

    @Override
    public void gamePlay() {

    }

    /**
     * Method to change the Phase. If the size of the
     */
    public void next() {
        boolean l_temp=true;
        try{
            if(d_ge.d_PLAYERS_LIST.size()>=2)
            {
                l_temp=false;
            }
        }catch (Exception e)
        {}
        if(l_temp==false)
        {
            d_ge1.setPhase(new GamePlayMain(d_ge1, d_ge));
        }
        else
        {
            System.out.println("There must be at least 2 players to continue");
        }
    }
}
