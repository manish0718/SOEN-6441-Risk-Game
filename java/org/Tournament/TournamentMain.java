package org.Tournament;

import org.GamePlay.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tournament mode class
 */
public class TournamentMain {

    String d_listOfMapFiles[] ;
    public  ConcurrentHashMap<String, Player> d_PLAYERS_LIST = new ConcurrentHashMap<String, Player>(); // concurrent only - thread safe .
    public  ConcurrentHashMap<String, Country> d_COUNTRIES = new ConcurrentHashMap<>();
    public HashMap<String,String> d_gameState = new HashMap<>();
    Country d_country;
    File d_file;
    public  int d_numberofgames , j =0;
    public  int d_maxnumberofturns , k =0;
    public  int d_noOfMap , i =0;
    static int d_count ;
    static String d_winningPlayer ;
    Assign d_assign;
    TournamentGameEngine d_gameEngine ;
    public playGame d_play;
    public ArrayList<String> d_winners = new ArrayList<String>();


    String[] d_playersName;
    String D_winner;

    /**
     * This is from where tournament mode starts
     * @return runs overloaded run function
     */
    public String run() {
        System.out.println("Enter command to start the tournament mode");
        Scanner l_sc = new Scanner(System.in);
        String l_command = l_sc.nextLine();
        return run(l_command);
    }

    public String run(String l_command){
        d_gameEngine = new TournamentGameEngine();
        String[] l_commandSplit= l_command.split("-");
        if(l_commandSplit[0].equalsIgnoreCase("tournament ") && l_commandSplit.length==5)
        {
            d_listOfMapFiles = new String[l_commandSplit[1].split(" ").length-1];
            int i=0;
            if(l_commandSplit[1].split(" ")[0].equalsIgnoreCase("m")) {
                for (String map : l_commandSplit[1].split(" ")) {
                    if (map.equalsIgnoreCase("m")) {
                        continue;
                    } else {
                        d_listOfMapFiles[i] = map;
                        i += 1;
                    }
                }
            }
            else
            {
                System.out.println("Enter correct map command.");
                return "Enter correct map command";
            }
            i=0;
            if(l_commandSplit[2].split(" ")[0].equalsIgnoreCase("p"))
            {
                d_playersName= new String[l_commandSplit[2].split(" ").length];
                for(String l_playerName: l_commandSplit[2].split(" "))
                {
                    if(l_playerName.equalsIgnoreCase("p"))
                    {
                        continue;
                    }
                    else {
                        d_playersName[i] = l_playerName;
                        i+=1;
                    }
                }

            }
            else{
                System.out.println("Enter correct Player command.");
                return "Enter correct Player command";
            }

            if(l_commandSplit[3].split(" ")[0].equalsIgnoreCase("g"))
            {
                d_numberofgames= Integer.parseInt(l_commandSplit[3].split(" ")[1]);

            }
            else
            {
                System.out.println("Enter correct Game command");
                return "Enter correct Game command";
            }

            if(l_commandSplit[4].split(" ")[0].equalsIgnoreCase("d"))
            {
                d_maxnumberofturns = Integer.parseInt(l_commandSplit[4].split(" ")[1]);
            }
            else
            {
                System.out.println("Enter correct no of turns command");
                return "Enter correct no of turns command";
            }
        }
        else
        {
            System.out.println("Enter the correct command");
            return "Wrong command";
        }

        for (int i = 0; i < d_listOfMapFiles.length; i++) {
            System.out.println("Map" + d_listOfMapFiles[i] + " : ");
            d_file = new File("src/main/resources/maps/" + d_listOfMapFiles[i]);


            for (int j = 0; j < d_numberofgames; j++) {
                for(int t = 0 ; t<d_playersName.length -1 ; t++)
                {
                    d_PLAYERS_LIST.put(d_playersName[t], new Player(d_playersName[t]));
                }

                d_assign = new Assign(d_PLAYERS_LIST,d_country);
                d_assign.assignCountries(d_file);
                d_COUNTRIES = d_assign.d_country.COUNTRIESLIST;
                d_country = d_assign.d_country;

                d_play = new playGame(d_PLAYERS_LIST,d_country);
                d_play.playGameLoop();
                int k = j+1;
                d_gameState = d_gameEngine.gamePlay(d_file,d_maxnumberofturns,d_PLAYERS_LIST,d_country,d_listOfMapFiles[i],d_gameState);
                d_winners.add("Winner of game " + k +" on map " + d_listOfMapFiles[i] + " is " +d_gameState.get(d_listOfMapFiles[i]) +"\n");
            }

            System.out.println(d_winners + "\n");
        }
        return "Tournament Over";
    }



}
