package org.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is to display the map in the form of text.
 */

public class ShowMap {
    File d_FILE;
    /**
     * SC is a scanner used to take input from user.
     */
    public static Scanner SC;

    /**
     * This method initially check whether there is any predefined maps. If the file is empty then it will ask the user to create a new map.
     * Else this method will show the list of maps and ask to user to select a map which user want to see.
     * After that it will go back to the main menu.
     * @param d_FILE This is the map file.
     * @throws Exception If the map name entered by user is wrong or incorrect, this will throw exception.
     */

    public void mapshow(File d_FILE) throws Exception {
                MapTable l_list = new MapTable();
                ArrayList<String> l_countries = l_list.countryList(d_FILE);
                ArrayList<String> l_continent = l_list.continentList(d_FILE);
                HashMap<String, Integer> l_contval = l_list.continentandvalue(d_FILE);
                HashMap<Integer, String> l_countrykey = l_list.countryanditskey(d_FILE);
                HashMap<String, String> l_countrycont = l_list.countryanditscontinent(d_FILE);
                HashMap<String, ArrayList> l_countryneigh = l_list.countryanditsneighbours(d_FILE);

                System.out.println("************** COUNTRIES **************");
                for (int l_i = 0; l_i < l_countries.size(); l_i++) {
                    System.out.print(l_countries.get(l_i) + " ");
                }
                System.out.println("");
                System.out.println("****************************");
                System.out.println("");

                System.out.println("************** CONTINENTS **************");
                for (int l_i = 0; l_i < l_continent.size(); l_i++) {
                    System.out.print(l_continent.get(l_i) + " ");
                }
                System.out.println("");
                System.out.println("****************************");
                System.out.println("");

                System.out.println("************** CONTINENTS and their initial ARMY VALUE **************");
                for (
                        Map.Entry l_mapElement : l_contval.entrySet()) {
                    String l_key = (String) l_mapElement.getKey();
                    int l_value = (int) l_mapElement.getValue();
                    System.out.println(l_key + " = " + l_value);
                }
                System.out.println("****************************");
                System.out.println("");

                System.out.println("************** COUNTRIES with their UNIQUE KEY **************");
                for (Map.Entry l_mapElement : l_countrykey.entrySet()) {
                    Integer l_key = (Integer) l_mapElement.getKey();
                    String l_value = (String) l_mapElement.getValue();
                    System.out.println(l_key + " = " + l_value);
                }
                System.out.println("****************************");
                System.out.println("");

                System.out.println("************** COUNTRY and its CONTINENT **************");
                for (Map.Entry l_mapElement : l_countrycont.entrySet()) {
                    String l_key = (String) l_mapElement.getKey();
                    String l_value = (String) l_mapElement.getValue();
                    System.out.println(l_key + " = " + l_value);
                }
                System.out.println("****************************");
                System.out.println("");

                System.out.println("************** COUNTRY and its NEIGHBOURING COUNTRIES **************");
                for (Map.Entry<String, ArrayList> l_mapElement : l_countryneigh.entrySet()) {
                    String l_key = (String) l_mapElement.getKey();
                    ArrayList l_value = l_mapElement.getValue();
                    System.out.println(l_key + " = " + l_value);
                }
        System.out.println("");
    }
}
