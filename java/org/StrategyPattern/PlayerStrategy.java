package org.StrategyPattern;

import org.GamePlay.Country;
import org.GamePlay.Order;
import org.GamePlay.Player;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *	Strategy of the Strategy pattern
 */
public abstract class PlayerStrategy implements Serializable {

	private static final long serialVersionUID = 6061893153983494157L;
	ConcurrentHashMap<String, Country> d_map;
	Player d_player;

	/**
	 *	Determines the country to attack/move to.
	 *	The Random player determines this randomly
	 *	@return random territory
	 */
	protected abstract Country toAttack();
	/**
	 *	Determines the country to attack to.
	 *	The Random player determines this randomly.
	 *	@return random territory owned by the player
	 */
	protected abstract Country toAttackFrom();
	/**
	 *	Determines the country to attack from.
	 *	The Random player does not use this, so it returns null.
	 *	@return null
	 */
	protected abstract Country toMoveFrom();
	/**
	 *	Determines the country to move from.
	 *	The Random player does not use this, so it returns null.
	 *	@return null
	 */
	protected abstract Country toDefend();
	/**
	 *	Creates and order.
	 *	The Random player can either deploy or advance, determined randomly. .
	 *	@return null
	 */
	public abstract Order createOrder();
	
	PlayerStrategy(Player p_player,ConcurrentHashMap<String, Country> p_Countries){
		d_player = p_player;
		d_map = p_Countries;
	}
}
