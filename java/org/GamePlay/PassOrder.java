package org.GamePlay;

/**
 *Child class extending the Order Class , it deals with only Pass Order command
 */
public class PassOrder extends Order {

    Player d_player;
    Country d_cou;

    /**
     * Constructor to initilise the Pass Order object .
     *
     * @param p_p   player name
     * @param p_cou Country
     */
    public PassOrder(Player p_p, Country p_cou) {
        this.d_player = p_p;
        this.d_cou = p_cou;
    }
    /**
     *Blank Implementation of Execute in Pass Order
     * @param p_player Player Object
     */
    @Override
    void Execute(Player p_player) {}

}