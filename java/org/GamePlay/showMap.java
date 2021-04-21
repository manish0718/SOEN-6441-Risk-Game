package org.GamePlay;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is used to display all the details while playing the Game. It will display details like countries, and its continents,
 * armies deployed on that particular country, the owner of that country and the adjacency matrix for the neighbouring countries.
 */
public class showMap {
    /**
     * Global Player List
     */
    static ConcurrentHashMap<String, Player> PLAYERS_LIST = new ConcurrentHashMap<>();

    /**
     * Country Object
     */
    static Country COU;

    /**
     * Adjacency Matrix for Map representation of country and its countries.
     */
    static String[][] ADJACENT_NEIGHBOURS;

    String[][] d_details;


    /**
     * Constructor used to initialize the global variables
     *
     * @param p_players_list is the HashMap containing all the Name of the players as the String and its value containing the Player Object
     * @param p_cou          is the object of the Country Class from where we can have complete detail of the Country.
     */
    public showMap(ConcurrentHashMap<String, Player> p_players_list, Country p_cou) {
        this.COU = p_cou;
        this.PLAYERS_LIST = p_players_list;
    }

    /**
     * This method is used to add element to the adjacency matrix which is used for representing the country and its neighbours.
     *
     * @param p_row     represents the row number of the 2D Matrix.
     * @param p_col     represents the column number of the 2D Matrix.
     * @param p_element represents the data to be added at that specific index.
     */
    public void l_addElement(int p_row, int p_col, String p_element) {
        ADJACENT_NEIGHBOURS[p_row][p_col] = p_element;
    }

    /**
     * Method which is used to print all the details of the country which includes country name, its continent,
     * its owner, armies deployed on that country. It also shows the country and its neighbouring country by X and O.
     * "X" means that the country on Y-axis is connected with the country on the X-axis.
     * while the "O" (oh) represents that the country is not connected.
     */
    public void check() {
        ADJACENT_NEIGHBOURS = new String[COU.COUNTRIESLIST.size() + 1][COU.COUNTRIESLIST.size() + 1];
        d_details = new String[COU.COUNTRIESLIST.size()][4];
        ArrayList<String> l_allCountries = new ArrayList<>(COU.COUNTRIESLIST.keySet());
        int l_row = 0;
        for (String l_countries : COU.COUNTRIESLIST.keySet()) {
            d_details[l_row][0] = l_countries;
            d_details[l_row][1] = COU.COUNTRIESLIST.get(l_countries).d_owner;
            d_details[l_row][2] = COU.COUNTRIESLIST.get(l_countries).d_continent;
            if (COU.COUNTRIESLIST.get(l_countries).d_numOfArmiesPlaced != null) {
                d_details[l_row][3] = COU.COUNTRIESLIST.get(l_countries).d_numOfArmiesPlaced.toString();
            } else {
                d_details[l_row][3] = "0";
            }
            l_row += 1;
        }

        ADJACENT_NEIGHBOURS[0][0] = " ";
        l_row = 1;
        for (String l_country : COU.COUNTRIESLIST.keySet()) {
            ADJACENT_NEIGHBOURS[0][l_row] = l_country;
            l_row += 1;
        }
        l_row = 1;
        for (String l_country : COU.COUNTRIESLIST.keySet()) {
            ADJACENT_NEIGHBOURS[l_row][0] = l_country;
            l_row += 1;
        }

        l_row = 1;

        for (String l_country : COU.COUNTRIESLIST.keySet()) {

            for (String l_neighbour : COU.COUNTRIESLIST.get(l_country).d_neighbours) {
                int l_temp = l_allCountries.indexOf(l_neighbour);
                l_addElement(l_row, l_temp + 1, "X");
            }
            for (int l_index = 0; l_index < ADJACENT_NEIGHBOURS[l_row].length; l_index++) {
                if (ADJACENT_NEIGHBOURS[l_row][l_index] == null) {
                    l_addElement(l_row, l_index, "O");
                }
            }
            l_addElement(l_row, l_row, "X");
            l_row += 1;
        }

        System.out.println("The details are: ");
        l_row = 0;
        int l_col = 0;
        System.out.println("Country\t Owner\t Continent\t Armies");
        for (l_row = 0; l_row < d_details.length; l_row++) {
            for (l_col = 0; l_col < 4; l_col++) {
                System.out.print(d_details[l_row][l_col] + "\t");
            }
            System.out.println("");
        }

        System.out.println("Adjacency matrix: ");
        l_row = 0;
        l_col = 0;
        for (l_row = 0; l_row < ADJACENT_NEIGHBOURS.length; l_row++) {
            for (l_col = 0; l_col < ADJACENT_NEIGHBOURS[l_row].length; l_col++) {
                System.out.print(ADJACENT_NEIGHBOURS[l_row][l_col] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("");

        System.out.println("Cards of the Players: ");

        for(String l_player:PLAYERS_LIST.keySet())
        {
            if(PLAYERS_LIST.get(l_player).d_cards.size()!=0) {
                System.out.print(l_player + "= ");
                for (String l_cards : PLAYERS_LIST.get(l_player).d_cards) {
                    System.out.print(l_cards + " ");
                }
                System.out.println("");
            }
            else
            {
                System.out.println("No cards for player: "+l_player);
            }
        }
    }
}
