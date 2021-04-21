package org.StatePattern;

/**
 * This class is the abstract class of ShowMap phase class.
 */
public abstract class ShowMapAbstract extends Phase{

    /**
     * This is the constructor of this ShowMap abstract class.
     * @param p_ge This is the object of the GameEngine1 class use to set phase.
     */
    ShowMapAbstract(GameEngine1 p_ge) {
        super(p_ge);
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public void playGame(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public void startGameEngine(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public void EditMap(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public int playGameLoop(){
        printInvalidCommandMessage();
        return 0;
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public void mainGameLoop(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public void assignCountries(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public void execute(){
        printInvalidCommandMessage();
    }
    /**
     * This method when called in ShowMap phase will throw InvalidCommandMessage.
     */
    public void mapshow(){
        printInvalidCommandMessage();
    }
}

