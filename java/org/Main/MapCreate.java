package org.Main;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is called to create a map from scratch if the user want to edit a file and that file is not present in the predefined maps list.
 * Initially the user will asked to enter the details of the maps, after entering all the details the user will ask to enter the validate map command so as to send the details to another class to validate the map.
 * If the map gets validated then fileCreation method of this class is called to save the map into a .map extension which user created from scratch.
 */

public class MapCreate {

    /**
     * This method is called to create a map from scratch.
     * This method also validate the inputs of the user on the spot and at the end it will ask the user to enter validate map command so as to validate tha map i.e game can be played on the following map or not.
     * @param p_file This is the map file where all the details of the map will be saved after user done with entering the details of the map.
     */

    public static void createMap(File p_file){
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
            } catch (Exception p_e) {
                System.out.println("Enter positive integer value ");
            }
        }
        String[] l_cont_name = new String[l_continent];

        int l_c = 0;
        String l_x;
        int l_i1=0;
        while(l_i1<l_continent)
        {
            System.out.println("Enter " + (l_i1 + 1) + " continent name");
            Scanner l_sc = new Scanner(System.in);
            l_x = l_sc.nextLine();
            if(l_x.isEmpty())
            {
                System.out.println("Input can not be blank");
                System.out.println("");
                continue;
            }
            else{
                for (int l_k = 0; l_k < l_c; l_k++) {
                    while (true) {
                        if (!l_x.equals(l_cont_name[l_k])) {
                            l_cont_name[l_i1] = l_x;
                            break;
                        } else {
                            System.out.println("Continent Already Exists\n");
                            System.out.println("Enter another Continent name");
                            l_x = l_sc.nextLine();
                        }
                    }
                }
                if (l_c == 0 && !l_x.isEmpty()) {
                    l_cont_name[l_i1] = l_x;
                    l_c++;
                }
                l_i1+=1;
            }
        }

        String[] l_cont_val = new String[l_continent];
        for (int l_i = 0; l_i < l_continent; l_i++) {
            while (true) {
                System.out.println("Enter Control Value of " + l_cont_name[l_i]);
                Scanner l_sc = new Scanner(System.in);
                l_cont_val[l_i] = l_sc.nextLine();
                try {
                    int l_number = Integer.parseInt(l_cont_val[l_i]);
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

        int l_country_total;
        while (true) {
            try {
                System.out.println("Enter total number of Countries");
                l_country_total = l_input.nextInt();
                if (l_country_total > 0) {
                    break;
                } else {
                    System.out.println("Enter positive value");
                }
            } catch (Exception p_e) {
                System.out.println("Enter positive integer value");
            }

        }
        int[] l_country_num = new int[l_continent];


        int l_key = 0;
        int l_sum = 0;
        while (true) {
            System.out.println("Enter number of countries in " + l_cont_name[l_key]);
            l_country_num[l_key] = l_input.nextInt();
            l_sum = l_sum + l_country_num[l_key];
            l_key++;
            if (l_key == l_continent) {
                if (l_country_total == l_sum) {
                    break;
                } else {
                    System.out.println("Your total number of countries do not match");
                    l_key = 0;
                    l_sum = 0;
                }
            }
        }

        String[] l_country_names = new String[l_country_total];
        int[] l_continent_country_value = new int[l_country_total];
        String[] l_adjacent_countries = new String[l_country_total];
        HashMap<String, Integer> l_adj = new HashMap<>();

        for (int l_j = 0; l_j < l_cont_name.length; l_j++) {
            l_adj.put(l_cont_name[l_j], l_country_num[l_j]);
        }

        int l_i=0;
        int l_ct = 0;
        String l_y;
        while(l_i<l_country_total) {
            System.out.println("Enter " + (l_i + 1) + " country name");
            Scanner l_sc = new Scanner(System.in);
            l_y = l_sc.nextLine();
            if (l_y.isEmpty())
            {
                System.out.println("Input can not be blank");
                continue;
            }
            else
            {

                for (int l_j = 0; l_j < l_continent; l_j++) {
                    while (true) {
                        if (!l_y.equals(l_cont_name[l_j])) {
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
                        if (!l_y.equals(l_country_names[l_k])) {
                            l_country_names[l_i] = l_y;
                            break;
                        } else {
                            System.out.println("Country Already Exists\n");
                            System.out.println("Enter another Country name");
                            l_y = l_sc.nextLine();
                        }
                    }
                }
                if (l_ct == 0) {
                    l_country_names[l_i] = l_y;
                }
                l_ct++;

                System.out.println("");
                for (int k = 0; k < l_continent; k++) {
                    System.out.println(k + 1 + " " + l_cont_name[k]);
                }

                System.out.println("");

                while (true) {
                    Scanner l_sc1 = new Scanner(System.in);
                    System.out.println("Enter the continent unique value in which you want to add country");
                    try {
                        int l_value = l_sc1.nextInt();
                        if (l_value > 0) {
                            int l_val = (int) l_adj.values().toArray()[l_value - 1];
                            int l_num = l_val - 1;

                            if ((int) l_adj.values().toArray()[l_value - 1] <= 0) {
                                System.out.println("Continent is full");
                                System.out.println("Enter another continent unique number");

                            } else if ((int) l_adj.values().toArray()[l_value - 1] > 0) {
                                l_adj.put((String) l_adj.keySet().toArray()[l_value - 1], l_num);
                                l_continent_country_value[l_i] = l_value;
                                break;
                            }
                        } else {
                            System.out.println("Unique number cannot be zero or -ve");
                        }
                    } catch (Exception p_e) {
                        System.out.println("Enter unique number from the above given list");
                    }
                }
                System.out.println("");
                l_i+=1;
            }

        }

        System.out.println("Now here are the list of countries");
        for (int l_k = 0; l_k < l_country_total; l_k++) {
            System.out.println((l_k + 1) + " " + l_country_names[l_k]);
        }
        System.out.println("");
        l_i = 0;
        while (l_i < l_country_total) {
            System.out.println("Enter the unique keys of adjacent countries of " + (l_i + 1) + " country");
            Scanner l_sc3 = new Scanner(System.in);
            l_adjacent_countries[l_i] = l_sc3.nextLine();
            l_i += 1;
        }
        while(true) {
            try {
                Scanner l_sc = new Scanner(System.in);
                System.out.println("Enter validatemap command");
                String l_reply = l_sc.nextLine();
                if (l_reply.equalsIgnoreCase("validatemap")) {
                    UserCreatedMapValidation l_validate = new UserCreatedMapValidation();
                    l_validate.mapValidate(p_file, l_cont_name, l_continent, l_cont_val, l_country_total, l_country_names, l_continent_country_value, l_adjacent_countries);
                    break;
                }
                else{
                    System.out.println("Enter valid command");
                }
            } catch (Exception p_e) {
                p_e.printStackTrace();
            }
        }
    }

    /**
     * This method is used to create and save the validated map that user created from scratch.
     * @param p_file This is the file name in which map is created and saved by user.
     * @param p_continent_name This parameter is a string array which contains the list of the continents.
     * @param p_continent This is an integer value which denotes the number of continents in a map.
     * @param p_continent_value This parameter is a string array which contains the list of the control values of continents.
     * @param p_country_total This is an integer value which denotes the number of countries in a map.
     * @param p_country_names This parameter is a string array which contains the list of the countries.
     * @param p_continent_country_value This is an integer array which contains the list of unique ID's of continent to which a country belong.
     * @param p_adjacent_countries This a string array which contains the list of neighboring countries of a particular country.
     * @throws Exception If file doesn't found at the directed path then this will throw exception.
     * @return a File Object.
     */
    public static File fileCreation(File p_file, String[] p_continent_name, int p_continent, String[] p_continent_value, int p_country_total, String[] p_country_names, int[] p_continent_country_value, String[] p_adjacent_countries) throws Exception {
        PrintWriter l_print_writer = new PrintWriter(p_file);
        l_print_writer.println("[Map]");
        l_print_writer.println("author=");
        l_print_writer.println("image=Board.map");
        l_print_writer.println("wrap=no");
        l_print_writer.println("scroll=horizontal");
        l_print_writer.println("warn=yes");
        l_print_writer.println(" ");
        l_print_writer.println("[continents]");
        for (int l_i = 0; l_i < p_continent; l_i++) {
            l_print_writer.println(p_continent_name[l_i] + " " + p_continent_value[l_i]);
        }
        l_print_writer.println("");
        l_print_writer.println("[countries]");
        for (int l_i = 0; l_i < p_country_total; l_i++) {
            l_print_writer.println((l_i + 1) + " " + p_country_names[l_i] + " " + p_continent_country_value[l_i]);
        }
        l_print_writer.println("");
        l_print_writer.println("[borders]");
        for (int l_i = 0; l_i < p_country_total; l_i++) {
            String[] l_temp = p_adjacent_countries[l_i].split(",");
            l_print_writer.print((l_i + 1) + " ");
            for (int l_j = 0; l_j < l_temp.length; l_j++) {

                l_print_writer.print(l_temp[l_j] + " ");
            }
            l_print_writer.println();
        }
        System.out.println("Let's checkout if graph is connected or not");
        l_print_writer.close();
        return p_file;
    }


}
