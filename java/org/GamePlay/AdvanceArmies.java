package org.GamePlay;

import org.ObserverBasedLogging.LogEntryBuffer;
import org.ObserverBasedLogging.LogFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Child class extending the Order Class , it deals with advance Order and airlift order command
 */
public class AdvanceArmies extends Order implements Serializable {
    private static final long serialVersionUID = -1691590954228314787L;
    String d_FromCountry;
    String d_ToCountry;
    Integer d_armies;
    Country d_cou;
    String d_card;
    double d_probOfAttackersSuccess = 0.6;
    double d_probOfDefendersSuccess = 0.7;
    /**
     * HashMap to store the name of the Player as the Key and object of Player class as the Value.
     */
    public static ConcurrentHashMap<String, Player> PLAYER_LIST = new ConcurrentHashMap<String, Player>();
    ArrayList<String> d_cards = new ArrayList<>();

    /**
     * Constructor to initialise Advance/Airlift order objects
     * @param p_FromCountry Country from where we want to move armies
     * @param p_ToCountry  Country to which we want to move armies
     * @param p_armies  No. of armies we want to move
     * @param p_card It checks whether the command is airlift or advance
     * @param p_cou  It is the country class object
     * @param p_playerList It is the list of players currently in the game
     */
    public AdvanceArmies(String p_FromCountry, String p_ToCountry, Integer p_armies, String p_card, Country p_cou , ConcurrentHashMap<String, Player> p_playerList){
        this.d_FromCountry = p_FromCountry;
        this.d_ToCountry = p_ToCountry;
        this.d_armies = p_armies;
        this.d_card = p_card;
        this.d_cou = p_cou;
        this.PLAYER_LIST = p_playerList;
    }

    /**
     * Constructor to initialise Advance/Airlift order objects
     * @param p_FromCountry Country from where we want to move armies
     * @param p_ToCountry Country to which we want to move armies
     * @param p_armies No. of armies we want to move
     * @param p_cou It is the country class object
     * @param p_card It checks whether the command is airlift or advance
     */
    public AdvanceArmies(String p_FromCountry, String p_ToCountry, Integer p_armies, Country p_cou , String p_card){
        this.d_FromCountry = p_FromCountry;
        this.d_ToCountry = p_ToCountry;
        this.d_armies = p_armies;
        this.d_cou = p_cou;
        this.d_card = p_card;
    }

    /**
     * Implementation of Execute in Advance Order and Airlift Order
     *
     * @param p_p Player
     */
    @Override
    void Execute(Player p_p) {

        LogEntryBuffer l_observable = new LogEntryBuffer();
        LogFile l_observer = new LogFile();
        l_observable.addObserver(l_observer);
        String l_message;
        d_cards.add("BOMB");
        d_cards.add("BLOCKADE");
        d_cards.add("AIRLIFT");
        d_cards.add("NEGOTIATE");

        int l_range = 4;
        String l_separator = "***********************************************************************************************************";


        if(d_card !="airlift"){
            String l_order ="ORDER : " + p_p.d_name +" gave order :"+" Advance armies "+ d_armies +" from " + d_FromCountry +" to "+ d_ToCountry;

            if(!p_p.d_negotiate.contains(d_cou.COUNTRIESLIST.get(d_ToCountry).d_owner)) {
                int l_attackersArmy = d_armies;
                int l_defendersArmy = d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced;

                if (p_p.d_owned.contains(d_cou.COUNTRIESLIST.get(d_ToCountry))) {
                    d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced += d_armies;
                    System.out.println(l_separator);
                    System.out.println(l_order);
                    l_message  = "EFFECT : As the " + d_ToCountry + " also belongs to " + p_p.d_name + " we simply add the armies to the armies of this country";
                    System.out.println(l_message);
                    System.out.println(l_separator);
                    l_observable.setMsg(l_message);
                }
                else {

                    if (l_attackersArmy <= l_defendersArmy) {
                        int l_armiesLeft = (int) Math.floor(l_defendersArmy - ((d_probOfAttackersSuccess * l_attackersArmy) / d_probOfDefendersSuccess));
                        if(l_armiesLeft<0){
                            l_armiesLeft=0;
                        }
                        d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced -= d_armies;
                        if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced<0){
                            d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced=0;
                        }
                        d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced = l_armiesLeft;
                        System.out.println(l_separator);
                        System.out.println(l_order);
                        l_message = "EFFECT : As attackers armies are not sufficient , they will lose and the armies left of the " + d_ToCountry + " after the war will be " + l_armiesLeft;
                        System.out.println(l_message);
                        System.out.println(l_separator);
                        l_observable.setMsg(l_message);

                    } else {
                        int l_k = (int) Math.floor(d_probOfAttackersSuccess * l_attackersArmy / d_probOfDefendersSuccess);

                        if (l_k > l_defendersArmy) {
                            d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced -= d_armies;
                            if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced=0;
                            }
                            d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced = d_armies - (int) Math.floor(d_probOfDefendersSuccess * l_defendersArmy / d_probOfAttackersSuccess);
                            if(d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced=0;
                            }
                            if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced==0 && d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced==0){
                                System.out.println(l_separator);
                                System.out.println(l_order);
                                l_message = "EFFECT : Nobody won as after attack no armies are left of both the countries";
                                System.out.println(l_message);
                                System.out.println(l_separator);
                                l_observable.setMsg(l_message);
                            }
                            else{
                                for (String l_s : PLAYER_LIST.keySet()) {
                                    Player l_p = PLAYER_LIST.get(l_s);
                                    if (l_p.d_owned.contains(d_cou.COUNTRIESLIST.get(d_ToCountry))) {
                                        l_p.d_owned.remove(d_cou.COUNTRIESLIST.get(d_ToCountry));
                                    }

                                }
                                p_p.d_owned.add(d_cou.COUNTRIESLIST.get(d_ToCountry));
                                d_cou.COUNTRIESLIST.get(d_ToCountry).d_owner = p_p.d_name;
                                System.out.println(l_separator);
                                System.out.println(l_order);
                                l_message = "EFFECT : " +d_FromCountry + " won and armies left are " + d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced;
                                System.out.println(l_message);
                                System.out.println(l_separator);
                                l_observable.setMsg(l_message);
                                System.out.println(l_separator);
                                l_message = "Giving cards to " + p_p.d_name;
                                System.out.println(l_message);
                                System.out.println(l_separator);
                                l_observable.setMsg(l_message);
                                int l_rand = (int) (Math.random() * l_range);

                                p_p.d_cards.add(d_cards.get(l_rand));

                                System.out.println(p_p.d_cards);
                                l_observable.setMsg(l_message+" "+p_p.d_cards);
                            }

                        } else {
                            d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced -= l_k;
                            if(d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced=0;
                            }
                            d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced -= d_armies;
                            if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced=0;
                            }
                            System.out.println(l_separator);
                            System.out.println(l_order);
                            l_message ="EFFECT : " +  d_ToCountry + " won and armies left are " + d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced;
                            System.out.println(l_message);
                            System.out.println(l_separator);
                            l_observable.setMsg(l_message);
                        }
                    }
                }
            }
            else {
                System.out.println(l_separator);
                System.out.println(l_order);
                l_message = "EFFECT : Cannot advance armies to this country as you have negotiated with its owner";
                System.out.println(l_message);
                System.out.println(l_separator);
                l_observable.setMsg(l_message);
            }
        }
        else{
            String l_order = p_p.d_name +" gave order :"+" Airlift armies "+ d_armies +" from " + d_FromCountry +" to "+ d_ToCountry;
            if(!p_p.d_negotiate.contains(d_cou.COUNTRIESLIST.get(d_ToCountry).d_owner)) {
                int l_attackersArmy = d_armies;
                int l_defendersArmy = d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced;

                if (p_p.d_owned.contains(d_cou.COUNTRIESLIST.get(d_ToCountry))) {
                    d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced += d_armies;
                    System.out.println(l_separator);
                    System.out.println(l_order);
                    l_message = "EFFECT : As the " + d_ToCountry + " also belongs to " + p_p.d_name + " we simply add the armies to the armies of this country";
                    System.out.println(l_message);
                    System.out.println(l_separator);
                    l_observable.setMsg(l_message);
                }
                else {

                    if (l_attackersArmy <= l_defendersArmy) {

                        int l_armiesLeft = (int) Math.floor(l_defendersArmy - ((d_probOfAttackersSuccess * l_attackersArmy) / d_probOfDefendersSuccess));
                        if(l_armiesLeft<0){
                            l_armiesLeft=0;
                        }
                        d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced -= d_armies;
                        if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced<0){
                            d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced=0;
                        }
                        d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced = l_armiesLeft;
                        System.out.println(l_separator);
                        System.out.println(l_order);
                        l_message = "EFFECT : As attackers armies are less , they will lose and the armies left of the " + d_ToCountry + " after the war will be " + l_armiesLeft;
                        System.out.println(l_message);
                        System.out.println(l_separator);
                        l_observable.setMsg(l_message);

                    } else {
                        int l_k = (int) Math.floor(d_probOfAttackersSuccess * l_attackersArmy / d_probOfDefendersSuccess);

                        if (l_k > l_defendersArmy) {
                            d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced -= d_armies;
                            if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced=0;
                            }
                            d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced = d_armies - (int) Math.floor(0.7 * l_defendersArmy / d_probOfAttackersSuccess);
                            if(d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced=0;
                            }
                            if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced==0 && d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced==0){
                                System.out.println(l_separator);
                                System.out.println(l_order);
                                l_message = "EFFECT : Nobody won as after attack no armies are left of both the countries";
                                System.out.println(l_message);
                                System.out.println(l_separator);
                                l_observable.setMsg(l_message);
                            }
                            else{
                                for (String l_s : PLAYER_LIST.keySet()) {
                                    Player l_p = PLAYER_LIST.get(l_s);
                                    if (l_p.d_owned.contains(d_cou.COUNTRIESLIST.get(d_ToCountry))) {
                                        l_p.d_owned.remove(d_cou.COUNTRIESLIST.get(d_ToCountry));
                                    }
                                }
                                p_p.d_owned.add(d_cou.COUNTRIESLIST.get(d_ToCountry));
                                d_cou.COUNTRIESLIST.get(d_ToCountry).d_owner = p_p.d_name;
                                System.out.println(l_separator);
                                System.out.println(l_order);
                                l_message = "EFFECT : " +d_FromCountry + " won and armies left are " + d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced;
                                System.out.println(l_message);
                                System.out.println(l_separator);
                                l_observable.setMsg(l_message);

                                int l_rand = (int) (Math.random() * l_range);

                                p_p.d_cards.add(d_cards.get(l_rand));

                            }

                        } else {
                            d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced -= l_k;
                            if(d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced=0;
                            }
                            d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced -= d_armies;
                            if(d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced<0){
                                d_cou.COUNTRIESLIST.get(d_FromCountry).d_numOfArmiesPlaced=0;
                            }
                            System.out.println(l_separator);
                            System.out.println(l_order);
                            l_message = "EFFECT : " + d_ToCountry + " won and armies left are " + d_cou.COUNTRIESLIST.get(d_ToCountry).d_numOfArmiesPlaced;
                            System.out.println(l_message);
                            System.out.println(l_separator);
                            l_observable.setMsg(l_message);
                        }
                    }
                }
            }
            else {
                System.out.println(l_separator);
                System.out.println(l_order);
                l_message = "EFFECT : Cannot airlift armies this country as you have negotiated with its owner";
                System.out.println(l_message);
                System.out.println(l_separator);
                l_observable.setMsg(l_message);
            }
        }
    }
}
