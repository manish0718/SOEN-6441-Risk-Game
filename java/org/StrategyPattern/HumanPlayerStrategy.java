package org.StrategyPattern;

import org.GamePlay.Country;
import org.GamePlay.Order;
import org.GamePlay.Player;
import org.GamePlay.playGame;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Human Player Strategy class
 */
public class HumanPlayerStrategy extends PlayerStrategy {

    ConcurrentHashMap <String,Country> d_countries;
    Player d_player;
    ConcurrentHashMap<String,Player> d_playerList;
    Country d_country;

    /**
     * Constructor to initialise human player behaviour
     * @param p_player Player class object
     * @param p_Countries Countries Hashmap. Key country name and value object of that country
     * @param p_playerList Players list hashmap.Key Player name and value player object
     * @param p_country Country class object
     */
    public HumanPlayerStrategy(Player p_player, ConcurrentHashMap<String, Country> p_Countries, ConcurrentHashMap<String, Player> p_playerList , Country p_country)
    {
        super(p_player, p_Countries);
        this.d_countries = p_Countries;
        this.d_player = p_player;
        this.d_playerList = p_playerList;
        this.d_country = p_country;
    }


    /**
     *	Determines the country to attack/move to.
     *	The Human player determines this randomly
     *	@return random territory
     */
    @Override
    protected Country toAttack() {

        return null;
    }

    /**
     *	Determines the country to attack from which country.
     *	The Human player determines this randomly.
     *	@return null
     */
    @Override
    protected Country toAttackFrom() {
        return null;
    }

    /**
     *	Determines the country to move from.
     *	The Human player does not use this, so it returns null.
     *	@return null
     */
    @Override
    protected Country toMoveFrom() {
        return null;
    }

    /**
     *	Determines the country to attack from.
     *	The Human player does not use this, so it returns null.
     *	@return null
     */
    @Override
    protected Country toDefend() {
        return null;
    }


    /**
     *	Creates and order.
     *	The Human player can either deploy or advance, determined randomly. .
     *	@return Order
     */
    @Override
    public Order createOrder() {

        playGame l_humanPlay = new playGame(d_playerList,d_country);
        Order l_order = l_humanPlay.mainGameLoop();
        return l_order;

    }
}

