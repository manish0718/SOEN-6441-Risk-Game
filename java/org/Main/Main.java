package org.Main;

import java.io.File;
import java.util.*;

/***
 * This class is the Main file from where the game starts.
 */

public class Main {


    /**
     * FILE is the object which is used to store the path of the map.
     */

    public static File FILE;
    /**
     * SC is the object of scanner class used to get input from the user.
     */
    public static Scanner SC;

    /***
     * This method edit the map initially by asking the user to enter the file name.
     * If file doesn't exixts then it will ask the user to create a new map, else it will allow user to interact with the map and edit it.
     */
    public static void EditMap() {
        EditMap editmap = new EditMap();
        EditMapConquest editmaps = new EditMapConquest();
        int l_flag = 0;
        int l_flag9 = 0;
        while (true) {
            System.out.println("Enter edit filename command to edit map");
            SC = new Scanner(System.in);
            String l_filename = SC.nextLine();
            String[] l_text = l_filename.split(" ");
            if (l_filename.endsWith(".map")) {
                try {
                    if (l_text[0].equalsIgnoreCase("edit")) {
                        String l_file = null;
                        File l_directoryPath = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\");
                        String l_contents[] = l_directoryPath.list();
                        for (int l_i = 0; l_i < l_contents.length; l_i++) {
                            if (l_text[1].equals(l_contents[l_i])) {
                                l_file = l_text[1];
                                l_flag = 1;
                            }
                        }

                        if (l_flag == 0 || l_contents == null) {
                            System.out.println("File does not exits! Let's build a new map");
                            File l_file1 = new File(l_text[1]);
                            createMapByUser(l_file1);
                        }

                        if (l_flag == 1) {
                            File file = new File(l_directoryPath +"\\" +l_file);
                            Scanner l_sc3 = new Scanner(file);
                            while (l_sc3.hasNextLine()) {
                                String x =  l_sc3.next();
                                if (x.equals("[countries]")) {
                                    l_flag9 = 1;
                                    break;
                                }
                                if (x.equals("[Territories]")) {
                                    l_flag9 = 2;
                                    break;
                                }
                            }

                            if(l_flag9 == 1){
                                EditDominationAdapter edit = new EditDominationAdapter(editmap);
                                edit.editMap(l_file);
                            }

                            if(l_flag9 == 2){
                                EditConquestAdapter edits = new EditConquestAdapter(editmaps);
                                edits.editMap(l_file);
                            }
                        }
                        break;
                    } else {
                        System.out.println("Enter correct command\n");
                    }
                } catch (Exception p_e) {
                    p_e.printStackTrace();
                }
            } else {
                System.out.println("Enter the editmap command with .map extension");
                continue;
            }
        }
    }

    /**
     * This method is called if user want to edit the map and map doesn't exists.
     * This method allows user to create the map from the scratch.
     *
     * @param p_file1 This is the name of the map which user will create.
     * @throws Exception If file does not found at the path mentioned, this will throw exception.
     */

    public static void createMapByUser(File p_file1) throws Exception {
        FILE = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + p_file1);
        while (true) {
            System.out.println("In which format you want to save the file");
            System.out.println("1. Domination");
            System.out.println("2. Conquest");
            int l_ans = SC.nextInt();
            switch (l_ans) {
                case 1:
                    MapCreate l_map = new MapCreate();
                    l_map.createMap(FILE);
                    break;

                case 2:
                    MapCreateConquest l_maps = new MapCreateConquest();
                    l_maps.createMap(FILE);
                    break;

                default:
                    System.out.println("Enter correct command");

            }
        }
    }

    /**
     * This method shows the maps in the form of text.
     *
     * @throws Exception If file does not found at the path mentioned at ShowMap file, this will throw exception.
     */
    public static void mapShow() throws Exception {
        int l_flag = 0;
        while (true) {
            try {
                Scanner l_sc = new Scanner(System.in);
                File l_directoryPath = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\");
                String l_contents[] = l_directoryPath.list();
                int l_flag5 = 0;
                if (l_contents.length == 0) {
                    System.out.println("There are no predefined maps");
                    System.out.println("Let's create a new map");
                    while (true) {
                        System.out.println("Enter the name of the map");
                        SC = new Scanner(System.in);
                        String l_filename = SC.nextLine();
                        if (l_filename.endsWith(".map")) {
                            try {
                                File newFile = new File(l_filename);
                                l_flag5 = 1;
                                createMapByUser(newFile);
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Enter correct command\n");
                        }
                    }
                    if (l_flag5 == 1) {
                        break;
                    }
                }

                System.out.println("Here are the list of maps");
                System.out.println("");
                System.out.println("*************************");
                for (int l_i = 0; l_i < l_contents.length; l_i++) {
                    System.out.println(l_contents[l_i]);
                }
                System.out.println("*************************");
                System.out.println("");
                System.out.println("Enter the name of the map you want to select (Don't use extension)");
                String l_x = l_sc.nextLine();
                FILE = new File("C:\\SOEN-6441_TEAM_12-main\\src\\main\\resources\\maps\\" + l_x + ".map");
                break;
            } catch (Exception e) {
                System.out.println("Enter valid map name");
            }
        }
        ShowMapConquest l_show = new ShowMapConquest();
        ShowMap l_map = new ShowMap();

        Scanner l_sc3 = new Scanner(FILE);
        while (l_sc3.hasNextLine()) {
            String x =  l_sc3.next();
            if (x.equals("[countries]")) {
                l_flag = 1;
                break;
            }
            if (x.equals("[Territories]")) {
                l_flag = 2;
                break;
            }
        }

        if (l_flag == 1) {
            DominationAdapter l_maps = new DominationAdapter(l_map);
            l_maps.mapshow(FILE);
        }

        if(l_flag == 2){
            ConquestAdapter l_maps = new ConquestAdapter(l_show);
            l_maps.mapshow(FILE);
        }
    }

}
