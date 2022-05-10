
package com.github.q11hackermans.slakeoverflow_client.model;

import java.awt.event.*;

import net.jandie1505.connectionmanager.client.CMCClient;

import com.github.q11hackermans.slakeoverflow_client.observe.Observable;
import com.github.q11hackermans.slakeoverflow_client.utility.KeyBinds;

// communicate with the server
public class GameModel extends Observable {

    private CMCClient connectionManagerInstance;
    public GameModel(String host, int port) {
        connectionManagerInstance = new CMCClient(host, port);

    }



    public void fetchData() {



        int[][] demoArray = {
                { 0, 101, 102 },
                { 0, 0, 102 },
                { 0, 0, 102 }
        };
        for (int i=0; i<=5; i++){
            this.push(demoArray);
        }
    }

    public void sendData(KeyEvent e) {
        int keyBind = 0;
        switch (e.getKeyCode()) {
            // up
            case 87:
                keyBind = KeyBinds.PLAYER_UP;
                break;

            // down
            case 83:
                keyBind = KeyBinds.PLAYER_DOWN;
                break;

            // left
            case 65:
                keyBind = KeyBinds.PLAYER_LEFT;
                break;

            // right
            case 68:
                keyBind = KeyBinds.PLAYER_RIGHT;
                break;

            default:
                System.out.println("-- key not found");
                keyBind = -1;
                break;
        }

        if (keyBind != -1) {
            System.out.println("-- sending " + keyBind);
        }
    }
}
