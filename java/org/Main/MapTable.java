package org.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * This class is used for reading the map and saving it to the variables which will be required while playing the game.
 */
public class MapTable {

    /**
     * This method is used to create the list of all the countries available in that map. This method will first look for the [countries]
     * string inside the map and the will read the map line by line and will append the country's name into the arraylist.
     * @param p_file This contains the name of the file to be read.
     * @return Arraylist named l_list containing the names of the all the countries.
     * @throws Exception as it is using the countryandborderline method which is basically throwing the exception.
     */
    public ArrayList<String> countryList(File p_file) throws Exception {

        ReadLines l_line = new ReadLines();
        int l_n =l_line.countryandborderline(p_file);

        ArrayList<String> l_list = new ArrayList<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count = 0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");

                    l_list.add(l_input[1]);
                }
                l_count=1 ;
            }
        }
        return l_list;
    }

    /**
     * Method which will read the particular map file and return the list containing all the continents. Firstly it will match for the [continents] string
     * inside the map file and then from the next line onwards, it will read the file line by line and split the string on space. And then selecting up the 0th index
     * will give us the continent name.
     * @param p_file is the parameter containing the filename to be read.
     * @return ArrayList containing all the names of the continent.
     * @throws Exception The exception thrown by the continentline method needs to be handled.
     */
    public ArrayList<String> continentList(File p_file) throws Exception {

        ReadLines l_line = new ReadLines();
        int l_num = l_line.continentline(p_file);

        ArrayList<String> l_list1 = new ArrayList<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count = 0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[continents]")){
                for(l_i=0;l_i<l_num-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    l_list1.add(l_input[0]);
                }
                l_count=1 ;
            }
        }
        return l_list1;
    }

    /**
     * Method which will read the certain file and will look only for the [continent] string in the map file. Then it will split the string on space and will select the
     * 0th index as the continent name and 1st index as the Continent control value and will place it in HashMap at their respective position
     * @param p_file is the filename which needs to be read in order to fetch these details.
     * @return HashMap of string and Integer whose key will contain the continent name and value will contain the control value of that particular continent.
     * @throws Exception continentline is being used which is throwing exception and thus it needs to be handled.
     */
    public HashMap<String,Integer> continentandvalue(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.continentline(p_file);

        HashMap<String,Integer> l_contval = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[continents]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_val = Integer.parseInt(l_input[1]);
                    l_contval.put(l_input[0],l_val);
                }
                l_count = 1 ;
            }
        }
        return l_contval;
    }

    /**
     * Function which will look specifically for the [countries] string and then from that line onwards, it will split the string on space and will select the 0th
     * index as the country's unique key and the 1st index as the country's name and will place it in HashMap at their respective position.
     * @param p_file which contains the file name which has to be read.
     * @return HashMap of Integer as a key and String as a value.
     * @throws Exception as countryandborderline is being used which throws exception and it needs to be handled.
     */
    public HashMap<Integer,String> countryanditskey(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);

        HashMap<Integer,String> l_countryval = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_val = Integer.parseInt(l_input[0]);
                    l_countryval.put(l_val,l_input[1]);
                }
                l_count = 1 ;
            }
        }
        return l_countryval;
    }

    /**
     * This method will particularly look for the [countries] string in the map file and then from the continent unique value, continent's name will be mapped
     * using the continentList method.
     * @param p_file contains the name of the file that has to be read.
     * @return HashMap which contains the continent's name as the value and the country's name as the key.
     * @throws Exception as countryandborderline is being used by this method which throws the exception and it needs to be handled.
     */

    public HashMap<String,String> countryanditscontinent(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);
        ArrayList<String> l_continent = continentList(p_file);
        HashMap<String,String> l_countrycont = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_val = Integer.parseInt(l_input[2]);
                    String l_x = l_continent.get(l_val-1);
                    l_countrycont.put(l_input[1],l_x);
                }
                l_count = 1 ;
            }
        }
        return l_countrycont;
    }

    /**
     * This method will firstly look for the [countries] in the map file and make the hashmap for the countries and its unique value.
     * Then it will precisely look for the [borders] in the map file and make the hashmap for the country and its adjacent/neighbouring countries.
     * It will add the "DNE" as the country name if one enters such neighbours that doesn't exists.
     * @param p_file will contains the name of the file to be read.
     * @return a Hashmap of String and ArrayList. Country will be the key of this hashmap and all the neighbouring countries will be available in form of the arraylist.
     * @throws Exception is being thrown as if the scanner can't open/find the file.
     */
    public HashMap<String, ArrayList> countryanditsneighbours(File p_file) throws Exception {

        HashMap<String,ArrayList> l_countryNeigh= new HashMap<>();
        HashMap <Integer,String> l_countryIndex= new HashMap<>();
        File l_file1 = new File(String.valueOf(p_file));
        Scanner l_sc = new Scanner(l_file1);
        while(l_sc.hasNextLine()) {
            String l_line = l_sc.nextLine();
            if(l_line.equalsIgnoreCase("[countries]"))
            {
                break;
            }
        }
        while(l_sc.hasNextLine())
        {
            String l_line = l_sc.nextLine();
            if(l_line.equals(""))
            {
                break;
            }
            l_countryIndex.put(Integer.parseInt(l_line.split(" ")[0]),l_line.split(" ")[1]);

        }
        System.out.println(l_countryIndex);
        while(l_sc.hasNextLine()) {
            String l_line = l_sc.nextLine();
            if(l_line.equalsIgnoreCase("[borders]"))
            {
                break;
            }
        }
        while(l_sc.hasNextLine())
        {
            String l_line= l_sc.nextLine();
            String l_name;
            String l_lineSplit[] = l_line.split(" ");
            ArrayList<String> l_nameOfNeighCountries = new ArrayList();
            for(int l_i=1;l_i<l_lineSplit.length;l_i++)
            {
                if(l_countryIndex.containsKey(Integer.parseInt(l_lineSplit[l_i])))
                {
                    l_name = l_countryIndex.get(Integer.parseInt(l_lineSplit[l_i]));
                    l_nameOfNeighCountries.add(l_name);
                }
                else
                {
                    l_nameOfNeighCountries.add("DNE");
                }
            }
            l_name = l_countryIndex.get(Integer.parseInt(l_lineSplit[0]));
            l_countryNeigh.put(l_name,l_nameOfNeighCountries);
        }
        return l_countryNeigh;
    }

    /**
     * This method will look for the countries in the map file and then will look for its continent's unique value and will place it in HashMap at their respective position.
     * @param p_file is map file that needs to be read.
     * @return HashMap whose key is Country's name and the value is the Unique value of the continent.
     * @throws Exception as this method is using the countryandborderline method that is throwing exception
     */
    public HashMap<String,Integer> countryanditsuniquecontinent(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);
        HashMap<String, Integer> l_countrycontkey = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_val = Integer.parseInt(l_input[2]);
                    l_countrycontkey.put(l_input[1],l_val);
                }
                l_count = 1 ;
            }
        }
        return l_countrycontkey;
    }

    /**
     * This method will look for the country in the map file and then creates a hashmap whose key will be country's name and the value would be the country's Unique key
     * @param p_file is the map file which we want to read.
     * @return a HashMap of String and Integer. Key of this hashmap is Country's unique ID and the value is the Country's Unique ID.
     * @throws Exception as it is using the countryandborderline method which is throwing the exception
     */
    public HashMap<String,Integer> uniqueKeyanditscountry(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);
        HashMap<String, Integer> l_countrykey = new HashMap<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_val = Integer.parseInt(l_input[0]);
                    l_countrykey.put(l_input[1],l_val);
                }
                l_count = 1 ;
            }
        }
        return l_countrykey;
    }


    /**
     * Method which will keep a track of the particular country and its neighbouring countries. The country's Unique ID will be used as the key
     * and the unique Id of the neighbouring countries are stored in the ArrayList. 
     * @param file is the Object of the file which has to be read.
     * @return the HashMap containing the Integer as the Key and the ArrayList as the value.
     * @throws Exception because it is using the countryandborderline method which is throwing the exception
     */
    public HashMap<Integer,ArrayList> CountryAndItsNeighbours(File file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_num = l_line.countryandborderline(file);
        HashMap<Integer,ArrayList> l_intNeigh = new HashMap<>();
        List<Integer> l_check = new ArrayList<>();
        Scanner l_sc = new Scanner(file);
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
                    List<Integer> l_list = new ArrayList<>();
                    int [] l_arr = new int [l_input.length];
                    ArrayList<Integer> l_brd = new ArrayList<>();


                    for(int l_j=0;l_j<l_input.length;l_j++) {

                        if(l_input.length==1){
                            l_list.add(Integer.parseInt(l_input[l_j]));
                            l_check.add(Integer.parseInt(l_input[l_j]));
                        }
                        else if(l_j==0){
                            l_list.add(Integer.parseInt(l_input[l_j]));
                            l_check.add(Integer.parseInt((l_input[l_j])));
                        }
                        else{
                            l_brd.add(Integer.parseInt(l_input[l_j]));
                        }
                    }
                    int l_val = l_list.get(0);
                    l_list.remove(0);
                    l_intNeigh.put(l_val,l_brd);
                }
                l_count = 1 ;
            }
        }
        return l_intNeigh;
    }

    /**
     * Method which will read the .map file after the borders and will pick the country's unique ID only and will store it in the list.
     * @param p_file is the Object of the File.
     * @return the List which contains the unique id of all the countries mentioned after [borders] line in the map file.
     * @throws Exception as it is using the countryandborderline method from the ReadLines.java file.
     */
    public List max(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);
        List<Integer> l_check = new ArrayList<>();
        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    l_check.add(Integer.parseInt(l_input[0]));
                }
                l_count = 1 ;
            }
        }
        return l_check;
    }


    /**
     * Method which will read the .map file after the borders and will pick the country's unique ID which is ath the first place in each line.
     * @param p_file is the Object of the File.
     * @return the List which contains the unique id of all the countries mentioned after [borders] line in the map file.
     * @throws Exception as it is using the countryandborderline method from the ReadLines.java file.
     */
    public List list(File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);
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
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    l_check.add(Integer.parseInt(l_input[0]));
                }
                l_count = 1 ;
            }
        }
        return l_check;
    }


    /**
     * This method will get the continents unique id and stores them in arraylist.
     * @param p_file is the filename which needs to be read in order to fetch these details.
     * @return Arraylist of continents unique id.
     * @throws Exception continentline is being used which is throwing exception and thus it needs to be handled.
     */
    public ArrayList<Integer> continentsKey(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.continentline(p_file);

        ArrayList<Integer> l_contId = new ArrayList<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[continents]")){
                for(l_i=1;l_i<l_n;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_val = l_i;
                    l_contId.add(l_val);

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

    public ArrayList<Integer> CountriesContinent(File p_file) throws Exception{

        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);
        ArrayList<Integer> l_cont = new ArrayList<>();

        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_continent = Integer.parseInt(l_input[2]);

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
    public HashMap<Integer, Integer> cont  (File p_file) throws Exception{
        ReadLines l_line = new ReadLines();
        int l_n = l_line.countryandborderline(p_file);
        HashMap<Integer,Integer> l_connect = new HashMap<>();
        Scanner l_sc = new Scanner(p_file);
        int l_count=0;
        int l_i;
        int l_a=0;
        while(l_sc.hasNextLine()){
            if(l_count==1){
                break;
            }
            if(l_sc.next().equals("[countries]")){
                for(l_i=0;l_i<l_n-1;l_i++){
                    if(!l_sc.hasNext()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    if(l_a == 0) {
                        l_text = l_sc.nextLine();
                        l_a=1;
                    }
                    String[] l_input = l_text.split(" ");
                    int l_country = Integer.parseInt(l_input[0]);
                    int l_continent = Integer.parseInt(l_input[2]);
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

}

