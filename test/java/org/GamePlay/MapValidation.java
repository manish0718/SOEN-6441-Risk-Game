package org.GamePlay;

import org.GamePlay.GameEngine.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Class to test if the map is validated or not.
 */
public class MapValidation {

    GameEngine ge,ge1;

    /**
     * Method to set the values before running the actual tests.
     */
    @Before
    public void set()
    {
        ge = new GameEngine();
        ge.d_filename = "n.map";
        ge.d_FILE = new File(".\\src\\test\\resources\\maps\\n.map");


        ge1 = new GameEngine();
        ge1.d_filename = "bigeurope.map";
        ge1.d_FILE = new File(".\\src\\test\\resources\\maps\\bigeurope.map");
    }

    /**
     * Test case to check if the map provided is Invalid or not.
     */
    @Test
    public void executeValidation()
    {
        ge.playGame();
        assertEquals("Invalid Map",ge.d_message);

    }

    /**
     * Test case to check if the map provided is Valid or not.
     */
    @Test
    public void validMap()
    {
        ge1.playGame();
        assertEquals("Map Validated",ge1.d_message);
    }
}
