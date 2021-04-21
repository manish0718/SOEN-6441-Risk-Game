package org.StatePattern;


import java.io.File;

public abstract class Phase {
    GameEngine1 ge;

    Phase(GameEngine1 p_ge) {
        ge = p_ge;
    }

    //Loadmap
    abstract public void playGame();
    //Initialize Player, Assign Countries;
    abstract public void startGameEngine();
    abstract public void gamePlay();

    //Reinforcement:
    abstract public int playGameLoop();
    //Issue Order--> Order Execution:
    abstract public void mainGameLoop();
    abstract public void assignCountries();
    abstract public void execute();
    abstract public void mapshow();

    abstract public void EditMap() throws Exception;
    abstract public void ShowMaps() throws Exception;
    abstract public void next();
    /**
     *  Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
    }
}
