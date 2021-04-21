package org.StatePattern;

/**
 * Abstract class for Assigning countries to player
 */
public abstract class AssignCountriesAbstract extends Phase {
    /**
     * Constructor to call the super constructor.
     * @param p_ge object of GameEngine1 class
     */
    AssignCountriesAbstract(GameEngine1 p_ge) {
        super(p_ge);
    }

    /**
     * This method will show Invalid command error if user enters play Game command.
     */
    @Override
    public void playGame() {
    printInvalidCommandMessage();
    }

    /**
     * This method will show Invalid command error if user wants to restart GameEngine.
     */
    @Override
    public void startGameEngine() {
    printInvalidCommandMessage();
    }

    /**
     * This method will show Invalid command error if user wants to restart GamePlay.
     */
    @Override
    public int playGameLoop() {
        printInvalidCommandMessage();
        return 0;
    }

    /**
     * This method will show Invalid command error if user wants to restart the Game.
     */
    @Override
    public void mainGameLoop() {
    printInvalidCommandMessage();
    }

    /**
     * This method will show Invalid command error if user wants to edit the map.
     */
    @Override
    public void EditMap() throws Exception {
    printInvalidCommandMessage();
    }

    /**
     * This method will show Invalid command error if user wants to see the Map.
     */
    @Override
    public void ShowMaps() throws Exception {
    printInvalidCommandMessage();
    }
    /**
     * This method will show Invalid command error if user wants to executes the order.
     */
    public void execute()
    {
        printInvalidCommandMessage();
    }

    /**
     * This method will show Invalid command error if user wants see the map.
     */
    public void mapshow()
    {
        printInvalidCommandMessage();
    }
}
