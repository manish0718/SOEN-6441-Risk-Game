package org.GamePlay;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * This is the class in which the Blockade order validity is Checked.
 */
public class BlockadeTest {

    Country d_con1 , d_con2 , d_con3;
    Cards d_card1 , d_card2;
    Player d_p1 , d_p2;

    /**
     * Method to initialize the values required for the execute Method of the Blockade Order card.
     */
    @Before
    public void startUpPhase(){

        d_p1 = new Player("Manjit");
        d_p1.add_card("BLOCKADE");
        d_p2 = new Player("Manish");
        d_p2.add_card("BLOCKADE");

        d_con1 = new Country("d","b",5,3,new ArrayList<>(Arrays.asList("d","e")));
        d_con2 = new Country("e","b",5,3,new ArrayList<>(Arrays.asList("d","e")));

        d_con3.COUNTRIESLIST.put("d", d_con1);
        d_con3.COUNTRIESLIST.put("e", d_con2);

        d_con1.d_numOfArmiesPlaced = 20;
        d_con2.d_numOfArmiesPlaced = 10;

        d_p1.d_owned.add(d_con1);

        d_card1 = new Cards("d" , "blockade" , d_con3);
        d_card2 = new Cards("e" , "blockade" , d_con3);

        d_p1.issue_orders(d_card1);
        d_p2.issue_orders(d_card2);
    }

    /**
     * Method to check the execute Method from the Blockade card.
     * In this case the player using the Blockade card successfully executes it as it uses it on its own country.
     */
    @Test
    public void BlockadePass(){
        d_card1.Execute(d_p1);

        assertEquals(60, (int) d_con1.COUNTRIESLIST.get("d").d_numOfArmiesPlaced);
    }

    /**
     * Method to check the execute Method from the Blockade card.
     * In this case the player using the Blockade card does not successfully executes it as it uses it on someone else's country.
     */
    @Test
    public void BlockadeFail(){
        d_card2.Execute(d_p2);

        assertEquals(10 , (int) d_con2.COUNTRIESLIST.get("e").d_numOfArmiesPlaced);
    }
}
