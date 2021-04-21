package org.StatePattern;

import org.Main.Main;


/**
 * This is the main class of the ShowMap phase.
 */

public class ShowMap extends ShowMapAbstract {

    /**
     * This is the object of the GameEngine1 class.
     */
    GameEngine1 d_ge;

    /**
     * This is the object of the main class.
     */
    Main d_m;

    /**
     * This is the constructor of the ShowMap main class.
     * @param p_ge This is the object of the GameEngine1 class.
     */
    ShowMap(GameEngine1 p_ge) {
        super(p_ge);
        this.d_ge = p_ge;
    }

    @Override
    public void gamePlay() {

    }

    /**
     * This method set the phase to ShowMap phase and also call the ShowMap function to print the map details.
     * @throws Exception If file/map is not found then it will throw the exception.
     */
    @Override
    public void ShowMaps() throws Exception {
        d_m = new Main();
        d_m.mapShow();
        d_ge.setPhase(this);

    }

    /**
     * This method is called after the ShowMap phase.
     * This will set the phase to MapEditor phase from ShowMap phase.
     */
    public void next(){
        d_ge.setPhase(new MapEditor(d_ge));
    }


}
