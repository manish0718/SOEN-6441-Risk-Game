package org.GamePlay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.StatePattern.serialObj;
/**
 * This class is used to assign the troops
 * Deploy the reinforcements
 * show the map
 */
public class playGame {

    ConcurrentHashMap<String, Player> d_playerList = new ConcurrentHashMap<>();
    Country d_country;
    public int d_temp = 0;
    /**
     * Parameterised constructor for playGame
     *
     * @param p_playerList list of players playing the game
     * @param p_cou        the list of countries in the game
     */
    public playGame(ConcurrentHashMap<String, Player> p_playerList, Country p_cou) {
        this.d_playerList = p_playerList;
        this.d_country = p_cou;
    }

    /**
     * Method for Assignments of the reinforcements. This method will ask from the player about the order. And will save it to the the orders queue
     * of the respective player if the user inputs the correct command. There are 3 Main commands.
     * Deploy: Command which is used to deploy the armies on the particular country. The country should be available in the Map.
     * Pass: This command is used to pass the turn to the next player if the player doesn't want to give any deploy order. This function will terminate if all the players give the Pass Command
     * ShowMap: Command which shows the details about the countries.
     * @return l_armiesByCountries which is used for the testing the number of armies given to the player
     */
    public int playGameLoop() {
        int l_armiesByCountries = 0;

        System.out.println("Main game loop: assign reinforcements phase");
        for (String l_player : d_playerList.keySet()) {
            HashMap<String,ArrayList<Country>> l_continentCountry = new HashMap<>();
            Player l_player1 = d_playerList.get(l_player);
            System.out.println("For Player : " + l_player1.getD_name());
            l_armiesByCountries = (int) Math.floor(l_player1.d_owned.size() / 3);
            if (l_armiesByCountries < 3) {
                l_armiesByCountries = 3;
            }

            for(Country l_country: d_playerList.get(l_player).d_owned)
            {
                if(l_continentCountry.containsKey(l_country.d_continent))
                {
                    l_continentCountry.get(l_country.d_continent).add(l_country);
                }
                else
                {
                    l_continentCountry.put(l_country.d_continent, new ArrayList(Arrays.asList(l_country)));
                }
            }

            for(String l_continent: l_continentCountry.keySet())
            {
                if(l_continentCountry.get(l_continent).size()==l_continentCountry.get(l_continent).get(0).d_numberOfCountriesInContinent)
                {
                    l_player1.d_continentValue+= l_continentCountry.get(l_continent).get(0).d_continentBonus ;
                }
            }


            l_player1.d_armiesNum = l_armiesByCountries + l_player1.d_continentValue;
            d_temp = l_player1.d_armiesNum;
            System.out.println("Army count for " + l_player1.d_name + "--> " + l_player1.d_armiesNum);
            System.out.println("");
        }
        return d_temp;
    }

    /**
     * Method to run the Main Game Loop. Various orders that a player can use are:
     * Deploy Order deploys the player's armies at the certain country.
     * Pass is the order to pass the turn from one player to another player.
     * Showmap is the command to display all the contents of the map file along with owner of the country and the armies allocated to the certain player.
     */

    public Order mainGameLoop() {
        System.out.println("Human Player: issue orders phase");

        int l_i = 0;

        Player l_p = d_playerList.get("human");
        Boolean l_flag_1 = true;
        while (l_flag_1) {  //loop for correct input for order until a player inputs correct order
            System.out.println(l_p.d_name + " Please issue orders from the below commands");
            System.out.println("*************************");
            System.out.println("**Deploy**\n**Advance**\n**Cards**\n    -Bomb\n    -Blockade\n    -Airlift\n    -Negotiate\n**Pass**\n**ShowMap**\n**Savegame**");
            System.out.println("*************************");
            Scanner l_sc = new Scanner(System.in);
            String l_command = l_sc.nextLine();
            String l_commandSplit[] = l_command.split(" ");
            if (l_commandSplit[0].equalsIgnoreCase("deploy")) {
                if (l_commandSplit.length == 3) {
                    String l_countryId = l_commandSplit[1];
                    if (d_country.COUNTRIESLIST.containsKey(l_countryId)) {
                        String l_regex = "\\d+";
                        if (l_commandSplit[2].matches(l_regex)) {
                            int l_armiesToPlace = Integer.parseInt(l_commandSplit[2]);
                            if (l_armiesToPlace > 0) {
                                l_flag_1 = false;
                                return new DeployOrder(l_countryId, l_armiesToPlace, d_country);
                            } else {
                                System.out.println("Negative Army count not allowed");
                                continue;
                            }
                        } else {
                            System.out.println("The army number should be an integer");
                            continue;
                        }
                    } else {
                        System.out.println("The country that you entered doesn't exist in the Map");
                        continue;
                    }
                } else {
                    System.out.println("Your deploy command should be like 'deploy countryName armyToPlace'\n");
                    continue;
                }
            } else if (l_commandSplit[0].equalsIgnoreCase("bomb")) {
                if (l_commandSplit.length == 2) {
                    String l_countryId = l_commandSplit[1];
                    if (d_country.COUNTRIESLIST.containsKey(l_countryId)) {
                        if (!l_p.d_owned.contains(d_country.COUNTRIESLIST.get(l_countryId))) {
                            l_flag_1 = false;
                            return new Cards(l_countryId, l_commandSplit[0], d_country);
                        } else {
                            System.out.println("Cannot Bomb its own country");
                            continue;
                        }
                    } else {
                        System.out.println("Not a Valid Country");
                        continue;
                    }
                } else {
                    System.out.println("Not a Valid Command");
                    continue;
                }
            } else if (l_commandSplit[0].equalsIgnoreCase("advance")) {
                if (l_commandSplit.length == 4) {
                    String l_country_from = l_commandSplit[1];
                    String l_country_to = l_commandSplit[2];
                    String l_armies = l_commandSplit[3];
                    String l_card = "empty";
                    int l_armiesToAdvance = Integer.parseInt(l_armies);
                    if (d_country.COUNTRIESLIST.containsKey(l_country_from) && d_country.COUNTRIESLIST.containsKey(l_country_to)) {
                        if (l_p.d_owned.contains(d_country.COUNTRIESLIST.get(l_country_from))) {
                            if (d_country.COUNTRIESLIST.get(l_country_from).d_neighbours.contains(l_country_to)) {
                                if (l_p.d_armiesNum >= l_armiesToAdvance) {
                                    l_flag_1 = false;
                                    return new AdvanceArmies(l_country_from, l_country_to , l_armiesToAdvance , l_card , d_country , d_playerList);
                                } else {
                                    System.out.println("No of armies should be less or equal to no. of armies that player owns");
                                    continue;
                                }
                            } else {
                                System.out.println("Cannot advance to " + l_country_to + " as it is not a neighbor of " + l_country_from);
                                continue;
                            }
                        } else {
                            System.out.println(l_country_from + " does not belong to " + l_p.d_name);
                            continue;
                        }
                    } else {
                        System.out.println(l_country_from + " or " + l_country_to + " are not a valid country");
                        continue;
                    }
                } else {
                    System.out.println("Incomplete command, Please enter the full command");
                    continue;
                }
            }else if (l_commandSplit[0].equalsIgnoreCase("blockade")) {
                if (l_commandSplit.length == 2) {
                    String l_countryId = l_commandSplit[1];
                    if (d_country.COUNTRIESLIST.containsKey(l_countryId)) {
                        if (l_p.d_owned.contains(d_country.COUNTRIESLIST.get(l_countryId))) {
                            l_flag_1 = false;
                            return new Cards(l_countryId,l_commandSplit[0] ,  d_country);
                        } else {
                            System.out.println("Cannot apply blockade to someone else 'l_s country");
                            continue;
                        }
                    } else {
                        System.out.println("Not a Valid Country");
                        continue;
                    }
                } else {
                    System.out.println("Not a Valid Command");
                    continue;
                }
            }else if(l_commandSplit[0].equalsIgnoreCase("airlift")){
                if (l_commandSplit.length == 4) {
                    String l_countryFrom = l_commandSplit[1];
                    String l_countryTo = l_commandSplit[2];
                    String l_armies = l_commandSplit[3];
                    int l_armiesToAirlift = Integer.parseInt(l_armies);
                    if (d_country.COUNTRIESLIST.containsKey(l_countryFrom) && d_country.COUNTRIESLIST.containsKey(l_countryTo)) {
                        if (l_p.d_owned.contains(d_country.COUNTRIESLIST.get(l_countryFrom))) {
                            if(l_p.d_armiesNum>=l_armiesToAirlift){
                                l_flag_1 = false;
                                return new Cards(l_countryFrom,l_countryTo ,l_armiesToAirlift ,l_commandSplit[0] ,d_country);
                            }
                            else {
                                System.out.println("Not enough number of armies to airlift");
                            }

                        } else {
                            System.out.println("Cannot airlift armies from someone else'l_s country");
                            continue;
                        }
                    } else {
                        System.out.println(l_countryFrom  + "" + "or " + l_countryTo +" Not a Valid Country");
                        continue;
                    }
                } else {
                    System.out.println("Not a Valid Command");
                    continue;
                }

            }
            else if(l_commandSplit[0].equalsIgnoreCase("negotiate")){
                if (l_commandSplit.length == 2) {
                    String l_playerID = l_commandSplit[1];
                    if (d_playerList.containsKey(l_playerID)) {
                        l_flag_1 = false;
                        return new Cards(l_playerID , l_commandSplit[0] ,d_playerList);
                    }
                    else {
                        System.out.println("This player does not exists");
                    }

                } else {
                    System.out.println("Not a valid command");
                    continue;
                }
            }
            else if (l_commandSplit[0].equalsIgnoreCase("pass")) {
                l_i++;
                l_flag_1 = false;
                return new PassOrder(l_p, d_country);

            } else if (l_commandSplit[0].equalsIgnoreCase("showmap")) {
                showMap l_map = new showMap(d_playerList, d_country);
                l_map.check();
                return null;
            }
            else if(l_commandSplit[0].equalsIgnoreCase("savegame"))
            {
                System.out.println("Inside this savegame statement");
                if(l_commandSplit.length==2){
                    if(!l_command.contains(".")){
                        String l_fileName = l_commandSplit[1];
                        l_fileName += ".ser";
//                        System.out.println(System.getProperty("user.dir"));
                        File l_file = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + l_fileName);
                        try {
                            FileOutputStream l_fileOutputStream = new FileOutputStream(l_file);
                            ObjectOutputStream l_outputStream = new ObjectOutputStream(l_fileOutputStream);
                            serialObj obj = new serialObj(d_playerList,d_country);
                            l_outputStream.writeObject(obj);
                            l_outputStream.writeObject(d_country.COUNTRIESLIST);

                            l_outputStream.close();
                            l_fileOutputStream.close();
                        } catch (Exception e) {
                            System.out.println("Exception e: "+e);
                        }
                    }
                    else
                    {
                        System.out.println("Write savemap command without file extension");
                    }
                }
                else
                {
                    System.out.println("Command should be like 'savegame filename' (Don't enter extension)");
                }
            }

            else {
                System.out.println("Wrong command. Re-enter the correct one");
                return null;
            }

        }
    return null;
   }
}
