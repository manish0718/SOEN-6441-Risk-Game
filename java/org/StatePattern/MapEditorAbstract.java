package org.StatePattern;

/**
 * This class is the abstract class of MapEditor phase class.
 */
public abstract class MapEditorAbstract extends Phase{

    /**
     * This is the constructor of this MapEditor abstract class.
     * @param p_ge This is the object of the GameEngine1 class use to set phase.
     */
    MapEditorAbstract(GameEngine1 p_ge) {
        super(p_ge);
    }

    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public void playGame(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public void startGameEngine(){
        printInvalidCommandMessage();
    }

    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public int playGameLoop(){
        printInvalidCommandMessage();
        return 0;
    }
    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public void mainGameLoop(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public void assignCountries(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public void execute(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public void next(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in MapEditor phase will throw InvalidCommandMessage.
     */
    public void mapshow(){
        printInvalidCommandMessage();
    }

}
