package org.Main;

import java.io.File;

/**
 * This is the interface of the adapter pattern.
 */

public interface AdapterInterface {
    /**
     * This is the method of showing map.
     * @param p_file This is the map file which will be passed to print the map.
     * @throws Exception This is file not found exception.
     */
    void mapshow(File p_file) throws Exception;

    /**
     * This is the method of editing the map.
     * @param p_file This is the map file which will be passed to edit the map.
     * @throws Exception This is file not found exception.
     */
    void editMap(String p_file) throws Exception;
}
