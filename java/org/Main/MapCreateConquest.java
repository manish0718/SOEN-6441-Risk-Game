package org.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is called to create a Conquest map from scratch if the user want to edit a file and that file is not present in the predefined maps list.
 * Initially the user will asked to enter the details of the maps, after entering all the details the user will ask to enter the validate map command so as to send the details to another class to validate the map.
 * If the map gets validated then fileCreation method of this class is called to save the map into a .map extension which user created from scratch.
 */
public class MapCreateConquest {

    /**
     * This method is used to create and save the validated map that user created from scratch.
     * @param p_file This is the file name in which map is created and saved by user.
     * @param p_continame This parameter is a string array which contains the list of the continents.
     * @param p_continents This parameter contains the list of continents to which different different countries belongs.
     * @param p_continent This is an integer value which denotes the number of continents in a map.
     * @param p_ContVal This parameter is a string array which contains the list of the control values of continents.
     * @param p_countrytotal This is an integer value which denotes the number of countries in a map.
     * @param p_countrynames This parameter is a string array which contains the list of the countries.
     * @param p_X_Coordinates This parameter contains the X coordinates of all the countries.
     * @param p_Y_Coordinates This parameter contains the Y coordinates of all the countries.
     * @param p_adjacentcountries This a string array which contains the list of neighboring countries of a particular country.
     * @return a File which is created.
     * @throws FileNotFoundException If file doesn't found at the directed path then this will throw exception.
     */
    public static File filecreation(File p_file, String[] p_continame, String[] p_continents, int p_continent, String[] p_ContVal, int p_countrytotal, String[] p_countrynames, String[] p_X_Coordinates, String[] p_Y_Coordinates, String[] p_adjacentcountries) throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(p_file);
        printWriter.println("[Map]");
        printWriter.println("author=");
        printWriter.println("image=Board.map");
        printWriter.println("wrap=no");
        printWriter.println("scroll=horizontal");
        printWriter.println("warn=yes");
        printWriter.println(" ");
        printWriter.println("[Continents]");

        for (int i = 0; i < p_continent; i++) {
            printWriter.println(p_continame[i] + "=" + p_ContVal[i]);
        }
        printWriter.println("");

        printWriter.println("[Territories]");
        for (int i = 0; i < p_countrytotal; i++) {
            printWriter.println(p_countrynames[i] +","+ p_X_Coordinates[i] + "," + p_Y_Coordinates[i]+","+p_continents[i]+","+p_adjacentcountries[i]);
        }

        printWriter.println("");
        System.out.println("Map Created Successfully");
        printWriter.close();

        return p_file;

    }

    /**
     * This method is called to create a map from scratch.
     * This method also validate the inputs of the user on the spot and at the end it will ask the user to enter validate map command so as to validate tha map i.e game can be played on the following map or not.
     * @param p_file This is the map file where all the details of the map will be saved after user done with entering the details of the map.
     */
    public static void createMap(File p_file) {
        int l_continent;
        Scanner l_input;
        while (true) {
            try {
                l_input = new Scanner(System.in);
                System.out.println("Enter no of Continents");
                l_continent = l_input.nextInt();
                if (l_continent > 0) {
                    break;
                } else {
                    System.out.println("Enter positive value");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Enter positive integer value ");
            }
        }
        String[] l_continame = new String[l_continent];

        int l_c = 0;
        String l_x;
        for (int l_i = 0; l_i < l_continent; l_i++) {
            System.out.println("Enter " + (l_i + 1) + " continent name");
            Scanner l_sc = new Scanner(System.in);
            l_x = l_sc.nextLine();
            for (int l_k = 0; l_k < l_c; l_k++) {
                while (true) {
                    if (!l_x.equals(l_continame[l_k])) {
                        l_continame[l_i] = l_x;
                        break;
                    } else {
                        System.out.println("Continent Already Exists\n");
                        System.out.println("Enter another Continent name");
                        l_x = l_sc.nextLine();
                    }
                }
            }
            if (l_c == 0) {
                l_continame[l_i] = l_x;
            }
            l_c++;
        }

        String[] l_ContVal = new String[l_continent];
        for (int l_i = 0; l_i < l_continent; l_i++) {
            while (true) {
                System.out.println("Enter Control Value of " + l_continame[l_i]);
                Scanner l_sc = new Scanner(System.in);
                l_ContVal[l_i] = l_sc.nextLine();
                try {
                    int l_number = Integer.parseInt(l_ContVal[l_i]);
                    if (l_number > 0) {
                        break;
                    } else {
                        System.out.println("Enter positive value");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Enter Integer Value");
                }
            }

        }

        int l_countrytotal;
        while (true) {
            try {
                System.out.println("Enter total number of Countries");
                l_countrytotal = l_input.nextInt();
                if (l_countrytotal > 0) {
                    break;
                } else {
                    System.out.println("Enter positive value");
                }
            } catch (Exception e) {
                System.out.println("Enter positive integer value");
            }

        }
        int[] l_countrynum = new int[l_continent];


        int l_key = 0;
        int l_sum = 0;
        while (true) {
            System.out.println("Enter number of countries in " + l_continame[l_key]);
            l_countrynum[l_key] = l_input.nextInt();
            l_sum = l_sum + l_countrynum[l_key];
            l_key++;
            if (l_key == l_continent) {
                if (l_countrytotal == l_sum) {
                    break;
                } else {
                    System.out.println("Your total number of countries do not match");
                    l_key = 0;
                    l_sum = 0;
                }
            }
        }

        String[] l_countrynames = new String[l_countrytotal];
        int[] l_continentcountryvalue = new int[l_countrytotal];
        String[] l_adjacentcountries = new String[l_countrytotal];
        String[] l_continents = new String[l_countrytotal];
        String[] l_X_Coordinate = new String[l_countrytotal];
        String[] l_Y_Coordinate = new String[l_countrytotal];
        HashMap<String, Integer> l_adj = new HashMap<>();


        for (int l_j = 0; l_j < l_continame.length; l_j++) {
            l_adj.put(l_continame[l_j], l_countrynum[l_j]);
        }
        int l_ct = 0;
        int l_dt = 0;
        String l_y;
        for (int l_i = 0; l_i < l_countrytotal; l_i++) {
            System.out.println("Enter " + (l_i + 1) + " country name");
            Scanner l_sc = new Scanner(System.in);
            l_y = l_sc.nextLine();
            for (int l_j = 0; l_j < l_continent; l_j++) {
                while (true) {
                    if (!l_y.equals(l_continame[l_j])) {
                        break;
                    } else {
                        System.out.println("Country and Continent name cannot be same\n");
                        System.out.println("Enter another " + (l_i + 1) + " Country name");
                        l_y = l_sc.nextLine();
                    }
                }
            }
            for (int l_k = 0; l_k < l_ct; l_k++) {
                while (true) {
                    if (!l_y.equals(l_countrynames[l_k])) {
                        l_countrynames[l_i] = l_y;
                        break;
                    } else {
                        System.out.println("Country Already Exists\n");
                        System.out.println("Enter another Country name");
                        l_y = l_sc.nextLine();
                    }
                }
            }
            if (l_ct == 0) {
                l_countrynames[l_i] = l_y;
            }
            l_ct++;
            System.out.println("");
            for (int l_k = 0; l_k < l_continent; l_k++) {
                System.out.println(l_k + 1 + " " + l_continame[l_k]);
            }
            System.out.println("");
            int l_flag1 = 0;
            int l_flag = 0;
            while (true) {
                Scanner l_sc1 = new Scanner(System.in);
                System.out.println("Enter the continent name in which you want to add country");
                try {
                    String l_value = l_sc1.nextLine();
                    while (true) {
                        for (int l_k = 0; l_k < l_continame.length; l_k++) {
                            if (l_value.equals(l_continame[l_k])) {
                                l_flag = 1;
                                l_flag1 = 1;
                                break;
                            }

                        }
                        if (l_flag == 1) {
                            l_continents[l_dt] = l_value;
                            break;
                        }
                    }

                } catch (Exception p_e) {
                    System.out.println("Enter unique name from the above given list");
                }
                if (l_flag1 == 1) {
                    break;
                }
            }
            System.out.println("");
            l_dt += 1;
            System.out.println("");
            while (true) {
                System.out.println("Enter the X coordinate of the country");
                Scanner l_sc2 = new Scanner(System.in);
                l_X_Coordinate[l_i] = l_sc2.nextLine();
                try {
                    int l_yourNumber = Integer.parseInt(l_X_Coordinate[l_i]);
                    if (l_yourNumber > 0) {
                        break;
                    } else {
                        System.out.println("Enter positive integer value");
                    }
                } catch (NumberFormatException p_ex) {
                    System.out.println("Enter integer value");
                }
            }
            System.out.println("");
            while (true) {
                System.out.println("Enter the Y coordinates of the country");
                Scanner l_sc4 = new Scanner(System.in);
                l_Y_Coordinate[l_i] = l_sc4.nextLine();
                try {
                    int l_yourNumber = Integer.parseInt(l_X_Coordinate[l_i]);
                    if (l_yourNumber > 0) {
                        break;
                    } else {
                        System.out.println("Enter positive integer value");
                    }
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Enter Integer Value");
                }
            }
        }
        System.out.println("Now here are the list of countries");
        for (int l_k = 0; l_k < l_countrytotal; l_k++) {
            System.out.println((l_k + 1) + " " + l_countrynames[l_k]);
        }
        System.out.println("");
        int l_i = 0;
        String[] l_neighbors = new String[l_countrytotal];
        while (l_i < l_countrytotal) {
            System.out.println("Enter the name of adjacent countries of " + (l_i + 1) + " country");
            Scanner l_sc3 = new Scanner(System.in);
            l_adjacentcountries[l_i] = l_sc3.nextLine();
            String l_splitString[] = l_adjacentcountries[l_i].split(",");
            l_i=l_i+1;
        }

        while(true) {
            try {
                Scanner l_sc = new Scanner(System.in);
                System.out.println("Enter validatemap command");
                String l_reply = l_sc.nextLine();
                if (l_reply.equalsIgnoreCase("validatemap")) {
                    UserCreatedMapValidationConquest l_validate = new UserCreatedMapValidationConquest();
                    l_validate.mapValidate(p_file, l_continame, l_continents, l_continent, l_ContVal, l_countrytotal, l_countrynames, l_X_Coordinate, l_Y_Coordinate, l_adjacentcountries);
                    break;
                }
                else{
                    System.out.println("Enter valid command");
                }
                filecreation(p_file, l_continame, l_continents, l_continent, l_ContVal, l_countrytotal, l_countrynames, l_X_Coordinate, l_Y_Coordinate, l_adjacentcountries);
                break;
            } catch (Exception p_e) {
                p_e.printStackTrace();
            }
        }
    }
}
