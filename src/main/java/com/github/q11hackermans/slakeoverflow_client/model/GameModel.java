package com.github.q11hackermans.slakeoverflow_client.model;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import com.github.q11hackermans.slakeoverflow_client.listeners.EventListener;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import net.jandie1505.connectionmanager.client.CMCClient;

import com.github.q11hackermans.slakeoverflow_client.observe.GameObservable;
import com.github.q11hackermans.slakeoverflow_client.utility.KeyBinds;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamHandler;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOType;
import org.json.JSONArray;
import org.json.JSONObject;

public class GameModel extends GameObservable {


    private int[][] gameField;
    private CMCClient  cmcClient;
    private DataIOStreamHandler dataIOStreamHandler;

    public GameModel(String host, int port) throws IOException {
        this.setCmcClient(host, port);
        this.cmcClient = new CMCClient(host, port, List.of(new EventListener()));
        this.dataIOStreamHandler = new DataIOStreamHandler(cmcClient, DataIOType.UTF, false);
    }

    /*public GameModel(){
        Logger.info("GameModel created without host and port set");
    }*/

    public void setData() {
        Logger.info("received data");
        int[][] demoArray = {
                { 0, 101, 102 },
                { 0, 0, 102 },
                { 0, 0, 102 }
        };
        for (int i=0; i<=5; i++){
            this.updateGame(demoArray);
        }
    }

    public void getKey(KeyEvent e) {
        int nextKey = 0;
        switch (e.getKeyCode()) {
            // up
            case 87:
                nextKey = KeyBinds.PLAYER_UP;
                break;

            // down
            case 83:
                nextKey = KeyBinds.PLAYER_DOWN;
                break;

            // left
            case 65:
                nextKey = KeyBinds.PLAYER_LEFT;
                break;

            // right
            case 68:
                nextKey = KeyBinds.PLAYER_RIGHT;
                break;

            default:
                Logger.info("key not found");
                nextKey = -1;
                break;
        }

        if (nextKey != -1) {
            Logger.info("key "+ nextKey + " pressed");
            JSONObject s = new JSONObject();
            s.put("cmd","game_direction_change");
            s.put("direction",nextKey);
            try {
                dataIOStreamHandler.writeUTF(s.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



    //GETTERS + SETTERS

    /*
        the gameField stores the current gameField
     */
    public int[][] getGameField() {
        return gameField;
    }

    public void setGameField(int[][] gameField) {
        this.gameField = gameField;
    }


    /*
        Create ( and get ) CMCClient if not already created in constructor
     */


    public CMCClient getCmcClient() {
        return cmcClient;
    }

    public void setCmcClient(String host, int port) {
        try {
            this.cmcClient = new CMCClient(host, port);
            dataIOStreamHandler = new DataIOStreamHandler(cmcClient, DataIOType.UTF, false);
            Logger.info("cmcClient and dataIOStreamHandler created");
        }catch (IOException e) {
            Logger.error(e.toString());
        }
    }
}
