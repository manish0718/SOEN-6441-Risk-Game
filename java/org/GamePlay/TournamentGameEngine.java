package org.GamePlay;

import org.ObserverBasedLogging.LogEntryBuffer;
import org.ObserverBasedLogging.LogFile;
import org.StrategyPattern.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Game Engine Class for Stratup Phase and all three game phases of Main game loop
 * Phase 1 : Reinforcement Phase
 * Phase 2 : Issue Order Phase
 * Phase 3 : Execute Order Phase
 */
public class TournamentGameEngine {
    /**
     * GLobal List of Players
     */
    public static ConcurrentHashMap<String, Player> PLAYERS_LIST = new ConcurrentHashMap<String, Player>(); // concurrent only - thread safe .
    HashMap<String, String> winner = new HashMap<>();
    public static ArrayList<String> Behaviours = new ArrayList<String>();

    public void set_Behaviour() {
        Behaviours.add("random");
        Behaviours.add("human");
        Behaviours.add("aggressive");
        Behaviours.add("cheater");
        Behaviours.add("benevolent");
    }

    public playGame play;
    public Assign assign;
    public Order order;
    /**
     * Global Country Object
     */
    public static Country COUNTRY;
    /**
     * Global variable for File
     */
    public File FILE;
    /**
     * Global variable for storing the message.
     */
    public String d_message;

    public void executeAllOrders() {
        System.out.println("===============BEGIN EXECUTING ALL ORDERS=================");
        Order order;
        boolean still_more_orders = false;
        do {
            still_more_orders = false;
            for (String l_s : PLAYERS_LIST.keySet()) {
                Player p = PLAYERS_LIST.get(l_s);
                order = p.next_order();
                if (order != null) {
                    still_more_orders = true;
                    order.Execute(p);
                }
            }
        } while (still_more_orders);
        System.out.println("===============END EXECUTING ALL ORDERS===================");
    }


    public HashMap<String, String> gamePlay(File p_file, int turns, ConcurrentHashMap<String, Player> p_playerList, Country p_country, String map, HashMap<String, String> mapInfo) {

        PLAYERS_LIST = p_playerList;
        COUNTRY = p_country;
        for (String l_s : PLAYERS_LIST.keySet()) {
            Player p = PLAYERS_LIST.get(l_s);
            System.out.println(p.getD_name());
            if (l_s.contains("-")) {
                String[] l_name = l_s.split("-");
                if (l_name[0].equalsIgnoreCase("random")) {
                    p.setStrategy(new RandomPlayerStrategy(p, COUNTRY.COUNTRIESLIST, COUNTRY));
                }
                if (l_name[0].equalsIgnoreCase("aggressive")) {
                    p.setStrategy(new AggressivePlayerStrategy(p, COUNTRY.COUNTRIESLIST, COUNTRY));
                }
                if (l_name[0].equalsIgnoreCase("benevolent")) {
                    p.setStrategy(new BenevolentPlayerStrategy(p, COUNTRY.COUNTRIESLIST, COUNTRY));
                }
                if (l_name[0].equalsIgnoreCase("cheater")) {
                    p.setStrategy(new CheaterPlayerStrategy(p, COUNTRY.COUNTRIESLIST, PLAYERS_LIST));
                }
                if (l_name[0].equalsIgnoreCase("human")) {
                    p.setStrategy(new HumanPlayerStrategy(p, COUNTRY.COUNTRIESLIST, PLAYERS_LIST, COUNTRY));
                }

            }
            if (l_s.equalsIgnoreCase("random")) {
                p.setStrategy(new RandomPlayerStrategy(p, COUNTRY.COUNTRIESLIST, COUNTRY));
            }
            if (l_s.equalsIgnoreCase("aggressive")) {
                p.setStrategy(new AggressivePlayerStrategy(p, COUNTRY.COUNTRIESLIST, COUNTRY));
            }
            if (l_s.equalsIgnoreCase("benevolent")) {
                p.setStrategy(new AggressivePlayerStrategy(p, COUNTRY.COUNTRIESLIST, COUNTRY));
            }
            if (l_s.equalsIgnoreCase("cheater")) {
                p.setStrategy(new CheaterPlayerStrategy(p, COUNTRY.COUNTRIESLIST, PLAYERS_LIST));
            }
            if (l_s.equalsIgnoreCase("human")) {
                p.setStrategy(new HumanPlayerStrategy(p, COUNTRY.COUNTRIESLIST, PLAYERS_LIST, COUNTRY));
            }

        }
        showMap sMap = new showMap(PLAYERS_LIST, COUNTRY);
        LogEntryBuffer l_observable = new LogEntryBuffer();
        LogFile l_observer = new LogFile();
        l_observable.addObserver(l_observer);
        int counter = 0;
        boolean end = true;
        while (end) {
            boolean an_order = true;


            do {
                for (String l_player : PLAYERS_LIST.keySet()) {
                    Player p = PLAYERS_LIST.get(l_player);
                    an_order = p.issue_order(order);
                    if (!an_order)
                        break;
                }
                {
                    counter++;
                    if (counter == turns) {
                        if (PLAYERS_LIST.size() > 1) {
                            mapInfo.put(map, "DRAW");
                            //System.out.println("Nobody is the winner, Its a draw");
                            return mapInfo;
                        }
                        break;
                    }
                    for (String l_player : PLAYERS_LIST.keySet()) {
                        if (PLAYERS_LIST.get(l_player).d_owned.size() == 0) {
                            PLAYERS_LIST.remove(l_player);
                            //d_message = l_player + " you have lost the game. So you're out of the game!!!";
                            System.out.println(d_message);
                            l_observable.setMsg(d_message);
                        }
                    }
                    if (PLAYERS_LIST.size() == 1) {
                        for (String player : PLAYERS_LIST.keySet()) {
                            //   d_message = player + " is the winner of the game!!!!!!!!";
                            System.out.println(d_message);
                            l_observable.setMsg(d_message);
                            end = false;
                            mapInfo.put(map, player);
                            return mapInfo;
                        }
                        break;
                    }
                }

                //sMap.check();
                executeAllOrders();
            } while (an_order);


        }
        return mapInfo;

    }
}
