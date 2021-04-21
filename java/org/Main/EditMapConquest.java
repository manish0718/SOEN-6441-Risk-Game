package org.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class allows user to edit the map. Either it can be continent, countries or neighbours.
 */

public class EditMapConquest {
    static Scanner d_SC = new Scanner(System.in);

    /**
     * This method allows user to edit the map.
     * This method allows user to edit continent in a way that user can add the continent or remove the continent from the map.
     * This method also allows user to edit countries in a way that user can add country or remove country from the map.
     * It also allows user to edit neighbor as well in a way that user can add a neighbor to a country and can remove the country from it's neighboring country.
     * After editing this method will ask the user to enter the file name in which user want to save the changes.
     * After that it will ask the user to enter the validate map command so as before saving the map, it will be validated.
     * Furthermore if the map is valid then it will get saved in that file name else it will say that map is not valid and changes will not saved.
     * This method also checks if there is any wrong command enter by the user and tell the user that user entered the wrong command.
     * @param p_file This file is the map in which user can do the editing.
     * @throws Exception If there is some error or command entered by user is wrong, this will throws exception.
     */

    public static void editMap(String p_file) throws Exception {
        int l_flag_error = 0;
        while (true) {
            System.out.println("*************************");
            System.out.println("* 1- editcontinent *");
            System.out.println("* 2- editcountry   *");
            System.out.println("* 3- editneighbor *");
            System.out.println("*************************\n");

            System.out.println("");
            System.out.println("Enter your command to edit map");

            String l_option = d_SC.nextLine();
            String[] l_text = l_option.split("-");

            if (l_text[0].equalsIgnoreCase("editcontinent ")) {

                File l_file = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + p_file);

                MapTable l_list = new MapTable();
                ArrayList<String> l_countries = l_list.ConquestterritoriesList(l_file);
                ArrayList<String> l_continent = l_list.ConquestcontinentsList(l_file);
                HashMap<String, Integer> l_cont_val = l_list.Conquestcontinentsandvalue(l_file);
                HashMap<String, String> l_country_continent = l_list.Conquestcountryanditscontinent(l_file);
                HashMap<String, ArrayList> l_country_neighbour = l_list.Conquestcountryanditsneighbours(l_file);
                int l_flag_to_save = 0;

                for (int l_i = 1; l_i < l_text.length; l_i++) {

                    String[] l_command = l_text[l_i].split(" ");
                    if (l_command[0].equalsIgnoreCase("add")) {
                        if(l_command.length<3 || l_command.length>3)
                        {
                            System.out.println("Enter correct command");
                            l_flag_error=1;
                            l_flag_to_save=1;
                            break;
                        }
                        l_continent.add(l_command[1]);
                        l_cont_val.put(l_command[1], Integer.parseInt(l_command[2]));
                    }
                    else if (l_command[0].equalsIgnoreCase("remove")) {
                        if(l_command.length<2 || l_command.length>2)
                        {
                            System.out.println("Enter correct command");
                            l_flag_error=1;
                            l_flag_to_save=1;
                            break;
                        }
                        String l_continent_name = l_command[1];
                        int l_continent_index = l_continent.indexOf(l_continent_name);
                        if (l_continent.contains(l_continent_name)) {
                            l_continent.remove(l_continent_name);
                            l_cont_val.remove(l_continent_name);
                            ArrayList<String> l_country_in_continent = new ArrayList<>();
                            for (String l_country : l_country_continent.keySet()) {
                                if (l_country_continent.get(l_country).equalsIgnoreCase(l_continent_name)) {
                                    l_country_in_continent.add(l_country);
                                }
                            }
                            for (String l_country : l_country_in_continent) {
                                l_country_neighbour.remove(l_country);
                            }
                            for (String l_country : l_country_neighbour.keySet())
                            {
                                for (String l_c : l_country_in_continent) {
                                    if (l_country_neighbour.get(l_country).contains(l_c)) {
                                        l_country_neighbour.get(l_country).remove(l_c);
                                    }
                                }
                            }
                            Iterator<Map.Entry<String, String> >
                                    iterator = l_country_continent.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry entry = iterator.next();
                                if (l_continent_name.equals(entry.getValue())) {
                                    iterator.remove();
                                }
                            }
                        } else {
                            System.out.println("Continent doesn't exist.");
                            l_flag_to_save = 1;
                            l_flag_error=1;
                            break;
                        }
                    }
                    else
                    {
                        System.out.println("Enter correct command");
                        l_flag_error=1;
                        l_flag_to_save=1;
                        break;
                    }
                }
                if (l_flag_to_save == 0) {
                    String l_address;
                    while(true) {
                        System.out.println("To save changes enter save map command");
                        String l_out = d_SC.nextLine();
                        String[] l_filename = l_out.split(" ");
                        if (l_filename[0].equalsIgnoreCase("savemap")) {
                            if ((l_filename.length < 2 || l_filename.length > 2) || !l_filename[1].endsWith(".map")) {
                                System.out.println("Your command should be like savemap filename.map");
                                continue;
                            } else {
                                l_address = "C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + l_filename[1];
                                break;
                            }
                        }
                        else{
                            System.out.println("Enter valid command");
                            continue;
                        }
                    }
                    File l_file3 = new File(l_address);
                    l_flag_error = 0;

                    while(true) {
                        try {
                            System.out.println("Enter validatemap command");
                            String l_reply = d_SC.nextLine();
                            if (l_reply.equalsIgnoreCase("validatemap")) {
                                MapValidationConquest l_validate = new MapValidationConquest();
                                l_validate.mapValidate(l_file3, l_countries, l_continent, l_cont_val, l_country_continent, l_country_neighbour);
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
            }
            else if (l_text[0].equalsIgnoreCase("editcountry ")) {
                int l_flag1 = 0;
                int l_flag2 = 0;
                File l_file1 = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + p_file);

                MapTable l_list = new MapTable();
                ArrayList<String> l_countries = l_list.ConquestterritoriesList(l_file1);
                ArrayList<String> l_continent = l_list.ConquestcontinentsList(l_file1);
                HashMap<String, Integer> l_cont_val = l_list.Conquestcontinentsandvalue(l_file1);
                HashMap<String, String> l_country_continent = l_list.Conquestcountryanditscontinent(l_file1);
                HashMap<String, ArrayList> l_country_neighbours = l_list.Conquestcountryanditsneighbours(l_file1);
                int l_flag_save = 0;
                for (int l_i = 1; l_i < l_text.length; l_i++) {
                    String[] l_command = l_text[l_i].split(" ");
                    if (l_command[0].equalsIgnoreCase("add")) {
                        int l_num = 0;
                        if(l_command.length<3 || l_command.length>3)
                        {
                            System.out.println("Enter correct command");
                            l_flag_error=1;
                            l_flag_save=1;
                            break;
                        }
                        for (int l_j = 0; l_j < l_continent.size(); l_j++) {
                            if (l_command[2].equalsIgnoreCase(l_continent.get(l_j))) {
                                l_num = l_j + 1;
                                l_flag1 = 1;
                                break;
                            }
                        }

                        if (l_flag1 == 0) {
                            System.out.println("Continent do not exists");
                            l_flag_save = 1;
                            l_flag_error=1;
                            break;
                        }
                        if (l_flag1 == 1) {
                            l_country_continent.put(l_command[1],l_command[2]);

                        }
                    }
                    else if (l_command[0].equalsIgnoreCase("remove")) {
                        if(l_command.length<2 || l_command.length>2)
                        {
                            System.out.println("Enter correct command");
                            l_flag_error=1;
                            l_flag_save=1;
                            break;
                        }
                        for (int l_h = 0; l_h < l_countries.size(); l_h++) {
                            if (l_command[1].equalsIgnoreCase(l_countries.get(l_h))) {
                                l_flag2 = 1;
                                break;
                            }
                        }
                        if (l_flag2 == 0) {
                            System.out.println("country do not exists");
                            l_flag_save = 1;
                            l_flag_error=1;
                            break;
                        }
                        if (l_flag2 == 1) {
                            l_country_continent.remove(l_command[1]);
                            for (String l_country : l_country_neighbours.keySet()) {
                                if (l_country.equalsIgnoreCase(l_command[1])) {
                                    l_country_neighbours.remove(l_country);
                                    break;
                                }
                            }

                            for (String l_country : l_country_neighbours.keySet()) {
                                if (l_country_neighbours.get(l_country).contains(l_command[1])) {
                                    l_country_neighbours.get(l_country).remove(l_command[1]);
                                }
                            }

                        }
                    }
                    else
                    {
                        System.out.println("Enter correct command");
                        l_flag_error=1;
                        l_flag_save=1;
                        break;
                    }
                }
                if (l_flag_save == 0 ) {
                    String l_address;
                    while(true) {
                        System.out.println("To save changes enter save map command");
                        String l_out = d_SC.nextLine();
                        String[] l_filename = l_out.split(" ");
                        if (l_filename[0].equalsIgnoreCase("savemap")) {
                            if ((l_filename.length < 2 || l_filename.length > 2) || !l_filename[1].endsWith(".map")) {
                                System.out.println("Your command should be like savemap filename.map");
                                continue;
                            } else {
                                l_address = "C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + l_filename[1];
                                break;
                            }
                        }
                        else{
                            System.out.println("Enter valid command");
                            continue;
                        }
                    }
                    File l_file3 = new File(l_address);
                    l_flag_error=0;
                    while(true) {
                        try {
                            System.out.println("Enter validatemap command");
                            String l_reply = d_SC.nextLine();
                            if (l_reply.equalsIgnoreCase("validatemap")) {
                                MapValidationConquest l_validate = new MapValidationConquest();
                                l_validate.mapValidate(l_file3, l_countries, l_continent, l_cont_val, l_country_continent, l_country_neighbours);
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
            }
            else if (l_text[0].equalsIgnoreCase("editneighbor ")) {
                File l_file = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + p_file);
                MapTable l_list = new MapTable();
                ArrayList<String> l_countries = l_list.ConquestterritoriesList(l_file);
                ArrayList<String> l_continent = l_list.ConquestcontinentsList(l_file);
                HashMap<String, Integer> l_cont_val = l_list.Conquestcontinentsandvalue(l_file);
                HashMap<String, String> l_country_continent = l_list.Conquestcountryanditscontinent(l_file);
                HashMap<String, ArrayList> l_country_neighbours = l_list.Conquestcountryanditsneighbours(l_file);
                int l_flag_to_save=0;
                l_flag_error=0;
                for (int l_i = 1; l_i < l_text.length; l_i++) {
                    String[] l_command = l_text[l_i].split(" ");
                    if(l_command.length<3 || l_command.length>3)
                    {
                        System.out.println("Enter correct command");
                        l_flag_error=1;
                        l_flag_to_save=1;
                        break;
                    }
                    if (l_command[0].equalsIgnoreCase("add")) {
                        if (l_countries.contains(l_command[1])) {
                            if(l_country_neighbours.containsKey(l_command[1]))
                            {
                                ArrayList<String> l_list_of_neighbours = l_country_neighbours.get(l_command[1]);
                                l_list_of_neighbours.add(l_command[2]);
                                l_country_neighbours.put(l_command[1], l_list_of_neighbours);
                            }
                            else
                            {
                                ArrayList<String> l_list_of_neighbours = new ArrayList<>();
                                l_list_of_neighbours.add((l_command[2]));
                                l_country_neighbours.put(l_command[1],l_list_of_neighbours);
                            }
                        } else {
                            System.out.println("The country you entered doesn't exist");
                            l_flag_error=1;
                            l_flag_to_save=1;
                        }
                    } else if (l_command[0].equalsIgnoreCase("remove")) {
                        if (l_countries.contains(l_command[1])) {
                            ArrayList<String> l_list_of_neighbours = l_country_neighbours.get(l_command[1]);
                            l_list_of_neighbours.remove(l_command[2]);
                            l_country_neighbours.put(l_command[1], l_list_of_neighbours);
                        } else {
                            System.out.println("The country you requested doesn't exist");
                            l_flag_error=1;
                            l_flag_to_save=1;                        }
                    } else {
                        System.out.println("Enter valid command");
                        l_flag_error=1;
                        l_flag_to_save=1;
                        break;
                    }
                }
                if(l_flag_to_save==0) {
                    String l_address;
                    while(true) {
                        System.out.println("To save changes enter save map command");
                        String l_out = d_SC.nextLine();
                        String[] l_filename = l_out.split(" ");
                        if (l_filename[0].equalsIgnoreCase("savemap")) {
                            if ((l_filename.length < 2 || l_filename.length > 2) || !l_filename[1].endsWith(".map")) {
                                System.out.println("Your command should be like savemap filename.map");
                                continue;
                            } else {
                                l_address = "C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + l_filename[1];
                                break;
                            }
                        }
                        else{
                            System.out.println("Enter valid command");
                            continue;
                        }
                    }
                    File l_file2 = new File(l_address);
                    l_flag_error=0;
                    while(true) {
                        try {
                            System.out.println("Enter validatemap command");
                            String l_reply = d_SC.nextLine();
                            if (l_reply.equalsIgnoreCase("validatemap")) {
                                MapValidationConquest l_validate = new MapValidationConquest();
                                l_validate.mapValidate(l_file2, l_countries, l_continent, l_cont_val, l_country_continent, l_country_neighbours);
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
            }
            else {
                System.out.println("Check your commands and re-run");
            }

            if(l_flag_error == 0) {
                while(true) {
                    System.out.println("");
                    System.out.println("Want to continue editing or wanna see your map?");
                    System.out.println("If you want to continue editing enter command 'editing' or if you wish to go to main menu enter command 'main' ");
                    String l_yesOrNo = d_SC.nextLine();
                    String[] l_ans = l_yesOrNo.split(" ");
                    if (l_ans[0].equalsIgnoreCase("editing") && l_ans.length == 1) {
                        Main.EditMap();
                        break;
                    } else if (l_yesOrNo.equalsIgnoreCase("main") && l_ans.length == 1) {
                        break;
                    }else {
                        System.out.println("Enter correct command");
                        continue;
                    }
                }
                break;
            }
        }
    }

    /**
     * This method is called after when user is done with the editing and map is validated.
     * This method creates a file with the changes that user did and saves the file in the destination folder.
     * @param p_file This is the file name in which map is created and saved.
     * @param p_country_continent  This is hashmap which contain the country name as the key and it's respective continent as the value.
     * @param p_cont_val This is a data structure, basically a hashmap which denotes the continent names as the key and the control value of that continent as the value.
     * @param l_country_neighbour This is the hashmap which contains the country as the key and it's neighbors as it's value.
     * @return the object of the class File.
     * @throws FileNotFoundException If file is not present at the provided path it will throw exception.
     */
    public static File fileCreation(File p_file, HashMap<String, String> p_country_continent, HashMap<String, Integer> p_cont_val,HashMap<String, ArrayList> l_country_neighbour) throws FileNotFoundException {
        PrintWriter l_printWriter = new PrintWriter(p_file);
        l_printWriter.println("[Map]");
        l_printWriter.println("author=");
        l_printWriter.println("image=Board.map");
        l_printWriter.println("wrap=no");
        l_printWriter.println("scroll=horizontal");
        l_printWriter.println("warn=yes");
        l_printWriter.println(" ");
        l_printWriter.println("[Continents]");
        for (Map.Entry<String,Integer> l_entry : p_cont_val.entrySet()) {
            l_printWriter.println(l_entry.getKey() + "=" + l_entry.getValue());
        }
        l_printWriter.println(" ");
        l_printWriter.println("[Territories]");
        for(String l_countryName: p_country_continent.keySet())
        {
            int l_x = (int) (Math.random()*10)+5;
            int l_y = (int) (Math.random()*10)+7;
            l_printWriter.print(l_countryName+","+l_x+","+l_y+","+p_country_continent.get(l_countryName));
            if(l_country_neighbour.get(l_countryName) != null){
                for (Object l_neighbouringCountry : l_country_neighbour.get(l_countryName)) {
                    l_printWriter.print("," + l_neighbouringCountry.toString());
                }
            }
            l_printWriter.println();
        }

        l_printWriter.println("");

        System.out.println("Let's see if the map is connected or not");
        l_printWriter.close();
        return p_file;
    }

}
