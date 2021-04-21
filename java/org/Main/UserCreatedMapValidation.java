package org.Main;

import org.StatePattern.GameEngine1;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class will get the details of the map after user creates a map from the scratch and then this will validate the map on some conditions.
 * If the map is validated, then the file will get saved else it will show that the file in not correct.
 */

public class UserCreatedMapValidation {
    /**
     * This method will get the details of the map after user creates a map from scratch and then it will validate the map on some conditions.
     * If the map is validated, then the file will get saved else it will show that the file in not correct.
     * @param p_file This is the file name in which map is created and saved by user and on which map validation is called.
     * @param p_continent This parameter is a string array which contains the list of the continents.
     * @param p_continentsize This is an integer value which denotes the number of continents in a map.
     * @param p_ContVal This parameter is a string array which contains the list of the control values of continents.
     * @param p_countrytotal This is an integer value which denotes the number of countries in a map.
     * @param p_countries This parameter is a string array which contains the list of the countries.
     * @param p_continentcountryvalue This is an integer array which contains the list of unique ID's of continent to which a country belong.
     * @param p_adjacentcountries This a string array which contains the list of neighboring countries of a particular country.
     * @throws Exception If file doesn't found at the directed path then this will throw exception.
     */
    public void mapValidate(File p_file, String[] p_continent, int p_continentsize, String[] p_ContVal, int p_countrytotal, String[] p_countries, int[] p_continentcountryvalue, String[] p_adjacentcountries) throws Exception {
        int l_finalFlag = 0;
        for (int l_i = 0; l_i < p_countries.length; l_i++) {
            for(int j=l_i+1; j < p_countries.length-1;j++){
                if(p_countries[l_i].equalsIgnoreCase(p_countries[j])){
                    System.out.println("Countries cannot be of same name");
                    l_finalFlag = 1;
                    break;
                }
            }
        }
        for (int l_i = 0; l_i < p_continent.length; l_i++) {
            for(int l_j=l_i+1; l_j < p_continent.length-1;l_j++){
                if(p_continent[l_i].equalsIgnoreCase(p_continent[l_j])){
                    System.out.println(" Continents cannot be of same name ");
                    l_finalFlag = 1;
                    break;
                }
            }
        }
        for(int l_i =0; l_i < p_ContVal.length; l_i++ ){
            int l_number = Integer.parseInt(p_ContVal[l_i]);
            if(l_number < 0){
                System.out.println("Control value of p_continent cannot be negative ");
                l_finalFlag = 1;
                break;
            }
        }
        for(int l_i=0;l_i<p_adjacentcountries.length;l_i++)
        {
            String l_splitString[] = p_adjacentcountries[l_i].split(",");
            int l_flag =0;
            int l_repititionFlag=0;;
            for(int l_j=0;l_j<l_splitString.length;l_j++)
            {
                try{
                    if (Integer.parseInt(l_splitString[l_j]) > 0 && Integer.parseInt(l_splitString[l_j]) <= p_countrytotal) {
                        l_flag += 1;
                    }
                    if (l_j <= l_splitString.length - 2) {
                        for (int k = l_j + 1; k < l_splitString.length; k++) {
                            if (l_splitString[l_j].equals(l_splitString[k])) {
                                l_repititionFlag += 1;
                                break;
                            }
                        }
                    }
                } catch (Exception e)
                {}
            }
            if(l_flag==l_splitString.length && l_repititionFlag==0 )
            {
                continue;
            }
            if(l_repititionFlag==1)
            {
                System.out.println("Repetition of country's Unique ID not allowed");
                l_finalFlag=1;
                break;
            }
            else
            {
                System.out.println("The range of Unique keys is between 1 to "+p_countrytotal);
                l_finalFlag=1;
                break;
            }
        }
        if(l_finalFlag==1)
        {
            System.out.println("Map is Incorrect");
        }
        else if(l_finalFlag==0)
        {
            MapCreate l_save  = new MapCreate();
            File l_file = l_save.fileCreation(p_file, p_continent, p_continentsize, p_ContVal, p_countrytotal, p_countries, p_continentcountryvalue, p_adjacentcountries);
            GraphConnected l_validate = new GraphConnected(l_file);
            ContinentsConnected l_obj = new ContinentsConnected(l_file);
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
                GameEngine1 start = new GameEngine1();
                start.start();
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
                GameEngine1 start = new GameEngine1();
                start.start();
            }
        }

    }
}
