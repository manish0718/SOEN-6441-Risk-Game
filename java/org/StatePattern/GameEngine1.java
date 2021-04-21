package org.StatePattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.GamePlay.Country;
import org.GamePlay.GameEngine;
import org.GamePlay.Player;
import org.StatePattern.ShowMap;
import org.ObserverBasedLogging.LogWriter;
import org.Tournament.TournamentMain;

/**
 * Class to start the game.
 */
public class GameEngine1 {

    /**
     * State object of the GameEngine
     */
    private Phase d_gamePhase;

    /**
     * Integer variable to store the Option.
     */
    public int MYSTART;
    /**
     * String variable to store the command.
     */
    public String MYCOMMAND;

    /**
     * String variable to store the message to be displayed. Used for testing file only.
     */
    public String MESSAGE;

    public TournamentMain d_tournamentMain ;

    /**
     * Method that allows the GameEngine object to change its state.
     *
     * @param p_phase new state to be set for the GameEngine object.
     */
    public void setPhase(Phase p_phase) {
        d_gamePhase = p_phase;
        if(!(p_phase instanceof End)) {
            MESSAGE = "new phase: " + p_phase.getClass().getSimpleName();
            System.out.println(MESSAGE);
        }
    }

    /**
     * This method will ask the user:
     * 1. What part of the game they want to start with (edit map or play game).
     * Depending on the choice, the state will be set to a different object,
     * which will set different behavior.
     * 2. What command they want to execute from the game.
     * Depending on the state of the GameEngine, each command will potentially
     * have a different behavior.
     * @throws Exception as it is using the ShowMap function from the Main class and as
     * it is throwing exception which needs to be handled.
     */
    public void start() throws Exception {
        d_tournamentMain  = new TournamentMain();
        Scanner l_keyboard = new Scanner(System.in);
        do {
            LogWriter l_log = new LogWriter();
            System.out.println("1. Edit Map");
            System.out.println("2. Play Game");
            System.out.println("3. Load Game");
            System.out.println("4. Tournament Mode");
            System.out.println("5. Quit");
            System.out.println("Where do you want to start?: ");
            MYSTART = l_keyboard.nextInt();
            switch (MYSTART) {

                case 1:
                    ShowMap l_map = new ShowMap(this);
                    setPhase(l_map);
                    break;

                case 2:
                    GameStartUpPhaseMain l_ge = new GameStartUpPhaseMain(this);
                    setPhase(l_ge);
                    break;
                case 3:
                    System.out.println("Enter fileName (without extension): ");
                    Scanner l_sc = new Scanner(System.in);
                    String l_fileName= l_sc.nextLine();
                    l_fileName+=".ser";
                    File l_file = new File(".\\src\\main\\resources\\maps\\"+l_fileName);
                    serialObj serialOBJ;
                    Country l_country;
                    ConcurrentHashMap <String, Player> l_playerList = new ConcurrentHashMap<>();
                    try{
                        FileInputStream l_fileInputStream = new FileInputStream(l_file);
                        ObjectInputStream l_inputStream = new ObjectInputStream(l_fileInputStream);

                        serialOBJ =(serialObj) l_inputStream.readObject();

                        GameEngine ge = new GameEngine();
                        ge.d_PLAYERS_LIST = serialOBJ.d_playerList;
                        ge.d_COUNTRY.COUNTRIESLIST = (ConcurrentHashMap<String, Country>) l_inputStream.readObject();
                        GamePlayMain phase = new GamePlayMain(this,ge);
                        setPhase(phase);
                        for(String l_playerName: l_playerList.keySet())
                        {
                            System.out.print("PlayerName: "+l_playerName+" owned countries are:-");
                            for(Country cou:l_playerList.get(l_playerName).d_owned)
                            {
                                System.out.print(" "+cou.d_countryId);
                            }
                            System.out.println("");
                        }
                        l_fileInputStream.close();
                        l_inputStream.close();

                    }catch(Exception e)
                    {
                        System.out.println("Exception e :"+e);
                    }
                    break;
                case 4:
                    String result = d_tournamentMain.run();
                    return;
                case 5:
                    System.out.println("Bye!");
                    return;
            }
            int l_i=0;
            l_keyboard.nextLine();
            do {
                System.out.println(" =================================================");
                System.out.println("| #   PHASE                   : command           |");
                System.out.println(" =================================================");
                System.out.println("| 1.  Show Map                : showmap           |");
                System.out.println("| 2.  Edit Map                : editmap           |");
                System.out.println("| 3.  GamePlay : load map     : loadmap           |");
                System.out.println("| 4.  GamePlay: set Player    : gameplayer        |");
                System.out.println("| 6.  Game Play: execution    : gameplay          |");
                System.out.println("| 5.  Any Phase               : end game          |");
                System.out.println("| 6.  Any Phase               : next phase        |");
                System.out.println(" =================================================");
                System.out.println("enter a " + d_gamePhase.getClass().getSimpleName() + " phase command: ");
                MYCOMMAND = l_keyboard.nextLine();
                System.out.println(" =================================================");

                switch (MYCOMMAND) {
                    case ("showmap"):
                        d_gamePhase.ShowMaps();
                        break;
                    case ("editmap"):
                        d_gamePhase.EditMap();
                        break;
                    case ("exit"):
                        l_i=1;
                        break;
                    case ("loadmap"):
                        d_gamePhase.playGame();
                        break;
                    case("gameplayer"):
                        d_gamePhase.startGameEngine();
                        break;
                    case ("next"):
                        d_gamePhase.next();
                        break;
                    case ("mapshow"):
                        d_gamePhase.mapshow();
                        break;
                    case ("gameplay"):
                        d_gamePhase.gamePlay();
                        break;
                    case("end"):
                    {
                        d_gamePhase = new End(new GameEngine1());
                    }
                    default:
                        System.out.println("this command does not exist");
                }
                if(l_i==1){
                    break;
                }
                l_i=0;
            } while (!(d_gamePhase instanceof End));
        } while (MYSTART != 5);
        l_keyboard.close();
    }
}
