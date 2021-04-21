package org.Main;

import java.io.File;

/**
 * This class is adaptee class of the domination map.
 */
public class DominationAdapter implements AdapterInterface{

    /**
     * This is the object of ShowMap class.
     */
    ShowMap l_map;
    /**
     * This is the constructor of this class.
     * @param p_map This is the map file which is to be printed.
     */
    public DominationAdapter(ShowMap p_map){
        this.l_map = p_map;
    }

    /**
     * This method is used to call the map show function which will read the domination map.
     * @param p_file This is the map file which will be passed to print the map.
     * @throws Exception This is file not found exception.
     */
    public void mapshow(File p_file) throws Exception{
        l_map.mapshow(p_file);
    }

    /**
     *  This method is used to call the map show function which will read the conquest map.
     * @param p_file This is the map file which will be passed to edit the map.
     * @throws Exception This is file not found exception.
     */
    @Override
    public void editMap(String p_file) throws Exception {

    }


}
