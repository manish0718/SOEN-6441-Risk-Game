package org.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to read the map file. This class will be used directly or indirectly in order to read from the MAP files
 */
public class ReadMap {
    /**
     * This method is used to find line count for the particular file. This method is used by the Readline.java file to
     * count the number of lines for different sections.
     * @param p_file is the name of the file to be read.
     * @return Integer variable count which tells the total number of lines being occupied.
     * @throws FileNotFoundException if the compiler is not able to find the file specified in the parameter.
     */
    public int totallines(File p_file) throws FileNotFoundException {
        Scanner l_sc1 = new Scanner(p_file);
        int l_count = 0;
        while (l_sc1.hasNextLine()) {
            l_count++;
            l_sc1.nextLine();
        }
        return l_count;
    }

    /**
     * This method is used to find the line number of the string [countries]. Let's say the [countries] string is available at line number
     * 25. Then this method will return the line number as 25.
     * @param p_file is the name of the file to be read.
     * @return Integer variable which is telling about the line number for string [countries] in the map file.
     * @throws FileNotFoundException if the file doesn't exist.
     */
    public int countrylines(File p_file) throws FileNotFoundException{
        int l_a = 0;
        Scanner l_sc2 = new Scanner(p_file);
        while (l_sc2.hasNextLine()){
            l_a++;
            l_sc2.nextLine();
            if(l_sc2.next().equals("[countries]")){
                break;
            }
        }
        return l_a;
    }

    /**
     * This method is used to find the line count of the string [continents]. Then this count is used to find the
     * names of different continents once we have the line number from where the continents are starting.
     * @param p_file is the name of the file to be read.
     * @return l_b is basically the count .
     * @throws Exception if the file specified doesn't exist.
     */
    public int continentlines(File p_file) throws Exception{
        int l_b = 0;
        Scanner l_sc3 = new Scanner(p_file);
        while (l_sc3.hasNextLine()){
            l_b++;
            l_sc3.nextLine();
            if(l_sc3.next().equals("[continents]")){
                break;
            }
        }
        return l_b;
    }

    /**
     * This method is used to find the [borders] string and then return the line number from where the country's Unique key
     * and its neighbours can be extracted once we know the line number from where it is starting.
     * @param p_file is the name of the file to be read.
     * @return l_c variable which tells the line count.
     * @throws Exception if the file specified doesn't exist.
     */
    public int borderlines(File p_file) throws Exception{
        int l_c = 0;

        Scanner l_sc4 = new Scanner(p_file);
        while (l_sc4.hasNextLine()){
            l_c++;
            l_sc4.nextLine();
            if(l_sc4.next().equals("[borders]")){
                break;
            }
        }
        return l_c;
    }

    /**
     * This method is used to find the line number of the string [Territories]. Let's say the [Territories] string is available at line number
     * 25. Then this method will return the line number as 25.
     * @param p_file is the name of the file to be read.
     * @return Integer variable which is telling about the line number for string [countries] in the map file.
     * @throws FileNotFoundException if the file doesn't exist.
     */
    public int territorylines(File p_file) throws Exception{
        int l_c = 0;

        Scanner l_sc4 = new Scanner(p_file);
        while (l_sc4.hasNextLine()){
            l_c++;
            l_sc4.nextLine();
            if(l_sc4.next().equals("[Territories]")){
                break;
            }
        }
        return l_c;
    }

    /**
     * This method is used to find the line count of the string [Continents]. Then this count is used to find the
     * names of different continents once we have the line number from where the continents are starting.
     * @param p_file is the name of the file to be read.
     * @return l_b is basically the count .
     * @throws Exception if the file specified doesn't exist.
     */
    public int Conquestcontinentlines(File p_file) throws Exception{
        int l_b = 0;
        Scanner l_sc3 = new Scanner(p_file);
        while (l_sc3.hasNextLine()){
            l_b++;
            l_sc3.nextLine();
            if(l_sc3.next().equals("[Continents]")){
                break;
            }
        }
        return l_b;
    }

}
