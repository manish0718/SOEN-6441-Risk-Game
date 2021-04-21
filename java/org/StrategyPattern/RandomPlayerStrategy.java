package org.StrategyPattern;

import org.GamePlay.*;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 *	ConcreteStrategy of the Strategy pattern
 */
public class RandomPlayerStrategy extends PlayerStrategy {
	Country d_country;

	/**
	 * Random Player Strategy behaviour
	 * @param p_player Player class object
	 * @param p_map Countries Hashmap. Key country name and value object of that country
	 * @param p_country Country class object
	 */
	public RandomPlayerStrategy(Player p_player, ConcurrentHashMap<String, Country> p_map , Country p_country) {
		super(p_player, p_map);
		this.d_country = p_country;
	}

	/**
	 *	Determines the country to attack/move to. 
	 *	The Random player determines this randomly
	 *	@return random territory
	 */
	protected Country toAttack() {
		Random l_generator = new Random();
		Object[] l_values = (Object[]) d_map.values().toArray();
		Country l_rnd_target_territory = (Country) l_values[l_generator.nextInt(l_values.length+0)];
		return l_rnd_target_territory;
	}
	
	/**
	 *	Determines the country to attack to. 
	 *	The Random player determines this randomly. 
	 *	@return random territory owned by the player
	 */
	protected Country toDefend() {
		Random l_rand = new Random();
		int l_rnd_target_territory;
		l_rnd_target_territory = l_rand.nextInt(d_player.d_owned.size()+0);
		return(d_player.d_owned.get(l_rnd_target_territory));
	}

	/**
	 *	Determines the country to attack from. 
	 *	The Random player does not use this, so it returns null.
	 *	@return null
	 */
	protected Country toAttackFrom() {
		return toDefend();
	}

	/**
	 *	Determines the country to move from. 
	 *	The Random player does not use this, so it returns null.
	 *	@return null
	 */
	protected Country toMoveFrom() {
		return toDefend();
	}
	
	/**
	 *	Creates and order. 
	 *	The Random player can either deploy or advance, determined randomly. .
	 *	@return null
	 */
	public Order createOrder() {
		Random l_rand = new Random();

		int rnd_num_of_armies = l_rand.nextInt(d_player.d_armiesNum-1);
		if (l_rand.nextInt(5) != 0) {
			switch (l_rand.nextInt(3)) {
				case (0):
					return new DeployOrder(d_player.getD_name(), rnd_num_of_armies, toDefend());

				case (1):
					if(d_player.d_cards.contains("AIRLIFT")){
						return new AdvanceArmies(toDefend().d_countryId, toDefend().d_countryId, rnd_num_of_armies, d_country, "airlift");
					}
					else {
						return new AdvanceArmies(toDefend().d_countryId, toDefend().d_countryId, rnd_num_of_armies, d_country, "advance");
					}

				case (2):
					if(d_player.d_cards.contains("BOMB")){
						return new Cards(toAttack().d_countryId,"bomb",d_country);
					}
					else if(d_player.d_cards.contains("BLOCKADE")){
						return new Cards(toAttack().d_countryId,"blockade",d_country);
					}
					else {
						return new AdvanceArmies(toDefend().d_countryId, toDefend().d_countryId, rnd_num_of_armies, d_country, "advance");
					}
			}
		}
		return new AdvanceArmies(toDefend().d_countryId, toDefend().d_countryId, rnd_num_of_armies, d_country, "advance");
	}
}
