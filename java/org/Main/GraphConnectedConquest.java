package org.Main;

import java.io.File;
import java.util.*;
import java.util.HashMap;

/**
 * This class checks whether the given .map file is CONNECTED or NOT.
 */

public class GraphConnectedConquest{
    static File d_FILE;
    MapTableConquest l_borders = new MapTableConquest();
    HashMap<Integer,ArrayList> l_countryNeigh;
    List<Integer> l_realNodes;
    List<Integer> l_country_index;
    Stack<Integer> l_stack;
    ArrayList<Integer> l_connections;
    int l_max;
    boolean[] l_visited;
    boolean[] l_scan;

    /**
     * Simple Constructor throwing exception.
     * @param p_file This is the map file.
     * @throws Exception whether the file exists or not.
     */
    public GraphConnectedConquest(File p_file) throws Exception {
        this.d_FILE = p_file;
        l_countryNeigh = l_borders.CountryAndIDConquest(d_FILE);
        l_country_index = l_borders.maxConquest(d_FILE);
        l_realNodes = l_borders.maxConquest(d_FILE);
        l_stack = new Stack<>();
        l_connections = new ArrayList<>();
        l_max = Collections.max(l_realNodes,null);
        l_visited = new boolean[l_max +1];
        l_scan = new boolean[l_max + 1];

    }

    /**
     * This function is modifying the Hashmap of the .map file values, to its relevant form.
     * @return Hashmap which has each country unique id and in its values is..
     * Arraylist of neighbouring countries unique id's.
     */
    public HashMap<Integer,ArrayList> complete(){
        ArrayList<Integer> l_empty = new ArrayList<>();

        l_scan[0] = true;
        for (int l_i = 0; l_i< l_country_index.size(); l_i++){
            l_scan[l_country_index.get(l_i)]= true;
        }
        for (int l_j = 0; l_j< l_realNodes.size(); l_j++){
            if(l_scan[l_realNodes.get(l_j)]==false){
                int l_note = l_realNodes.get(l_j);
                l_countryNeigh.put(l_note,l_empty);
            }
        }

        return l_countryNeigh;

    }



    /**
     * This method implements Depth First Search Algorithm in order to traverse the given graph.
     * And checks whether all the nodes are visited or not.
     * @param p_value variable p_value is integer value which determines from where the graph traversing should start.
     */
    public void dfs(int p_value) {
        l_stack.push(p_value);
        l_visited[p_value] = true;
        complete();
        int l_count = 0;
        while (true) {
            if(l_stack.isEmpty() == true){
                break;
            }
            Integer l_node = l_stack.pop();
            l_connections = l_countryNeigh.get(l_node);
            if(!l_connections.isEmpty()){
                for (Integer l_neighbour : l_connections) {
                    if (!l_visited[l_neighbour]) {
                        l_stack.push(l_neighbour);
                        l_visited[l_neighbour] = true;
                    }
                }
            }
            l_count++;
        }
    }

    /**
     * This method finally checks the visited boolean array and sees if any value is false in it.
     * If yes that means our graph is not connected and if no our graph is connected.
     * @return boolean variable boolean which is simple true or false.
     */
    public boolean ifGraphConnected() {
        int l_key_1 = l_countryNeigh.keySet().stream().findFirst().get();
        int p_start = l_key_1;
        dfs(p_start);
        l_visited[0] = true;
        for (int l_i = 0; l_i < l_realNodes.size(); l_i++) {
            int l_check = l_realNodes.get(l_i);
            boolean l_comp = l_visited[l_check];
            if(l_comp == false){
                return false;
            }
        }
        return true;
    }


    /**
     * This function checks if each country is a connected sub graph.
     * @return boolean whether the individual continent is connected or not.
     * @throws Exception as we are using ConnectedContinent class Functions
     */
    public boolean ContinentsCheck() throws Exception {
        ContinentsConnectedConquest d_obj = new ContinentsConnectedConquest(d_FILE);
        HashMap<Integer,ArrayList> l_list = d_obj.ifContinentCountriesConnected();
        for(Map.Entry<Integer, ArrayList> l_entry: l_list.entrySet()) {
            HashMap<Integer,ArrayList> l_check = l_borders.CountryIDConquest(d_FILE,l_entry.getValue());
            List<Integer> l_nodes = l_entry.getValue();
            int l_max = Collections.max(l_nodes);
            boolean [] l_traversed = new boolean[l_max +1];
            int l_key_1 = l_check.keySet().stream().findFirst().get();
            int p_start = l_key_1;
            l_stack.push(p_start);
            l_traversed[p_start] = true;
            int l_count = 0;
            while (true) {
                if (l_stack.isEmpty() == true) {
                    break;
                }
                Integer l_node = l_stack.pop();
                l_connections = l_check.get(l_node);
                if (!l_connections.isEmpty()) {
                    for (Integer l_neighbour : l_connections) {
                        if (!l_traversed[l_neighbour]) {
                            l_stack.push(l_neighbour);
                            l_traversed[l_neighbour] = true;
                        }
                    }
                }
                l_count++;
            }

            l_traversed[0] = true;
            for (int l_i = 0; l_i < l_nodes.size(); l_i++) {
                int l_see = l_nodes.get(l_i);
                boolean l_comp = l_traversed[l_see];
                if(l_comp == false){
                    return false;
                }
            }

        }

        return true;
    }
}

