package org.GamePlay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Country Class containing all data for one country
 */
public class Country implements Serializable {
    private static final long serialVersionUID= -1204069388230272497L;
    /**
     * GLobal List of all countries
     */
    public static ConcurrentHashMap<String, Country> COUNTRIESLIST = new ConcurrentHashMap<>();

    int d_numberOfCountriesInContinent;
    public String d_countryId;
    public Integer d_numOfArmiesPlaced = 0;
    public ArrayList<String> d_neighbours = new ArrayList();
    public String d_continent;
    public String d_owner;
    public int d_continentBonus;

    /**
     * Constructor to intialize the country object
     *
     * @param p_countryId                    Country Name
     * @param p_continent                    Continent Name
     * @param p_continentBonus               Continent Control Value
     * @param p_numberOfCountriesInContinent number Of Countries In Continent
     * @param p_neighbours                   list of neighbouring countries
     */
    public Country(String p_countryId, String p_continent, int p_continentBonus, int p_numberOfCountriesInContinent, ArrayList p_neighbours) {
        this.d_countryId = p_countryId;
        this.d_continent = p_continent;
        this.d_continentBonus = p_continentBonus;
        this.d_numberOfCountriesInContinent = p_numberOfCountriesInContinent;
        this.d_neighbours = p_neighbours;
    }
    
}
