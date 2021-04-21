package org.StatePattern;

import org.GamePlay.GameEngine;

public class GamePlayMain extends GamePlayAbstract{
    GameEngine d_ge;
    GameEngine1 d_ge1;

    /**
     * Constructor to initialize the values.
     * @param p_ge is the Object of the class GameEngine1.
     * @param p_ge1 is the Object of the class GameEngine
     */
    public GamePlayMain(GameEngine1 p_ge,GameEngine p_ge1) {
        super(p_ge);
        this.d_ge1 = p_ge;
        this.d_ge = p_ge1;
    }

    /**
     * Method to start the GameEngine.
     */
    @Override
    public void gamePlay() {
        d_ge.gamePlay(d_ge.d_FILE);
    }

    /**
     * Method to change the Phase. If the size of the
     */
    public void next() {

        d_ge1.setPhase(new End(new GameEngine1()));

    }
}
