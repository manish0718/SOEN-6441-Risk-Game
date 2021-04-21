package org.StatePattern;

import org.GamePlay.Country;
import org.GamePlay.Player;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class serialObj implements Serializable {
    //implements Serializable
    private static final long serialVersionUID= 8553244259610679861L;
    public ConcurrentHashMap <String, Player> d_playerList;
    public Country d_country;
    public serialObj(ConcurrentHashMap<String,Player>p_playerList, Country p_Country)
    {
        this.d_country = p_Country;
        this.d_playerList = p_playerList;
    }
    public ConcurrentHashMap getd_playerList()
    {
        return d_playerList;
    }
    public Country getD_country()
    {
        return d_country;
    }
}
