package org.GamePlay;

import org.Main.MapTable;
import org.ObserverBasedLogging.LogEntryBuffer;
import org.ObserverBasedLogging.LogFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Assign countries to all players
 */
public class Assign {
    ConcurrentHashMap<String, Player> d_playersList = new ConcurrentHashMap<>();
    public Country d_country;

    /**
     * Constructor to initialise
     *
     * @param p_players_list list of players
     * @param p_country      country to be assigned
     */
    public Assign(ConcurrentHashMap<String, Player> p_players_list, Country p_country) {
        this.d_playersList = p_players_list;
        this.d_country = p_country;
    }

    /**
     * Assign all the countries
     *
     * @param p_file Map File
     */
    public void assignCountries(File p_file) {
        LogEntryBuffer l_observable = new LogEntryBuffer();
        LogFile l_observer = new LogFile();
        l_observable.addObserver(l_observer);

        Scanner l_sc = new Scanner(System.in);
        System.out.println("There are " + d_playersList.size() + " Players");
        int l_i = 1;
        System.out.println("");
        for (String l_string : d_playersList.keySet()) {
            System.out.println("Player " + l_i + " : " + l_string);
            l_i++;
        }
        System.out.println("");
        System.out.println("Starting assigning countries");

        MapTable l_table = new MapTable();
        HashMap<Integer, String> l_temp_map = new HashMap<>();
        HashMap<String, String> l_country_continent = new HashMap<>();
        HashMap<String, Integer> l_continent_key = new HashMap<>();
        HashMap<String, ArrayList> l_country_neighbour = new HashMap<>();
        try {
            l_temp_map = l_table.countryanditskey(p_file);
            l_country_continent = l_table.countryanditscontinent(p_file); //String-String
            l_continent_key = l_table.continentandvalue(p_file); //String-Integer
            l_country_neighbour = l_table.countryanditsneighbours(p_file);
        } catch (Exception e) {
        }

        for (String l_z : l_temp_map.values()) {
            int l_number_of_countries_in_continent = 0;
            for (String l_country : l_country_continent.keySet()) {
                if (l_country_continent.get(l_country).equalsIgnoreCase(l_country_continent.get(l_z))) {
                    l_number_of_countries_in_continent += 1;
                }
            }
            if (l_country_continent.containsKey(l_z)) {
                Integer l_continent_control_value = l_continent_key.get(l_country_continent.get(l_z));
                d_country = new Country(l_z, l_country_continent.get(l_z), l_continent_control_value, l_number_of_countries_in_continent, l_country_neighbour.get(l_z));
                d_country.COUNTRIESLIST.put(l_z, d_country);
            }
        }
        ArrayList<String> l_total_countries = new ArrayList<>();
        for (String l_country : d_country.COUNTRIESLIST.keySet()) {
            l_total_countries.add(this.d_country.COUNTRIESLIST.get(l_country).d_countryId);
        }
        int l_num_of_countries_per_player = l_total_countries.size() / d_playersList.size();
        int l_remaining_countries = l_total_countries.size() % d_playersList.size();

        Random l_random = new Random();
        String l_message;
        for (String l_player_name1 : d_playersList.keySet()) {
            int l_x = 0;
            l_message="";
            l_message ="Countries assigned to player "+l_player_name1+"= ";
            ArrayList<Country> l_countries_owned = new ArrayList<>();
            while (l_x < l_num_of_countries_per_player) {
                int l_random_int = l_random.nextInt(l_total_countries.size());
                Country l_country = this.d_country.COUNTRIESLIST.get(l_total_countries.get(l_random_int));
                l_message+= l_country.d_countryId+" ";
                l_countries_owned.add(l_country);
                this.d_country.COUNTRIESLIST.get(l_total_countries.get(l_random_int)).d_owner = l_player_name1;
                l_total_countries.remove(l_random_int);
                l_x += 1;
            }
            Player l_player = new Player(l_player_name1);
            for (int l_f = 0; l_f < l_countries_owned.size(); l_f++) {
                l_player.d_owned.add(l_countries_owned.get(l_f));
            }
            d_playersList.put(l_player_name1, l_player);
            l_observable.setMsg(l_message);
        }
        if (l_remaining_countries != 0) {
            ArrayList<String> l_player_names = new ArrayList<>();
            for (String l_playername : d_playersList.keySet()) {
                l_player_names.add(l_playername);
            }
            for (int l_j = 0; l_j < l_remaining_countries; l_j++) {
                int l_random_player_index = l_random.nextInt(l_player_names.size());
                int l_random_country_index = l_random.nextInt(l_total_countries.size());
                Country l_country = this.d_country.COUNTRIESLIST.get(l_total_countries.get(l_random_country_index));
                d_playersList.get(l_player_names.get(l_random_player_index)).d_owned.add(l_country);
                this.d_country.COUNTRIESLIST.get(l_total_countries.get(l_random_country_index)).d_owner = l_player_names.get(l_random_player_index);
                l_player_names.remove(l_random_player_index);
                l_total_countries.remove(l_random_country_index);
            }
        }
        for (String l_player : d_playersList.keySet()) {
            System.out.print(l_player + " = ");
            for (int l_k = 0; l_k < d_playersList.get(l_player).d_owned.size(); l_k++) {
                System.out.print(d_playersList.get(l_player).d_owned.get(l_k).d_countryId + " ");
            }
            System.out.println("");
        }
    }
}
