package org.StatePattern;

import org.Main.Main;

import java.util.Scanner;

/**
 * This is the main class of the Mapeditor phase.
 */
public class MapEditor extends MapEditorAbstract {

    /**
     * This is the object of the GameEngine1 class.
     */
    GameEngine1 d_ge;

    /**+
     * This is the object of the main class.
     */
    Main d_m;

    /**
     * This is the constructor of the MapEditorMain class.
     * @param p_ge This is the object of the GameEngine1 class.
     */
    public MapEditor(GameEngine1 p_ge) {
        super(p_ge);
        this.d_ge = p_ge;
    }

    @Override
    public void gamePlay() {

    }

    /**3
     * This method set the phase to MapEditor phase and also call the EditMap function to edit the map.
     * @throws Exception If file/map is not found then it will throw the exception.
     */
    @Override
    public void EditMap() throws Exception {
        d_m = new Main();
        d_m.EditMap();
        d_ge.setPhase(this);
    }

    /**
     * This method is called if ShowMaps method is called in the MapEditor Phase.
     * @throws Exception If file/map is not found then it will throw the exception.
     */
    @Override
    public void ShowMaps() throws Exception {
        printInvalidCommandMessage();
    }

    /**
     * This method is called after the MapEditor phase.
     * It asks the user whether to continue with the EditMap phase or quit the phase.
     */
    public void next() {
        Scanner l_sc = new Scanner(System.in);
        System.out.println("Do you want to edit the map or want to exit ?");
        System.out.println(" ===================================================");
        System.out.println("| #   CHOICE                   : command           |");
        System.out.println(" ===================================================");
        System.out.println("| 1.  Edit Map                 : editmap           |");
        System.out.println("| 2.  Quit                     : quit              |");
        System.out.println(" ===================================================");


        while (true) {
            String l_command = l_sc.nextLine();
            if (l_command.equalsIgnoreCase("editmap")) {
                Main l_edit = new Main();
                l_edit.EditMap();
                break;

            } else if (l_command.equalsIgnoreCase("quit")) {
                break;
            }
            else{
                System.out.println("Enter correct command");
            }
        }
    }

}
