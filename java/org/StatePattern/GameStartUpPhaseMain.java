package org.StatePattern;

import org.GamePlay.GameEngine;

/**
 * Main class to start the GameStartUp.
 */
public class GameStartUpPhaseMain extends GameStartUpPhase {
    /**
     * Object of the GameEngine class.
     */
    public GameEngine d_ge;
    GameEngine1 d_ge1;
    /**
     * Variable of type String to store the message.
     */
    public String d_message;

    /**
     * Constructor to call the super constructor.
     * @param p_ge is the object of GameEngine1 class.
     */
    public GameStartUpPhaseMain(GameEngine1 p_ge)
    {
        super(p_ge);
        this.d_ge1 = p_ge;
    }

    /**
     * Method to start the game Engine.
     */
    @Override
    public void playGame() {
        d_ge = new GameEngine();
        d_ge.playGame();
    }

    /**
     * Method to print the Invalid Command Message if player enters the wrong command.
     */
    @Override
    public void startGameEngine() {
        printInvalidCommandMessage();
    }

    @Override
    public void gamePlay() {

    }

    /**
     * Method to change the phase to the next phase. If the map is not loaded then it will show
     * an error saying to load the map first else it will change the Phase to Player Initialization.
     */
    public void next()
    {
        if(d_ge.d_FILE ==null)
        {
            d_message ="You first need to load the map before going further";
            System.out.println(d_message);
        }
        else
        {
            d_ge1.setPhase(new PlayerInitializationMain(d_ge1, d_ge));
        }
    }
}
