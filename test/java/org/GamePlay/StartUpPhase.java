package org.GamePlay;

import org.StatePattern.GameEngine1;
import org.StatePattern.GameStartUpPhaseMain;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the Startup phase of the game.
 */
public class StartUpPhase {

    GameEngine1 d_ge1;
    GameStartUpPhaseMain d_gStartup;
    GameEngine d_ge;

    /**
     *Before Method to initialize the values before the actual test runs.
     */
    @Before
    public void beforeStartUp()
    {
        d_ge1 = new GameEngine1();
        d_gStartup = new GameStartUpPhaseMain(d_ge1);
        d_ge = new GameEngine();
        d_ge1.setPhase(d_gStartup);
        d_gStartup.d_ge = d_ge;
        d_gStartup.d_ge.d_FILE = null;
    }

    /**
     * Test Method to run the actual test of the StartUp Phase of the Game. It checks if the FILE variable is null or not.
     * If null then it should not continue further as we need to have some loaded map to continue further.
     */
    @Test
    public void checkStartUpPhase()
    {
        d_gStartup.next();
        assertEquals("You first need to load the map before going further", d_gStartup.d_message);
    }
}
