package org.GamePlay;

/**
 * This is the Abstract class which contains the decleartion of Execute Order Function.
 */
public abstract class Order {

    /**
     * This is the abstract method declaration which will execute the orders issued by each player.
     * @param p_p this is the player object, which will execute the correct orders from each individual player.
     */
    abstract void Execute(Player p_p);
}
