package org.GamePlay;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Child class extending the Order Class , it deals with only deploy Order command
 */
public class DeployOrder extends Order implements Serializable {
    private static final long serialVersionUID = 2113471175527832670L;
    String d_countryId;
    Integer d_armiesToPlace;
    Country d_cou;
    String d_temp = "";

    /**
     * Constructor to initialize the DeployOrder Object.
     *
     * @param p_countryId     Country Name
     * @param p_armiesToPlace No. of Armies to Deploy
     * @param p_cou           Country
     */
    public DeployOrder(String p_countryId, Integer p_armiesToPlace, Country p_cou) {
        this.d_countryId = p_countryId;
        this.d_armiesToPlace = p_armiesToPlace;
        this.d_cou = p_cou;
    }

    /**
     * Implementation of Execute in Deploy Order
     *
     * @param p_p Player
     */
    public void Execute(Player p_p) {

        int l_index = 10000000;
        for (int i = 0; i < p_p.d_owned.size(); i++) {
            if (p_p.d_owned.get(i).d_countryId.equalsIgnoreCase(d_countryId)) {
                l_index = i;
            }
        }
        String l_order = "ORDER : " + p_p.d_name +" gave order :"+" Deploy armies "+ d_armiesToPlace +" to " + d_countryId;
        String l_separator = "***********************************************************************************************************";
        if (l_index != 10000000) {
            if (d_armiesToPlace <= p_p.d_armiesNum) {
                p_p.d_armiesNum = p_p.d_armiesNum - d_armiesToPlace;

                d_cou.COUNTRIESLIST.get(d_countryId).d_numOfArmiesPlaced += d_armiesToPlace;
                d_cou.COUNTRIESLIST.get(d_countryId).d_owner = p_p.d_name;
                System.out.println(l_separator);
                System.out.println(l_order);
                System.out.print("EFFECT : " + p_p.d_name + " your order has been placed. Now your army count is " + p_p.d_armiesNum + ". ");
                System.out.print("MSG : " + d_armiesToPlace + " unit of armies are placed on " + p_p.d_owned.get(l_index).d_countryId);
                System.out.println("");
                System.out.println(l_separator);
                ConcurrentHashMap<String, Player> playerList = new ConcurrentHashMap<>();
                playerList.put(p_p.d_name, p_p);
                showMap map = new showMap(playerList, d_cou);
                d_temp = d_armiesToPlace + " armies deployed";
            } else {
                d_cou.COUNTRIESLIST.get(d_countryId).d_numOfArmiesPlaced += p_p.d_armiesNum;
                d_cou.COUNTRIESLIST.get(d_countryId).d_owner = p_p.d_name;
                System.out.println(l_separator);
                System.out.println(l_order);
                System.out.println("EFFECT : " + p_p.d_name + " you only own " + p_p.d_armiesNum + " armies and all of them are now placed on " + p_p.d_owned.get(l_index).d_countryId);
                System.out.println(l_separator);
                d_temp = "You only own " + p_p.d_armiesNum + " armies";
                p_p.d_armiesNum = 0;
            }
        } else {
            p_p.d_armiesNum = p_p.d_armiesNum - d_armiesToPlace;
            if (p_p.d_armiesNum < 0) {
                p_p.d_armiesNum = 0;
            }
            System.out.println(l_separator);
            System.out.println(l_order);
            System.out.println("EFFECT : You don't own this country. Penalty has been applied. Now your army count is " + p_p.d_armiesNum);
            System.out.println(l_separator);
            d_temp = "You don't own this country";
        }

    }

}
