package org.GamePlay;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DeployOrderTest {

    Country l_cou1,l_cou2,l_cou;
    DeployOrder d_order;
    Player d_p;
    @Before
    public void setUp()
    {
        d_p= new Player("Nitpreet");

        l_cou1 = new Country("d","b",5,3,new ArrayList<>(Arrays.asList("d","e")));
        l_cou2 = new Country("e","c",5,3,new ArrayList<>(Arrays.asList("d","e")));
        l_cou.COUNTRIESLIST.put("d",l_cou1);
        l_cou.COUNTRIESLIST.put("e",l_cou2);
        d_order = new DeployOrder("d",10,l_cou);

        d_p.d_owned.add(l_cou1);
        d_p.d_armiesNum=4;
        d_p.d_owned.add(l_cou2);
        d_p.issue_orders(d_order);
    }

    @Test
    public void executeTest() {
        d_order.Execute(d_p);

        assertEquals("You only own 4 armies",d_order.d_temp);
    }
}