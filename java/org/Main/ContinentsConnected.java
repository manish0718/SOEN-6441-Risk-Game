package org.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class check whether the continents are connected to each other or not.
 */
public class ContinentsConnected {

    static File d_file;

    /**
     * This is the constructor of the class which just intialises the file.
     * @param p_file This is the file which is being used to check whether the continents in it are connected or not.
     */
    public ContinentsConnected(File p_file) {

        this.d_file = p_file;

    }

    /**
     * This function uses the continentsKey and CountriesContinent fuctions from map table and actually..
     * decides whether continents are disconnected or not. This puts all continents unique ids in continentsKey Arraylist..
     * and each countries continent id in CountriesContinent and then is sorted and compared with continentsKey arraylist..
     * if both are equal the continents are connected and if not the not connected.
     * @return boolean value whether a particulars maps continents are connected or not.
     * @throws Exception this is file not found exception.
     */
    public boolean ifContinentsConnected() throws Exception {

        MapTable l_obj = new MapTable();
        ArrayList<Integer> l_ContinentAndKey = l_obj.continentsKey(d_file);
        ArrayList<Integer> l_ContId = l_obj.CountriesContinent(d_file);
        Collections.sort(l_ContId);
        GraphConnected l_check = new GraphConnected(d_file);

        if (l_check.ifGraphConnected() == true) {

            if (l_ContinentAndKey.equals(l_ContId)==true){
                return true;
            }


        }
        return false;
    }

    /**
     * This method will check if all the continent and countries are connected i.e there is no alone country or continent should be there.
     * @return This will return a hashmap in which the key is the unique key of continent and arraylist contains the countries in that continent.
     * @throws Exception This is the file not found exception.
     */

    public HashMap<Integer,ArrayList> ifContinentCountriesConnected() throws Exception {
        MapTable l_obj = new MapTable();

        ArrayList<Integer> l_copy = new ArrayList<>();
        HashMap<Integer,ArrayList> l_nodes = new HashMap<>();
        HashMap<Integer,Integer> l_connect = l_obj.cont(d_file);
        ArrayList<Integer> l_ContinentAndKey = l_obj.continentsKey(d_file);

        for(int l_i =0 ; l_i<l_ContinentAndKey.size() ; l_i++){
            ArrayList<Integer> l_countries = new ArrayList<>();
            for(Map.Entry<Integer, Integer> l_entry: l_connect.entrySet()){

                if(l_entry.getValue()== l_ContinentAndKey.get(l_i)){
                    l_countries.add(l_entry.getKey());
                    l_copy = l_countries;
                    l_nodes.put(l_ContinentAndKey.get(l_i),l_copy);

                }

            }

        }

        return l_nodes;
    }
}
