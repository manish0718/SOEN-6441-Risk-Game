package org.GamePlay;

import org.Tournament.TournamentMainTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Class for generating the Suite for the GamePlay
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdvanceArmiesTest.class,
        AirliftTest.class,
        BlockadeTest.class,
        BombTest.class,
        checkPhaseChange.class,
        DeployOrderTest.class,
        EndOfGame.class,
        MapValidation.class,
        NegotiateTest.class,
        playGameTest.class,
        StartUpPhase.class,
        TournamentMainTest.class
})
public class GamePlaySuite {
}
