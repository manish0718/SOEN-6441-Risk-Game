package org.Main;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will get the details of the map after user edit some changes in the map and then this will validate the map on some conditions.
 * If the map is validated, then the file will get saved else it will show that the file in not correct.
 */
public class MapValidationConquest {

    /**
     * This Flag is set to 1 if the graph is not validated or incorrect else it's value will be equal to 0.
     */

    public int d_final_flag = 0;
    /**
     * This method will get the details of the map after user edit some changes in the map and then it will validate the map on some conditions.
     * If the map is validated, then the file will get saved else it will show that the file in not correct.
     * @param p_file This is the map file where all the details of the map will be saved.
     * @param p_countries This parameter is an arraylist which contains the list of countries.
     * @param p_continent This parameter is an arraylist which contains the list of the continents.
     * @param p_cont_val This is a hashmap data structure which contains the continent name as the key and their control value as the hashmap value.
     * @param p_country_cont This is a hashmap which contains the name of the country as the key and the continent to which it belong as the value of the hashmap.
     * @param p_country_neighbour This is a hashmap which contains a string as the key which contains the name of the country and an arraylist as the value of the hashmap which contains the list of neighboring countries of that key country.
     * @throws Exception If file doesn't found at the directed path then this will throw exception.
     */
    public void mapValidate(File p_file,ArrayList<String> p_countries,ArrayList<String> p_continent,HashMap<String, Integer> p_cont_val,HashMap<String, String> p_country_cont,HashMap<String, ArrayList> p_country_neighbour) throws Exception {

        for (int l_i = 0; l_i < p_countries.size(); l_i++) {
            for(int l_j=l_i+1; l_j < p_countries.size()-1;l_j++){
                if(p_countries.get(l_i).equalsIgnoreCase(p_countries.get(l_j))){
                    System.out.println("Countries cannot be of same name");
                    d_final_flag = 1;
                    break;
                }
            }
        }
        for (int l_i = 0; l_i < p_continent.size(); l_i++) {
            for(int l_j=l_i+1; l_j < p_continent.size()-1;l_j++){
                if(p_continent.get(l_i).equalsIgnoreCase(p_continent.get(l_j))){
                    System.out.println(" Continents cannot be of same name ");
                    d_final_flag= 1;
                    break;
                }
            }
        }
        for (Map.Entry<String, Integer> l_entry : p_cont_val.entrySet()) {
            if(l_entry.getValue() < 0){
                System.out.println("Continent's Control value cannot be negative");
                d_final_flag = 1;
                break;
            }
        }
        for (Map.Entry<String,ArrayList> l_map_element : p_country_neighbour.entrySet()) {
            String l_key = (String) l_map_element.getKey();
            ArrayList l_value = l_map_element.getValue();
            for(int l_i=0;l_i<l_value.size();l_i++){
                if(l_value.get(l_i).equals("DNE")){
                    d_final_flag = 1;
                }
            }
        }
        if(d_final_flag==1)
        {
            String l_a = "Map is Incorrect";
            System.out.println("Map is Incorrect");
        }
        else if(d_final_flag==0)
        {


            EditMapConquest l_save  = new EditMapConquest();
            File l_file = l_save.fileCreation(p_file, p_country_cont,p_cont_val, p_country_neighbour);
            GraphConnectedConquest l_validate = new GraphConnectedConquest(l_file);
            ContinentsConnectedConquest l_obj = new ContinentsConnectedConquest(l_file);
            boolean l_check = l_validate.ifGraphConnected();
            boolean l_sub = l_validate.ContinentsCheck();
            boolean l_one = l_obj.ifContinentsConnected();

            if(l_check==true && l_sub == true && l_one==true ){
            System.out.println("**************************************");
            System.out.println("Graph is Connected");
            System.out.println("**************************************");
            System.out.println("");

            System.out.println("**************************************");
            System.out.println("Map is validated");
            System.out.println("**************************************");
            d_final_flag = 0;
        }
        else{
            System.out.println("**************************************");
            System.out.println("Graph is not Connected");
            System.out.println("**************************************");
            System.out.println("");

            System.out.println("");
            System.out.println("**************************************");
            System.out.println("Map is Incorrect");
            System.out.println("**************************************");
            d_final_flag = 1;
            }
        }
    }
}
