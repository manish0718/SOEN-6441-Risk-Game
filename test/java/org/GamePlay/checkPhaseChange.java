package org.GamePlay;

import org.StatePattern.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Class to check whether the phase changes or not.
 */
public class checkPhaseChange {

    GameEngine1 d_ge1;
    GameStartUpPhaseMain d_obj;

    /**
     * Before method to initialize the values before starting of the test cases.
     */
    @Before
    public void before()
    {
        d_ge1 = new GameEngine1();
        d_obj =  new GameStartUpPhaseMain(d_ge1);
    }

    /**
     * Test case to check which phase is ongoing.
     */
    @Test
    public void check()
    {
        d_ge1.setPhase(d_obj);
        assertEquals("new phase: GameStartUpPhaseMain", d_ge1.MESSAGE);
    }

    /**
     * Test case to check if the phase has changed or not.
     */
    @Test
    public void checkNext()
    {
        d_obj.d_ge = new GameEngine();
        d_obj.d_ge.d_FILE = new File("C:\\Final Project\\src\\test\\resources\\maps\\h.map");
        d_obj.next();
        assertEquals("new phase: PlayerInitializationMain", d_ge1.MESSAGE);
    }
}
