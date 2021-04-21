package org.StrategyPattern;

import org.GamePlay.*;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Aggressive player strategy class
 */
public class AggressivePlayerStrategy extends PlayerStrategy{

    Country d_country;

    /**
     * Aggressive strategy constructor being initialized
     * @param p_player Player class object
     * @param p_Countries It is a hashmap which has a key as country name and value as object of its key.
     * @param p_country Country class object
     */
    public AggressivePlayerStrategy(Player p_player, ConcurrentHashMap<String, Country> p_Countries, Country p_country){
        super(p_player, p_Countries);
        this.d_country=p_country;
    }

    /**
     *	Determines the country to attack/move to.
     *	The Aggressive player determines this randomly
     *	@return random territory
     */
    @Override
    protected Country toAttack() {
        int l_num=0;
        Country l_attack =null;
        for(Country c :d_player.d_owned){
            for(String n: c.d_neighbours){
                if(l_num>=d_country.COUNTRIESLIST.get(n).d_numOfArmiesPlaced && d_player.d_owned.contains(d_country.COUNTRIESLIST.get(n))){
                    l_attack = d_country.COUNTRIESLIST.get(n);
                    l_num = d_country.COUNTRIESLIST.get(n).d_numOfArmiesPlaced;
                }
            }
        }
        if(l_attack==null){
            l_attack = d_country.COUNTRIESLIST.get(d_country.COUNTRIESLIST.keySet().stream().findFirst().get());
        }
        return l_attack;
    }


    /**
     *	Determines the country to attack from which country.
     *	The Aggressive player determines this randomly.
     *	@return null
     */
    @Override
    protected Country toAttackFrom() {
        return null;
    }


    /**
     *	Determines the country to move from.
     *	The Aggressive player does not use this, so it returns null.
     *	@return null
     */
    @Override
    protected Country toMoveFrom() {
        return null;
    }


    /**
     *	Determines the country to attack from.
     *	The Aggressive player does not use this, so it returns null.
     *	@return null
     */
    @Override
    protected Country toDefend() {
        int l_num = 0;
        Country l_defend = null;
        for(Country l_c : d_player.d_owned){
            if( l_num <= l_c.d_numOfArmiesPlaced){
                l_defend = l_c;
                l_num = l_c.d_numOfArmiesPlaced;
            }
        }
        return l_defend;
    }

    /**
     *	Creates and order.
     *	The Aggressive player can either deploy or advance, determined randomly. .
     *	@return Order class object
     */
    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        int l_rnd_num_of_armies = l_rand.nextInt(d_player.d_armiesNum);
        if (l_rand.nextInt(5) != 0) {
            switch (l_rand.nextInt(2)) {
                case (0):
                    return new DeployOrder(d_player.getD_name(), l_rnd_num_of_armies, toDefend());

                case (1):
                    return new AdvanceArmies(toDefend().d_countryId, toAttack().d_countryId, l_rnd_num_of_armies, d_country, "advance");
            }
        }
        return new AdvanceArmies(toDefend().d_countryId, toAttack().d_countryId, l_rnd_num_of_armies, d_country, "advance");
    }
}
