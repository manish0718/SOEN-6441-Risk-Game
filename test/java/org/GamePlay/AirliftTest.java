package org.GamePlay;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * This is the class in which the Airlift Armies order validity is Checked.
 */
public class AirliftTest {
    Country d_con1 , d_con2 , d_con3 , d_con4 , d_con5;
    AdvanceArmies d_airlift1 , d_airlift2;
    Player d_player1 , d_player2 , d_player3 , d_player4;

    /**
     * Method to initialize the values required for the execute Method of the Airlift Order card.
     */
    @Before
    public void startUpPhase(){

        d_player1= new Player("Manjit");
        d_player1.d_armiesNum = 10;

        d_player2 = new Player("Manish");
        d_player2.d_armiesNum = 5;

        d_player3 = new Player("Nitpreet");
        d_player3.d_armiesNum = 8;

        d_player4 = new Player("Arunesh");
        d_player4.d_armiesNum = 4;

        d_con1 = new Country("d","b",5,3,new ArrayList<>(Arrays.asList("d","e","f","g")));
        d_con2 = new Country("e","c",5,3,new ArrayList<>(Arrays.asList("d","e","f","g")));
        d_con4 = new Country("f" , "c" , 5 , 3 , new ArrayList(Arrays.asList("d","e","f","g")));
        d_con5 = new Country("g" , "b" , 5 , 3 , new ArrayList(Arrays.asList("d","e","f","g")));

        d_con3.COUNTRIESLIST.put("d", d_con1);
        d_con3.COUNTRIESLIST.put("e", d_con2);
        d_con3.COUNTRIESLIST.put("f",d_con4);
        d_con3.COUNTRIESLIST.put("g",d_con5);

        d_con1.d_numOfArmiesPlaced = 4;
        d_con2.d_numOfArmiesPlaced = 2;
        d_con4.d_numOfArmiesPlaced = 4;
        d_con5.d_numOfArmiesPlaced = 3;

        d_airlift1 = new AdvanceArmies("d" , "e" , 4 , d_con3 , "AIRLIFT");
        d_airlift2 = new AdvanceArmies("f" , "g", 4 , d_con3 , "AIRLIFT");

        d_player1.d_owned.add(d_con1);
        d_player2.d_owned.add(d_con2);
        d_player3.d_owned.add(d_con4);
        d_player4.d_owned.add(d_con5);

        d_player1.issue_orders(d_airlift1);
        d_player3.issue_orders(d_airlift2);

    }

    /**
     * Method to check the execute Method from the Airlift Order card.
     * In this case the country using airlift card wins.
     */
    @Test
    public void airliftTestWin(){

        d_airlift1.Execute(d_player1);

        assertEquals(true,d_player1.d_owned.contains(d_con2.COUNTRIESLIST.get("e")));
    }

    /**
     * Method to check the execute Method from the Airlift Order card.
     * In this case the player using airlift card loses.
     */
    @Test
    public void airliftTestLose(){

        d_airlift2.Execute(d_player3);

        assertEquals(false,d_player3.d_owned.contains(d_con5.COUNTRIESLIST.get("g")));
    }
}
