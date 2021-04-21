package org.GamePlay;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * This is the class in which the Bomb order validity is Checked.
 */
public class BombTest {

    Country d_con1 , d_con2 , d_con3;
    Cards d_card1 , d_card2;
    Player d_p1,d_p2;

    /**
     * Method to initialize the values required for the execute Method of the Bomb Order card.
     */
    @Before
    public void startUpPhase(){

        d_p1 = new Player("Manjit");
        d_p1.add_card("BOMB");
        d_p2 = new Player("Manish");
        d_p2.add_card("BOMB");

        d_con1 = new Country("d","b",5,3,new ArrayList<>(Arrays.asList("d","e")));
        d_con2 = new Country("e","b",5,3,new ArrayList<>(Arrays.asList("d","e")));

        d_con3.COUNTRIESLIST.put("d", d_con1);
        d_con3.COUNTRIESLIST.put("e", d_con2);

        d_con1.d_numOfArmiesPlaced = 20;
        d_con2.d_numOfArmiesPlaced = 10;

        d_p1.d_owned.add(d_con1);
        d_p2.d_owned.add(d_con2);

        d_card1 = new Cards("e" , "bomb" , d_con3);
        d_card2 = new Cards("e" , "bomb" , d_con3);

        d_p1.issue_orders(d_card1);
        d_p2.issue_orders(d_card2);
    }

    /**
     * Method to check the execute Method from the Bomb Order card.
     * In this case the country using Bomb card successfully executes.
     */
    @Test
    public void BombPass(){
        d_card1.Execute(d_p1);

        assertEquals(5, (int) d_con2.COUNTRIESLIST.get("e").d_numOfArmiesPlaced);
    }

    /**
     * Method to check the execute Method from the Bomb Order card.
     * In this case the country using Bomb card does not successfully executes.
     */
    @Test
    public void BombFail(){
        d_card2.Execute(d_p2);

        assertEquals(10 , (int) d_con2.COUNTRIESLIST.get("e").d_numOfArmiesPlaced);
    }
}
