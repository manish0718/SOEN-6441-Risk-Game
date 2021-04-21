package org.StatePattern;

public class GamePlayAbstract extends Phase {
    /**
     * Constructor to call the super constructor.
     * @param p_ge is the Object of the GameEngine1 class.
     */
    GamePlayAbstract(GameEngine1 p_ge) {
        super(p_ge);
    }

    /**
     * Method to print Invalid message error if player wants to see the map while adding/removing the player
     */
    @Override
    public void mapshow()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid message error if player wants to start playing the Game
     * before adding/removing the player
     */
    @Override
    public void playGame() {
        printInvalidCommandMessage();
    }

    @Override
    public void startGameEngine() {

    }

    @Override
    public void gamePlay() {

    }

    /**
     * Method to print Invalid message error if player wants to start playing the game
     * before adding/removing the player
     */
    @Override
    public int playGameLoop() {
        return 0;
    }

    /**
     * Method to print Invalid message error if player wants to start playing the game
     * before adding/removing the player
     */
    @Override
    public void mainGameLoop() {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid message error if player wants to assign the countries in the game
     * before adding/removing the player
     */
    @Override
    public void assignCountries() {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid message error if player wants to Edit the Map
     * after it has been loaded.
     */
    @Override
    public void EditMap() throws Exception {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid message error if player wants to execute the orders of the player
     * before even adding/removing the player.
     */
    @Override
    public void execute()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print Invalid message error if player wants to see the map
     * after start of the GamePlay.
     */
    @Override
    public void ShowMaps() throws Exception {
        printInvalidCommandMessage();
    }

    @Override
    public void next() {

    }
}
