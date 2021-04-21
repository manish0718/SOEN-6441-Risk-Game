package org.StatePattern;

/**
 * This is a main class from where the whole game runs.
 */

public class GameDriver {

    /**
     * This is the main function of the project.
     * @param args This contains the supplied command-line arguments as an array of String objects
     * @throws Exception It's a file not found exception.
     */
    public static void main(String[] args) throws Exception {
        GameEngine1 l_ge = new GameEngine1();
        l_ge.start();
    }
}