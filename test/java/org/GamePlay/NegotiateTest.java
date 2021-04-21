package org.GamePlay;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;


/**
 * This is the class in which the Negotiate order validity is Checked.
 */
public class NegotiateTest {

    Country d_con1 , d_con2 , d_con3 ;
    Cards d_card1 , d_card2;
    Player d_p1,d_p2,d_p3;
    ConcurrentHashMap<String,Player> d_playerList = new ConcurrentHashMap<>();

    /**
     * Method to initialize the values required for the execute Method of the Negotiate Order card.
     */
    @Before
    public void startUpPhase(){

        d_p1 = new Player("Manjit");
        d_p1.add_card("NEGOTIATE");

        d_p2 = new Player("Manish");
        d_p2.add_card("NEGOTIATE");

        d_p3 = new Player("Nitpreet");

        d_playerList.put("Manjit" , d_p1);
        d_playerList.put("Manish" , d_p2);
        d_playerList.put("Nitpreet" , d_p3);

        d_con1 = new Country("d","b",5,3,new ArrayList<>(Arrays.asList("d","e","f")));
        d_con2 = new Country("e","c",5,3,new ArrayList<>(Arrays.asList("d","e","f")));
        d_con3 = new Country("f","a",5,3,new ArrayList<>(Arrays.asList("d","e","f")));

        d_con3.COUNTRIESLIST.put("d", d_con1);
        d_con3.COUNTRIESLIST.put("e", d_con2);

        d_con1.d_numOfArmiesPlaced = 20;
        d_con2.d_numOfArmiesPlaced = 10;

        d_p1.d_owned.add(d_con1);
        d_p2.d_owned.add(d_con2);
        d_p3.d_owned.add(d_con3);

        d_card1 = new Cards(d_p2.d_name, "negotiate" , d_playerList);
        d_card2 = new Cards(d_p1.d_name, "negotiate" , d_playerList);

        d_p1.issue_orders(d_card1);
        d_p2.issue_orders(d_card2);
    }

    /**
     * Method to check the execute Method from the Negotiate Order card.
     * In this case the country using Negotiate card successfully executes.
     */
    @Test
    public void NegotiatePass(){
        d_card1.Execute(d_p1);

        assertEquals(true, d_p1.d_negotiate.contains(d_p2.d_name));
    }

    /**
     * Method to check the execute Method from the Negotiate Order card.
     * In this case the country using Negotiate card does not successfully executes.
     */
    @Test
    public void NegotiateFail(){
        d_card2.Execute(d_p1);

        assertEquals(false , d_p2.d_negotiate.contains(d_p3.d_name));
    }
}
