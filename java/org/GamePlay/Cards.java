package org.GamePlay;

import org.ObserverBasedLogging.LogEntryBuffer;
import org.ObserverBasedLogging.LogFile;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Child class extending the Order Class , it deals with various cards order command
 */
public class Cards extends Order{
    Country d_country;
    String d_CountryID;
    String d_CountryFrom;
    String d_CountryTo;
    Integer d_armiesToAirlift;
    AdvanceArmies d_obj;
    String d_negotiate;
    static ConcurrentHashMap<String, Player> PLAYERS_LIST = new ConcurrentHashMap<>();
    String d_card;

    /**
     * Constructor to initialise corresponding card objects
     * @param p_CountryID Country on which the card will be applied
     * @param p_card Which card is used by player
     * @param p_cou Country class object
     */
    public Cards(String p_CountryID, String p_card, Country p_cou ){

        this.d_country = p_cou;
        this.d_card = p_card;
        this.d_CountryID = p_CountryID;

    }

    /**
     * Constructor to initialise corresponding card objects
     * @param p_CountryFrom Move armies from this country
     * @param p_CountryTo Move armies to this country
     * @param p_armiesToAirlift No of Armies to move
     * @param p_card Which card is used by player
     * @param p_cou Country class object
     */
    public Cards(String p_CountryFrom , String p_CountryTo, Integer p_armiesToAirlift, String p_card, Country p_cou ){

        this.d_CountryFrom = p_CountryFrom;
        this.d_CountryTo = p_CountryTo;
        this.d_armiesToAirlift = p_armiesToAirlift;
        this.d_card = p_card;
        this.d_country = p_cou;
    }

    /**
     * Constructor to initialise corresponding card objects
     * @param p_CountryID Country on which the card will be applied
     * @param p_card Which card is used by player
     * @param p_playerList The list of players currently playing the game
     */
    public Cards(String p_CountryID, String p_card, ConcurrentHashMap<String, Player> p_playerList){

        this.d_negotiate = p_CountryID;
        this.d_card = p_card;
        this.PLAYERS_LIST = p_playerList;
    }

    /**
     * Implementation of Execute method.
     * In this switch case is implemented for the right card the corresponding card method will be executed.
     * @param p_p Player
     */
    @Override
    void Execute(Player p_p) {

        LogEntryBuffer l_observable = new LogEntryBuffer();
        LogFile l_observer = new LogFile();
        l_observable.addObserver(l_observer);
        String l_message ="";


        String l_decide= "No";
        switch (d_card) {
            case "bomb":
                if (p_p.d_cards.contains("BOMB") && !p_p.d_owned.contains(d_country.COUNTRIESLIST.get(d_CountryID))) {
                    if (!p_p.d_negotiate.contains(d_country.COUNTRIESLIST.get(d_CountryID).d_owner)) {
                        for (int l_i = 0; l_i <= p_p.d_owned.size(); l_i++) {
                            if (d_country.COUNTRIESLIST.get(p_p.d_owned.get(l_i).d_countryId).d_neighbours.contains(d_CountryID)) {
                                l_decide = "YES";
                                if(l_decide.equalsIgnoreCase("YES")){
                                    int l_temp = d_country.COUNTRIESLIST.get(d_CountryID).d_numOfArmiesPlaced;
                                    d_country.COUNTRIESLIST.get(d_CountryID).d_numOfArmiesPlaced = (int) Math.floor(d_country.COUNTRIESLIST.get(d_CountryID).d_numOfArmiesPlaced / 2);
                                    l_observable.setMsg(p_p.d_name+" BOMB has reduced the army count of the "+ d_CountryID +"from "+l_temp+" to "+d_country.COUNTRIESLIST.get(d_CountryID).d_numOfArmiesPlaced);
                                    int l_indexCardName=-1;
                                    for(String l_cardName: p_p.d_cards)
                                    {
                                        l_indexCardName+=1;
                                        if(l_cardName.equalsIgnoreCase("BOMB"))
                                        {
                                            p_p.d_cards.remove(l_indexCardName);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        l_message="Cannot bomb this country as you have negotiated with the owner";
                        l_observable.setMsg(p_p+" "+l_message);
                        System.out.println(l_message);
                    }
                }
                else {
                    l_message = "You don't have the BOMB card";
                    l_observable.setMsg(p_p.d_name+" "+l_message);
                    System.out.println(l_message);
                }
                break;
            case "blockade":
                if(p_p.d_cards.contains("BLOCKADE") && p_p.d_owned.contains(d_country.COUNTRIESLIST.get(d_CountryID))) {
                    d_country.COUNTRIESLIST.get(d_CountryID).d_numOfArmiesPlaced *= 3;
                    p_p.d_owned.remove(d_country.COUNTRIESLIST.get(d_CountryID));
                    d_country.COUNTRIESLIST.get(d_CountryID).d_owner = "Neutral";
                    l_message = d_CountryID + " has been neutralized , now nobody owns this country";
                    System.out.println(l_message);
                    l_observable.setMsg(l_message);
                    int l_indexCardName=-1;
                    for(String l_cardName: p_p.d_cards)
                    {
                        l_indexCardName+=1;
                        if(l_cardName.equalsIgnoreCase("BLOCKADE"))
                        {
                            p_p.d_cards.remove(l_indexCardName);
                            break;
                        }
                    }
                }
                else{
                    l_message = "You don't have the BLOCKADE card.";
                    System.out.println(l_message);
                    l_observable.setMsg(p_p+" "+l_message);
                }
                break;
            case "airlift":
                if(p_p.d_cards.contains("AIRLIFT")) {
                    if (!p_p.d_negotiate.contains(d_country.COUNTRIESLIST.get(d_CountryTo).d_owner)) {
                        if (d_country.COUNTRIESLIST.get(d_CountryFrom).d_numOfArmiesPlaced >= d_armiesToAirlift) {
                            d_obj = new AdvanceArmies(d_CountryFrom, d_CountryTo, d_armiesToAirlift, d_country, d_card);
                            d_obj.Execute(p_p);
                            int l_indexCardName=-1;
                            for(String l_cardName: p_p.d_cards)
                            {
                                l_indexCardName+=1;
                                if(l_cardName.equalsIgnoreCase("AIRLIFT"))
                                {
                                    p_p.d_cards.remove(l_indexCardName);
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        l_message = "Cannot airlift to this country as you have negotiated with its owner";
                        System.out.println(l_message);
                        l_observable.setMsg(p_p.d_name+" "+l_message);
                    }
                }
                else {
                    l_message = "You don't have the AIRLIFT card";
                    System.out.println(l_message);
                    l_observable.setMsg(p_p.d_name+" "+l_message);
                }
                break;
            case "negotiate":
                if (p_p.d_cards.contains("NEGOTIATE")) {
                    p_p.add_negotiator(d_negotiate);
                    PLAYERS_LIST.get(d_negotiate).add_negotiator(p_p.d_name);
                    l_message = p_p.d_name + " successfully negotiated with " + d_negotiate + " and attack cannot happen between these two countries";
                    System.out.println(l_message);
                    l_observable.setMsg(l_message);
                    int l_indexCardName=-1;
                    for(String l_cardName: p_p.d_cards)
                    {
                        l_indexCardName+=1;
                        if(l_cardName.equalsIgnoreCase("negotiate"))
                        {
                            p_p.d_cards.remove(l_indexCardName);
                            break;
                        }
                    }
                }
                else {
                    l_message = "You don't have the NEGOTIATE card";
                    System.out.println(l_message);
                }
                break;
            default:
                l_message = "No Card Executed as you don't own the card";
                System.out.println(l_message);
                l_observable.setMsg(p_p.d_name+" "+l_message);
        }
    }
}
