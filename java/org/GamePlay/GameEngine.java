package org.GamePlay;

import org.Main.*;
import org.ObserverBasedLogging.LogEntryBuffer;
import org.ObserverBasedLogging.LogFile;
import org.StatePattern.End;
import org.StrategyPattern.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Game Engine Class for Stratup Phase and all three game phases of Main game loop
 * Phase 1 : Reinforcement Phase
 * Phase 2 : Issue Order Phase
 * Phase 3 : Execute Order Phase
 */
public class GameEngine {
    /**
     * GLobal List of Players
     */
    public static ConcurrentHashMap<String, Player> d_PLAYERS_LIST = new ConcurrentHashMap<String, Player>(); // concurrent only - thread safe .
    public static ArrayList<String> d_Behaviours = new ArrayList<String>();
    public void set_Behaviour(){
        d_Behaviours.add("random");
        d_Behaviours.add("human");
        d_Behaviours.add("aggressive");
        d_Behaviours.add("cheater");
        d_Behaviours.add("benevolent");
    }

    public playGame d_play;
    public Assign d_assign;
    public Order d_order;
    /**
     * Global Country Object
     */
    public static Country d_COUNTRY;
    /**
     * Global variable for File
     */
    public File d_FILE;
    /**
     * Global variable for storing the message.
     */
    public String d_message;
    String d_filename;

    /**
     * This where startegy patter orders are executed
     */
    public void executeAllOrders() {
        System.out.println("===============BEGIN EXECUTING ALL ORDERS=================");
        Order l_order;
        boolean l_still_more_orders = false;
        do {
            l_still_more_orders = false;
            for (String l_s : d_PLAYERS_LIST.keySet()) {
                Player p = d_PLAYERS_LIST.get(l_s);
                l_order = p.next_order();
                if (l_order != null) {
                    l_still_more_orders = true;
                    l_order.Execute(p);
                }
            }
        } while (l_still_more_orders);
        System.out.println("===============END EXECUTING ALL ORDERS===================");
    }


    /**
     * Game Startup Phase Implementation
     *
     * @param p_file Map File
     */
    public void startGameEngine(File p_file) {
        Scanner l_sc = new Scanner(System.in);
        String l_player_name = "";
        Boolean l_flag = true;
        System.out.println("*************************");
        System.out.println("Startup Phase");
        set_Behaviour();
        int l_counter=0;
        while (l_flag) {
            System.out.println("");
            System.out.println("*************************");
            System.out.println("1.Add/Remove Player");
            System.out.println("*************************");
            String l_command = l_sc.nextLine();
            String[] l_command_split = l_command.split("-");
            if (l_command_split[0].equalsIgnoreCase("gameplayer ")&&l_command_split.length>1) {
                for (int l_i = 1; l_i < l_command_split.length; l_i++) {
                    String l_command_split1[] = l_command_split[l_i].split(" ");
                    if (l_command_split1[0].equalsIgnoreCase("add")) {
                        if (d_PLAYERS_LIST.containsKey(l_command_split1[1])) {
                            d_message = "Player " + l_command_split1[1] + " already exists Please re-enter your name";
                            l_counter+= 1;
                            l_command_split1[1]+="-" + l_counter;
                            System.out.println(l_command_split1[1]);
                        }

                        d_message = "Player added";
                        d_PLAYERS_LIST.put(l_command_split1[1], new Player(l_command_split1[1]));
                        l_flag=false;
                    } else if (l_command_split1[0].equalsIgnoreCase("remove")) {
                        d_message = "Player Removed";
                        d_PLAYERS_LIST.remove(l_command_split1[1]);
                        l_flag=false;
                    }
                }
            } else {
                System.out.println("Wrong command. Please re-check");
            }
            System.out.println("");
            System.out.println("All Players as of now");
            for (String l_s : d_PLAYERS_LIST.keySet()) {
                Player p = d_PLAYERS_LIST.get(l_s);
                System.out.println(p.getD_name());
            }

        }
    }

    /**
     * This is where Strategy pattern implements
     * @param p_file
     */
    public void gamePlay(File p_file){

        d_play = new playGame(d_PLAYERS_LIST,d_COUNTRY);
        d_assign = new Assign(d_PLAYERS_LIST,d_COUNTRY);
        d_assign.assignCountries(p_file);
        int val = d_play.playGameLoop();

        for (String l_s : d_PLAYERS_LIST.keySet()) {
            Player p = d_PLAYERS_LIST.get(l_s);
            System.out.println(p.getD_name());
            if(l_s.contains("-")){
                String[] l_name = l_s.split("-");
                if(l_name[0].equalsIgnoreCase("random")){
                    p.setStrategy(new RandomPlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_COUNTRY));
                }
                if(l_name[0].equalsIgnoreCase("aggressive")){
                    p.setStrategy(new AggressivePlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_COUNTRY));
                }
                if(l_name[0].equalsIgnoreCase("benevolent")){
                    p.setStrategy(new BenevolentPlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_COUNTRY));
                }
                if(l_name[0].equalsIgnoreCase("cheater")){
                    p.setStrategy(new CheaterPlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_PLAYERS_LIST));
                }
                if(l_name[0].equalsIgnoreCase("human")){
                    p.setStrategy(new HumanPlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_PLAYERS_LIST,d_COUNTRY));
                }

            }
            if(l_s.equalsIgnoreCase("random")){
                p.setStrategy(new RandomPlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_COUNTRY));
            }
            if(l_s.equalsIgnoreCase("aggressive")){
                p.setStrategy(new AggressivePlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_COUNTRY));
            }
            if(l_s.equalsIgnoreCase("benevolent")){
                p.setStrategy(new AggressivePlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_COUNTRY));
            }
            if(l_s.equalsIgnoreCase("cheater")){
                p.setStrategy(new CheaterPlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_PLAYERS_LIST));
            }
            if(l_s.equalsIgnoreCase("human")){
                p.setStrategy(new HumanPlayerStrategy(p,d_COUNTRY.COUNTRIESLIST,d_PLAYERS_LIST,d_COUNTRY));
            }

        }
        showMap sMap = new showMap(d_PLAYERS_LIST,d_COUNTRY);
        LogEntryBuffer l_observable = new LogEntryBuffer();
        LogFile l_observer = new LogFile();
        l_observable.addObserver(l_observer);
        boolean end = true;
        while (end) {
            boolean an_order = true;

            do {
                for (String l_player : d_PLAYERS_LIST.keySet()) {
                    Player p = d_PLAYERS_LIST.get(l_player);
                    an_order = p.issue_order(d_order);
                    if (!an_order)
                        break;
                }
                for(String l_player: d_PLAYERS_LIST.keySet())
                {
                    if (d_PLAYERS_LIST.get(l_player).d_owned.size()==0)
                    {
                        d_PLAYERS_LIST.remove(l_player);
                        d_message = l_player+" you have lost the game. So you're out of the game!!!";
                        System.out.println(d_message);
                        l_observable.setMsg(d_message);
                    }
                }
                if(d_PLAYERS_LIST.size()==1)
                {
                    for(String player: d_PLAYERS_LIST.keySet()) {
                        d_message = player + " is the winner of the game!!!!!!!!";
                        System.out.println(d_message);
                        l_observable.setMsg(d_message);
                        end = false;
                        break;
                    }
                    break;
                }
                sMap.check();
                executeAllOrders();
            } while (an_order);

        }

    }

    /**
     * Main Game loop
     * Phase 1 : Reinforcement Phase
     * Phase 2 : Issue Order Phase
     * Phase 3 : Execute Order Phase
     */
    public void playGame() {
        Scanner l_sc = new Scanner(System.in);
        if(d_FILE==null) // This if-else is just for testing (MapValidation.java file in Testing-->Gameplay).
        {
            while (true) {
                System.out.println("**************************************");
                System.out.println("Enter 'loadmap' command to continue");
                System.out.println("**************************************");
                String l_command = l_sc.nextLine();
                String[] l_com = l_command.split(" ");

                GameEngine l_game = new GameEngine();
                int d_tempFlag=1;
                if (l_com[0].equalsIgnoreCase("loadmap")) {
                    if (l_com.length == 2) {
                        if (l_com[1].endsWith(".map")) {
                            d_filename = l_com[1];
                            d_FILE = new File(".\\src\\main\\resources\\maps\\" + d_filename);
                            if (d_FILE.exists()) {
                                int l_flag=0;
                                Scanner l_sc3 = null;
                                try {
                                    l_sc3 = new Scanner(d_FILE);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                while (l_sc3.hasNextLine()) {
                                    String x =  l_sc3.next();
                                    if (x.equals("[countries]")) {
                                        l_flag = 1;
                                        break;
                                    }
                                    if (x.equals("[Territories]")) {
                                        l_flag = 2;
                                        break;
                                    }
                                }

                                if (l_flag == 1) {
                                    MapValidation l_validation = new MapValidation();
                                    MapTable l_list = new MapTable();
                                    ArrayList<String> l_countries;
                                    ArrayList<String> l_continent;
                                    HashMap<String, Integer> l_contval;
                                    HashMap<Integer, String> l_countrykey;
                                    HashMap<String, String> l_countrycont;
                                    HashMap<String, ArrayList> l_countryneigh;
                                    HashMap<String, Integer> l_country_cont_key;
                                    HashMap<String, Integer> l_cont_unique_key;
                                    try {
                                        l_countries = l_list.countryList(d_FILE);
                                        l_continent = l_list.continentList(d_FILE);
                                        l_contval = l_list.continentandvalue(d_FILE);
                                        l_countrykey = l_list.countryanditskey(d_FILE);
                                        l_countrycont = l_list.countryanditscontinent(d_FILE);
                                        l_countryneigh = l_list.countryanditsneighbours(d_FILE);
                                        l_country_cont_key = l_list.countryanditsuniquecontinent(d_FILE);
                                        l_cont_unique_key = l_list.uniqueKeyanditscountry(d_FILE);
                                        l_validation.mapValidate(d_FILE, l_countries, l_continent, l_contval, l_countrykey, l_countrycont, l_countryneigh, l_country_cont_key, l_cont_unique_key);
                                    } catch (Exception e) {
                                    }
                                    d_tempFlag = l_validation.d_final_flag;
                                }

                                if(l_flag == 2){
                                    MapValidationConquest l_validation = new MapValidationConquest();
                                    MapTableConquest l_list = new MapTableConquest();
                                    ArrayList<String> l_countries;
                                    ArrayList<String> l_continent;
                                    HashMap<String, Integer> l_contval;
                                    HashMap<Integer, String> l_countrykey;
                                    HashMap<String, String> l_countrycont;
                                    HashMap<String, ArrayList> l_countryneigh;
                                    try {
                                        l_countries = l_list.ConquestterritoriesList(d_FILE);
                                        l_continent = l_list.ConquestcontinentsList(d_FILE);
                                        l_contval = l_list.Conquestcontinentsandvalue(d_FILE);
                                        l_countrykey=l_list.listCountryIdConquests(d_FILE);
                                        l_countrycont = l_list.Conquestcountryanditscontinent(d_FILE);
                                        l_countryneigh = l_list.Conquestcountryanditsneighbours(d_FILE);
                                        l_validation.mapValidate(d_FILE, l_countries, l_continent, l_contval, l_countrycont, l_countryneigh);
                                    } catch (Exception e) {
                                    }
                                    d_tempFlag = l_validation.d_final_flag;
                                }

                                if (d_tempFlag == 0) {
                                    d_message = "Map Validated";
                                    break;
                                } else {
                                    System.out.println("Invalid Map.");
                                    d_message = "Invalid Map";
                                    continue;
                                }

                            } else {
                                System.out.println("File you entered doesn't exist\n");
                                d_message = "File you entered doesn't exist";
                                continue;
                            }

                        } else {
                            System.out.println("Enter the command with .map extension");
                            d_message = "Enter the command with .map extension";
                            continue;
                        }
                    } else {
                        System.out.println("Wrong command. Enter 'loadmap filename.map'");
                        d_message = "Wrong command. Enter 'loadmap filename.map'";
                        continue;
                    }
                } else {
                    d_message = "Wrong Command";
                    System.out.println("Wrong command. Please retry");
                }
            }
        }
        else
        {
            if (d_FILE.exists()) {
                MapValidation l_validation = new MapValidation();

                MapTable l_list = new MapTable();
                ArrayList<String> l_countries;
                ArrayList<String> l_continent;
                HashMap<String, Integer> l_contval;
                HashMap<Integer, String> l_countrykey;
                HashMap<String, String> l_countrycont;
                HashMap<String, ArrayList> l_countryneigh;
                HashMap<String, Integer> l_country_cont_key;
                HashMap<String, Integer> l_cont_unique_key;
                try {
                    l_countries = l_list.countryList(d_FILE);
                    l_continent = l_list.continentList(d_FILE);
                    l_contval = l_list.continentandvalue(d_FILE);
                    l_countrykey = l_list.countryanditskey(d_FILE);
                    l_countrycont = l_list.countryanditscontinent(d_FILE);
                    l_countryneigh = l_list.countryanditsneighbours(d_FILE);
                    l_country_cont_key = l_list.countryanditsuniquecontinent(d_FILE);
                    l_cont_unique_key = l_list.uniqueKeyanditscountry(d_FILE);
                    l_validation.mapValidate(d_FILE, l_countries, l_continent, l_contval, l_countrykey, l_countrycont, l_countryneigh, l_country_cont_key, l_cont_unique_key);
                } catch (Exception e) {
                }

                if (l_validation.d_final_flag == 0) {
                    d_message = "Map Validated";
                } else {
                    System.out.println("Invalid Map.");
                    d_message = "Invalid Map";
                }

            } else {
                System.out.println("File you entered doesn't exist\n");
                d_message = "File you entered doesn't exist";
            }
        }
    }
}
