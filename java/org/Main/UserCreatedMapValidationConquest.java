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

public class UserCreatedMapValidationConquest {
    /**
     * This method will get the details of the map after user creates a map from scratch and then it will validate the map on some conditions.
     * If the map is validated, then the file will get saved else it will show that the file in not correct.
     * @param p_file This is the file name in which map is created and saved by user and on which map validation is called.
     * @param p_continent This parameter is a string array which contains the list of the continents.
     * @param p_continentsize This is an integer value which denotes the number of continents in a map.
     * @param p_ContVal This parameter is a string array which contains the list of the control values of continents.
     * @param p_countrytotal This is an integer value which denotes the number of countries in a map.
     * @param p_countries This parameter is a string array which contains the list of the countries.
     * @param p_adjacentcountries This a string array which contains the list of neighboring countries of a particular country.
     * @param continentscont This array contains the list of continents to which different countries belong.
     * @param X_coordinate This is String type array which contains all the X coordinates of every countries.
     * @param Y_coordinate This is String type array which contains all the X coordinates of every countries.
     * @throws Exception If file doesn't found at the directed path then this will throw exception.
     */

    public void mapValidate(File p_file, String[] p_continent,String[] continentscont, int p_continentsize, String[] p_ContVal, int p_countrytotal,String[] p_countries,String[] X_coordinate, String[] Y_coordinate, String[] p_adjacentcountries) throws Exception {
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
        if(l_finalFlag==1)
        {
            System.out.println("Map is Incorrect");
        }
        else if(l_finalFlag==0)
        {
            MapCreateConquest l_save  = new MapCreateConquest();

            File l_file = l_save.filecreation(p_file, p_continent,continentscont ,p_continentsize, p_ContVal, p_countrytotal,p_countries,X_coordinate,Y_coordinate,p_adjacentcountries);
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
