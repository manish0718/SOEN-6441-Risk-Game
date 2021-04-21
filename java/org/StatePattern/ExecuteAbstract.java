package org.StatePattern;

/**
 * Abstract class to execute the orders.
 */
public abstract class ExecuteAbstract extends Phase{
    /**
     * Constructor to call the super constructor.
     * @param p_ge object of GameEngine1 class
     */
    ExecuteAbstract(GameEngine1 p_ge) {
        super(p_ge);
    }

    /**
     * Method to print Invalid command Error if user enters playGame command in between the execute order phase.
     */
    @Override
    public void playGame() {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid command Error if user wants to restart the GameEngine in between the execute order phase.
     */
    @Override
    public void startGameEngine() {

    }

    /**
     * Method to print Invalid command Error if user enters playGame command in between the execute order phase.
     */
    @Override
    public int playGameLoop() {
        return 0;
    }

    /**
     * Method to print Invalid command Error if user enters mainGameLoop command in between the execute order phase.
     */
    @Override
    public void mainGameLoop() {

    }

    /**
     * Method to print Invalid command Error if user enters assign countries command in between the execute order phase.
     */
    @Override
    public void assignCountries() {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid command Error if user enters editMap command in between the execute order phase.
     */
    @Override
    public void EditMap() throws Exception {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid command Error if user wants to see the Map command in between the execute order phase.
     */
    @Override
    public void ShowMaps() throws Exception {
        printInvalidCommandMessage();
    }

}
