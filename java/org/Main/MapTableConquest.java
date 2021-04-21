package org.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used for reading the map and saving it to the variables which will be required while playing the game.
 */
public class MapTableConquest {

    /**
     * Method which will read the .map file after the borders and will pick the country's unique ID only and will store it in the list.
     * @param p_file is the Object of the File.
     * @return the List which contains the unique id of all the countries mentioned after [borders] line in the map file.
     * @throws Exception as it is using the countryandborderline method from the ReadLines.java file.
     */
    public List maxConquest(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.territoryline(p_file);
        List<Integer> l_check = new ArrayList<>();
        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=1;l_i<l_n;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    l_check.add(l_i);
                }
                l_count = 1 ;
            }
        }
        return l_check;
    }

    /**
     * Method which will read the .map file after the borders and will pick the country's unique ID which is at the first place in each line.
     * @param p_file is the Object of the File.
     * @return the List which contains the unique id of all the countries mentioned after [borders] line in the map file.
     * @throws Exception as it is using the countryandborderline method from the ReadLines.java file.
     */
    public List listConquest(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.territoryline(p_file);
        List<Integer> l_check = new ArrayList<>();
        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=1;l_i<l_n;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    l_check.add(l_i);
                }
                l_count = 1 ;
            }
        }
        return l_check;
    }

    /**
     * Function which will look specifically for the [Territories] string and then from that line onwards, it will split the string on space and will select the
     * the country's unique key and country's name and will place it in HashMap at their respective position.
     * @param p_file which contains the file name which has to be read.
     * @return HashMap of Integer as a key and String as a value.
     * @throws Exception This is the file not found exception.
     */
    public HashMap<String, Integer> listCountryIdConquest(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.territoryline(p_file);
        List<Integer> l_check = new ArrayList<>();
        HashMap<String,Integer> id = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    id.put(l_input[0],l_i+1);
                }
                l_count = 1 ;
            }
        }
        return id;
    }

    /**
     * This method creates a hashmap which contains the country's unique key as key and country's name as the value of hashmap.
     * @param p_file which contains the file name which has to be read.
     * @return HashMap of Integer as a value and String as a String.
     * @throws Exception This is the file not found exception.
     */
    public HashMap<Integer,String> listCountryIdConquests(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.territoryline(p_file);
        List<Integer> l_check = new ArrayList<>();
        HashMap<Integer,String> l_id = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    l_id.put(l_i+1,l_input[0]);
                }
                l_count = 1 ;
            }
        }
        return l_id;
    }
    /**
     * This method will get the continents unique id and stores them in arraylist.
     * @param p_file is the filename which needs to be read in order to fetch these details.
     * @return Arraylist of continents unique id.
     * @throws Exception continentline is being used which is throwing exception and thus it needs to be handled.
     */
    public ArrayList<Integer> continentsKeyConquest(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.continentsline(p_file);

        ArrayList<Integer> l_contId = new ArrayList<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Continents]")){
                for(l_i=1;l_i<l_n;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    int l_val = l_i;
                    l_contId.add(l_val);

                }
                l_count = 1 ;
            }
        }
        return l_contId;
    }

    /**
     * This method will particularly look for the [Continents] and their continent unique value and put them in a hashmap..
     * @param p_file contains the name of the file that has to be read.
     * @return it return the hashmap in which key is continent name and value is the continent's unique key.
     * @throws Exception This is the file not found exception.
     */

    public HashMap<String,Integer> continentsAndKeyConquest(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.continentsline(p_file);

        HashMap<String , Integer> l_contId = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Continents]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    int l_val = l_i;
                    String[] l_input = l_text.split("=");
                    l_contId.put(l_input[0],l_i+1);

                }
                l_count = 1 ;
            }
        }
        return l_contId;
    }

    /**
     * This method will particularly look for the [countries]  continent unique value and put them in a arraylist..
     * for each country.
     * @param p_file contains the name of the file that has to be read.
     * @return Arraylist which contains each countries continent.
     * @throws Exception as countryandborderline is being used by this method which throws the exception and it needs to be handled.
     */

    public ArrayList<Integer> CountriesContinentConquest(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.territoryline(p_file);
        ArrayList<Integer> l_cont = new ArrayList<>();
        HashMap<String,Integer> l_pair = new HashMap<>();
        l_pair = continentsAndKeyConquest(p_file);

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    int l_continent = l_pair.get(l_input[3]);

                    l_cont.add(l_continent);

                }
                l_count = 1 ;
            }
        }
        ArrayList<Integer> l_newList = new ArrayList<>();

        for (Integer l_element : l_cont) {

            if (!l_newList.contains(l_element)) {

                l_newList.add(l_element);
            }
        }

        return l_newList;
    }


    /**
     * Method which will read the .map file after the countries and will generate the hashmap pf countries and its continents
     * @param p_file is the Object of the File.
     * @return the hashmap whose key is country and value is continent.
     * @throws Exception as it is using the countryandborderline method from the ReadLines.java file.
     */
    public HashMap<Integer, Integer> contConquest  (File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.territoryline(p_file);
        HashMap<Integer,Integer> l_connect = new HashMap<>();
        HashMap<String,Integer> l_pair = new HashMap<>();

        l_pair = continentsAndKeyConquest(p_file);

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    int l_country = l_i + 1;
                    int l_continent = l_pair.get(l_input[3]);
                    l_connect.put(l_country,l_continent);

                }
                l_count = 1 ;
            }
        }
        return l_connect;
    }



    /**
     * Method which will keep a track of the particular country and its neighbouring countries. The country's Unique ID will be used as the key
     * and the unique Id of the neighbouring countries are stored in the ArrayList.
     * @param p_file is the Object of the p_file which has to be read.
     * @return the HashMap containing the Integer as the Key and the ArrayList as the value.
     * @throws Exception because it is using the countryandborderline method which is throwing the exception
     */
    public HashMap<Integer,ArrayList> CountryAndIDConquest(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_num = l_line.territoryline(p_file);

        HashMap<String,Integer> l_check = new HashMap<>();
        l_check = listCountryIdConquest(p_file);

        HashMap<Integer,ArrayList> l_cont_and_neigh = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){

            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){

                for(l_i=0;l_i<l_num;l_i++){

                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    ArrayList<Integer> neigh = new ArrayList<>();
                    for(int l_k = 4 ; l_k<l_input.length ; l_k++){
                        int l_j = l_check.get(l_input[l_k]);
                        neigh.add(l_j);

                    }
                    l_cont_and_neigh.put(l_i+1,neigh);

                }
                l_count = 1 ;

            }


        }

        return l_cont_and_neigh;
    }

    /**
     * Method which will keep a track of the particular country and its neighbouring countries. The country's Unique ID will be used as the key
     * and the unique Id of the neighbouring countries are stored in the ArrayList.
     * @param p_file is the Object of the p_file which has to be read.
     * @param p_list It is the arraylist which contains the list of countries.
     * @return the HashMap containing the Integer as the Key and the ArrayList as the value.
     * @throws Exception because it is using the countryandborderline method which is throwing the exception
     */
    public HashMap<Integer,ArrayList> CountryIDConquest(File p_file,ArrayList<Integer> p_list) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_num = l_line.territoryline(p_file);
        ArrayList<Integer> l_temp =new ArrayList<>();

        HashMap<Integer,ArrayList> l_comp = new HashMap<>();
        l_comp = CountryAndIDConquest(p_file);

        HashMap<String,Integer> l_check = new HashMap<>();
        l_check = listCountryIdConquest(p_file);
        ArrayList<Integer> countries = p_list;


        HashMap<Integer,ArrayList> cont_and_neigh = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){

            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){

                for(l_i=0;l_i<l_num;l_i++){

                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    ArrayList<Integer> l_neigh = new ArrayList<>();

                    String l_see = l_input[0];
                    for(int l_o =0 ; l_o<countries.size() ; l_o++){
                        ArrayList<Integer> l_addCountry =new ArrayList<>();
                        if(countries.contains(l_check.get(l_see))){
                            for(int l_j=4;l_j<l_input.length;l_j++) {
                                for (int l_k = 0; l_k < countries.size(); l_k++) {
                                    if (l_check.get(l_input[l_j]) == countries.get(l_k)) {

                                        l_addCountry.add(l_check.get(l_input[l_j]));
                                    }
                                }
                            }
                        }
                        if(countries.contains(l_check.get(l_see))){
                            int l_val = l_check.get(l_see);
                            cont_and_neigh.put(l_val,l_addCountry);
                        }

                    }
                }
                l_count = 1 ;
            }
        }
        return cont_and_neigh;
    }

    /**
     * This method is used to create the list of all the countries available in that map. This method will first look for the [Territories]
     * string inside the map and the will read the map line by line and will append the country's name into the arraylist.
     * @param p_file is the Object of the File.
     * @return the String which contains the list of all countries.
     * @throws Exception as it is using the territoryline method which is basically throwing the exception.
     */
    public ArrayList<String> ConquestterritoriesList(File p_file) throws Exception {

        ReadLines l_line = new ReadLines();
        int l_n =l_line.territoryline(p_file);

        ArrayList<String> l_list = new ArrayList<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count = 0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");

                    l_list.add(l_input[0]);
                }
                l_count=1 ;
            }
        }
        return l_list;
    }

    /**
     * This method is used to create the list of all the continents available in that map. This method will first look for the [Continents]
     * string inside the map and the will read the map line by line and will append the continent's name into the arraylist.
     * @param p_file is the Object of the File.
     * @return the String which contains the list of all continents.
     * @throws Exception This is the file not found exception.
     */
    public  ArrayList<String> ConquestcontinentsList(File p_file) throws Exception {

        ReadLines l_line = new ReadLines();
        int l_num = l_line.continentsline(p_file);

        ArrayList<String> l_list1 = new ArrayList<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count = 0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Continents]")){
                for(l_i=0;l_i<l_num-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split("=");
                    l_list1.add(l_input[0]);
                }
                l_count=1 ;
            }
        }
        return l_list1;
    }

    /**
     * Method which will read the certain file and will look only for the [Continents] string in the map file. Then it will split the string on space and will select the
     * 0th index as the continent name and 1st index as the Continent control value and will place it in HashMap at their respective position
     * @param p_file is the filename which needs to be read in order to fetch these details.
     * @return HashMap of string and Integer whose key will contain the continent name and value will contain the control value of that particular continent.
     * @throws Exception continentline is being used which is throwing exception and thus it needs to be handled.
     */
    public HashMap<String,Integer> Conquestcontinentsandvalue(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.continentsline(p_file);

        HashMap<String,Integer> l_contval = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Continents]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split("=");
                    int l_val = Integer.parseInt(l_input[1]);
                    l_contval.put(l_input[0],l_val);
                }
                l_count = 1 ;
            }
        }
        return l_contval;
    }

    /**
     * This method will particularly look for the [Territories] string in the map file and then from the continent unique value, continent's name will be mapped
     * using the continentList method.
     * @param p_file contains the name of the file that has to be read.
     * @return HashMap which contains the continent's name as the value and the country's name as the key.
     * @throws Exception as territoryline is being used by this method which throws the exception and it needs to be handled.
     */
    public HashMap<String,String> Conquestcountryanditscontinent(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.territoryline(p_file);
        ArrayList<String> l_continent = ConquestcontinentsList(p_file);
        HashMap<String,String> l_countrycont = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    l_countrycont.put(l_input[0],l_input[3]);
                }
                l_count = 1 ;
            }
        }
        return l_countrycont;
    }

    /**
     * Method which will keep a track of the particular country and its neighbouring countries. The country's name will be used as the key
     * and the name of the neighbouring countries are stored in the ArrayList.
     * @param p_file is the Object of the file which has to be read.
     * @return the HashMap containing the Integer as the Key and the ArrayList as the value.
     * @throws Exception it is the file not found exception.
     */
    public HashMap<String, ArrayList> Conquestcountryanditsneighbours(File p_file) throws Exception {

        ReadLines l_line = new ReadLines();
        HashMap<String,ArrayList> l_countryNeigh= new HashMap<>();
        Scanner l_sc = new Scanner(p_file);
        int l_n = l_line.territoryline(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[Territories]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    ArrayList<String> neigh = new ArrayList<>();
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(",");
                    for(int i = 4; i < l_input.length; i++){
                        neigh.add(l_input[i]);
                    }
                    l_countryNeigh.put(l_input[0],neigh);
                }
                l_count = 1 ;
            }
        }
        return l_countryNeigh;
    }

    /**
     * Method which will keep a track of the particular country and its neighbouring countries. The country's Unique ID will be used as the key
     * and the unique Id of the neighbouring countries are stored in the ArrayList.
     * @param p_file is the Object of the p_file which has to be read.
     * @param p_list is the p_list of countries for particular continent.
     * @return the HashMap containing the Integer as the Key and the ArrayList as the value.
     * @throws Exception because it is using the countryandborderline method which is throwing the exception
     */
    public HashMap<Integer,ArrayList> CountryNeighbours(File p_file, ArrayList<Integer> p_list) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_num = l_line.countryandborderline(p_file);
        ArrayList<Integer> countries = p_list;

        HashMap<Integer,ArrayList> l_intNeigh = new HashMap<>();
        List<Integer> l_check = new ArrayList<>();
        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[borders]")){
                for(l_i=0;l_i<l_num;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_see = Integer.parseInt(l_input[0]);


                    for(int l_o =0 ; l_o<countries.size() ; l_o++){
                        ArrayList<Integer> l_addCountry =new ArrayList<>();
                        if(countries.contains(l_see)){
                            for(int l_j=0;l_j<l_input.length;l_j++) {
                                for (int l_k = 0; l_k < countries.size(); l_k++) {
                                    if (Integer.parseInt(l_input[l_j]) == countries.get(l_k)) {

                                        if (l_input.length == 1) {
                                            l_check.add(Integer.parseInt(l_input[l_j]));
                                        } else if (l_j == 0) {
                                            l_check.add(Integer.parseInt((l_input[l_j])));
                                        } else {
                                            l_addCountry.add(Integer.parseInt(l_input[l_j]));

                                        }

                                    }
                                }
                            }
                            int l_val = l_check.get(0);
                            l_check.remove(0);
                            l_intNeigh.put(l_val,l_addCountry);

                        }

                    }

                }
                l_count = 1 ;
            }

        }
        return l_intNeigh;
    }

}
