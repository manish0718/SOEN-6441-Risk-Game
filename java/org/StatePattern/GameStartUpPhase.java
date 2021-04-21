package org.StatePattern;

/**
 * Abstract class for the StartUp phase of the Game.
 */
public abstract class GameStartUpPhase extends Phase {
    /**
     * Constructor to call the super constructor.
     * @param p_ge is the Object of GameEngine1.
     */
    GameStartUpPhase(GameEngine1 p_ge)
    {
        super(p_ge);
    }

    /**
     * Method to print the Invalid Command Message if user wants to start the GamePlay before StartUp Phase.
     * @return 0 as it is not required.
     */
    public int playGameLoop(){
        printInvalidCommandMessage();
        return 0;
    }

    /**
     * Method to print the Invalid Command Message if user wants to start the assign the countries
     * before StartUp Phase that is before the loading the Map.
     */
    public void assignCountries()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print the Invalid Command Message if user wants to execute the orders
     * before StartUp Phase that is before the loading the Map.
     */
    public void execute()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print the Invalid Command Message if user wants to start the Main Game Loop
     * before StartUp Phase that is before the loading the Map.
     */
    public void mainGameLoop()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print the Invalid Command Message if user wants to Edit the map in between the
     * StartUp Phase that is before the loading the Map.
     */
    public void EditMap(){
        printInvalidCommandMessage();
    }

    /**
     * Method to print the Invalid Command Message if user wants to see the map
     * before StartUp Phase that is before the loading the Map.
     */
    public void mapshow()
    {
        printInvalidCommandMessage();
    }

    /**
     * Method to print the Invalid Command Message if user wants to see the Map
     * before StartUp Phase that is before the loading the Map.
     */
    public void ShowMaps(){
        printInvalidCommandMessage();
    }
}
