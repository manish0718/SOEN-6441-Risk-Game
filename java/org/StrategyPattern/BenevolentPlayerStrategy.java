package org.StrategyPattern;

import org.GamePlay.*;

import java.util.concurrent.ConcurrentHashMap;

public class BenevolentPlayerStrategy extends PlayerStrategy{
    Player d_player;
    ConcurrentHashMap<String, Country> d_countries;
    Country country;
    public BenevolentPlayerStrategy(Player p_p,ConcurrentHashMap<String,Country> p_countries, Country country)
    {
        super(p_p,p_countries);
        d_player = p_p;
        this.d_countries = p_countries;
        this.country = country;
    }
    @Override
    protected Country toAttack() {
        return null;
    }

    @Override
    protected Country toAttackFrom() {
        return null;
    }

    @Override
    protected Country toMoveFrom() {
        return null;
    }

    @Override
    protected Country toDefend() {
        int l_index=0;
        int l_counter=0;
        int l_minValue=1000000;
        for(Country l_country: d_player.d_owned)
        {
            if (l_country.d_numOfArmiesPlaced<l_minValue)
            {
                l_index = l_counter;
                l_minValue = l_country.d_numOfArmiesPlaced;
            }
            l_counter+=1;
        }
        return d_player.d_owned.get(l_index);
    }

    @Override
    public Order createOrder() {
        if(d_player.d_owned.size()>0) {
            return new DeployOrder(toDefend().d_countryId, d_player.d_armiesNum, country);
        }
        else
        {
            return null;
        }
    }
}

