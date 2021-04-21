package org.GamePlay;

import org.StrategyPattern.PlayerStrategy;
import org.StrategyPattern.RandomPlayerStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *Player Class containing all the stats of the Player Class
 */
public class Player implements Serializable {
    private static final long serialVersionUID= 3710154222911855669L;
    public ArrayList<Country> d_owned = new ArrayList<Country>();
    public Queue<Order> d_orders = new LinkedList<Order>();// Queue only
    public Integer d_armiesNum;
    public String d_name;
    public int d_continentValue; // Continent Control Value ;
    public ArrayList<String> d_cards = new ArrayList<>();
    public ArrayList<String> d_negotiate = new ArrayList<>();
    PlayerStrategy d_strategy;


    /**
     * Parameterised Constructor to initilize Player Object by name .
     * @param p_name  Player Name
     */
    public Player(String p_name) {
        this.d_name = p_name;
    }

    public void setStrategy(PlayerStrategy p_strat) {
        d_strategy = p_strat;
    }

    /**
     * getter for Player Name.
     * @return Player Name
     */
    public String getD_name() {
        return d_name;
    }

    /**
     *Getter for list of countries owned by the player.
     * @return list of countries owned by the player
     */
    public ArrayList<Country> getD_owned() {
        return d_owned;
    }

    /**
     *setter for owned country by the player
     * @param p_country country won by the player
     */
    public void setD_owned(Country p_country) {
        this.d_owned.add(p_country);
    }

    public void add_card(String cards){
        this.d_cards.add(cards);
    }

    public  void add_negotiator(String player){this.d_negotiate.add(player);}

    /**
     *function to issue order .
     * @param p_order issue order object
     */
    public boolean issue_order(Order p_order)
    {
        p_order = d_strategy.createOrder();
        if (p_order != null) {
            d_orders.add(p_order);
            return true;
        }
        return false;
    }

    /**
     *function to issue order .
     * @param p_order issue order object
     */
    public boolean issue_orders(Order p_order)
    {
        d_orders.add(p_order);
        return true;

    }

    /**
     * function to fetch next order.
     * @return Order Next Order to be executed
     */
    public Order next_order() {
        if (!this.d_name.equalsIgnoreCase("cheater")) {
            Order l_to_return = null;
            if (!this.d_orders.isEmpty()) {
                l_to_return = this.d_orders.poll();
                this.d_orders.remove(d_orders.peek());
                return l_to_return;

            }
        }
        return null;
    }
}
