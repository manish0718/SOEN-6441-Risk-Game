package org.GamePlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Execute {
    ConcurrentHashMap<String,Player>d_playerList;
    boolean d_flag;
    public Execute(ConcurrentHashMap<String,Player> p_playerList)
    {
        this.d_playerList = p_playerList;
    }
    public void execute()
    {
        System.out.println("Execute Order Phase\n");
        d_flag = true;
        int l_p1 = 0;
        ArrayList<String> l_playersName = new ArrayList<>(d_playerList.keySet());
        while (d_flag) {      //Loop for iterating until all players give pass
            for (String l_player : l_playersName) {
                Player d_player = d_playerList.get(l_player);
                if (d_player.d_orders.size() == 0) {
                    l_p1 += 1;
                    l_playersName.remove(l_player);
                    break;
                }
                int l_maxArmiesToDeploy = d_player.d_armiesNum;
                Order l_nextOrder = d_player.next_order();

                if (l_nextOrder instanceof DeployOrder) {
                    System.out.println("Executing Order For Player : " + d_player.getD_name());
                    l_nextOrder.Execute(d_player);
                    System.out.println("");

                } else if (l_nextOrder instanceof Cards){
                    System.out.println("Executing Order For Player : " + d_player.getD_name());
                    l_nextOrder.Execute(d_player);
                    System.out.println("");
                }
                else if(l_nextOrder instanceof AdvanceArmies){
                    System.out.println("Executing Order For Player : " + d_player.getD_name());
                    l_nextOrder.Execute(d_player);
                    System.out.println("");
                }
                else {
                    System.out.println("Passing order for Player: " + d_player.d_name);
                    l_nextOrder.Execute(d_player);
                }
            }

            if (l_playersName.size() == 0) {
                d_flag = false;
            }
        }
        for (String l_player : l_playersName) {
            Player l_player1 = d_playerList.get(l_player);
            l_player1.d_negotiate.clear();
        }
    }
}