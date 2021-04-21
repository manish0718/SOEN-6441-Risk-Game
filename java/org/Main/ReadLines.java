package org.Main;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is used to count the number of lines between the map headings and then these line count is used
 * to make the variables for storing different things.
 */
public class ReadLines {

    /**
     * This method is used for counting the number of line between the string [countries] and the [borders] which will
     * eventually give the country names and its unique value.
     * @param p_file is the name of the map file to be read.
     * @return Integer variable l_countrylines which signifies the total number of lines for the country
     * @throws Exception as it is using the variable countrylines from the ReadMap.java files which throws the exception
     */
    public int countryandborderline(File p_file) throws Exception{
        ReadMap l_line = new ReadMap();
        int l_a = l_line.countrylines(p_file);
        int l_b = l_line.continentlines(p_file);
        int l_c = l_line.totallines(p_file);
        int l_d = l_line.borderlines(p_file);
        int l_countrylines = l_d-l_a;

        return l_countrylines;
    }

    /**
     * This method returns the total number of lines for the continents. Let's say continent index starts from line number 4
     * and goes upto line number 10. Therefore it will return the 10-4 = 6. This is used to extract the continent Name and its Control Value
     * @param p_file is the name of the map file to be read.
     * @return Integer variable l_continentlines which represents total number of lines for the continents.
     * @throws Exception as it is using the countrylines method from the ReadMap.java file which is throwing an exception.
     */
    public int continentline(File p_file) throws Exception {
        ReadMap l_line = new ReadMap();
        int l_a = l_line.countrylines(p_file);
        int l_b = l_line.continentlines(p_file);
        int l_c = l_line.totallines(p_file);
        int l_d = l_line.borderlines(p_file);
        int l_continentlines = l_a-l_b;

        return l_continentlines;
    }

    /**
     * This method returns the total number of lines for the continents. Let's say continent index starts from line number 4
     * and goes upto line number 10. Therefore it will return the 10-4 = 6. This is used to extract the continent Name and its Control Value
     * @param p_file is the name of the map file to be read.
     * @return Integer variable l_a which represents total number of lines for the continents.
     * @throws Exception This is the file not found exception.
     */
    public int continentsline(File p_file) throws Exception {
        ReadMap l_line = new ReadMap();
        int l_a = l_line.territorylines(p_file);
        int l_b = l_line.Conquestcontinentlines(p_file);
        int l_continentlines = l_a-l_b;

        return l_continentlines;
    }

    /**
     * This method is used for counting the number of line of [Territories].
     * @param p_file is the name of the map file to be read.
     * @return Integer variable l_countrylines which signifies the total number of lines for the country
     * @throws Exception as it is using the variable countrylines from the ReadMap.java files which throws the exception if file not found.
     */
    public int territoryline(File p_file) throws Exception{
        ReadMap l_line = new ReadMap();
        int l_a = l_line.territorylines(p_file);
        int l_c = l_line.totallines(p_file);
        int l_countrylines = l_c - l_a;

        return l_countrylines;
    }
}

