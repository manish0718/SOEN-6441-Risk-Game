package org.Main;

import java.io.File;

/**
 * This is the adaptee class of the conquest map used to edit the map.
 */

public class EditDominationAdapter implements AdapterInterface {

    /**
     * This is the object of EditMapDomination class.
     */
    EditMap l_map;

    /**
     * This the constructor of this class.
     * @param p_editmap This is the map file which is to be edited.
     */
    public EditDominationAdapter(EditMap p_editmap) {
        this.l_map = p_editmap;
    }

    /**
     * This method is used to call the Edit map function which will edit the conquest map.
     * @param p_file This is the map file which will be passed to edit the map.
     * @throws Exception This the file not found exception.
     */
    public void editMap(String p_file) throws Exception {
        l_map.editMap(p_file);
    }

    /**
     * This method is used to call the map show function which will read the domination map.
     * @param p_file This is the map file which will be passed to print the map.
     * @throws Exception This is file not found exception.
     */
    @Override
    public void mapshow(File p_file) throws Exception {

    }

}