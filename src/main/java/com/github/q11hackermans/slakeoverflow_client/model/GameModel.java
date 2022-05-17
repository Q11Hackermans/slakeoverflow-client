package com.github.q11hackermans.slakeoverflow_client.model;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import com.github.q11hackermans.slakeoverflow_client.listeners.EventListener;
import com.github.q11hackermans.slakeoverflow_client.listeners.GameModelListener;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import com.github.q11hackermans.slakeoverflow_client.view.GameView;
import net.jandie1505.connectionmanager.client.CMCClient;

import com.github.q11hackermans.slakeoverflow_client.utility.KeyBinds;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamHandler;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOType;
import org.json.JSONObject;

public class GameModel{

    public GameModel model;

    private GameModelListener listener;

    private int[][] gameMatrix;

    private int gameStatus;

    private CMCClient  cmcClient;
    private DataIOStreamHandler dataIOStreamHandler;



    public GameModel(String host, int port) throws IOException {
        this.gameStatus = 0;
        this.model = this;
        gameMatrix = new int[][] {{0},{0}};
        this.cmcClient = new CMCClient(host, port, List.of(new EventListener()));
        this.dataIOStreamHandler = new DataIOStreamHandler(cmcClient, DataIOType.UTF, false);
    }

    /**
     * Receive data from the server
     */
    public void setMatrixData(int[][] gridData) {
        Logger.info("matrix data set");
        gameMatrix = gridData;
    }

    /**
     * Register keypresses and send them to the server
     * @param input
     */
    public void sendKeyInput(JSONObject input) {
        try {
            dataIOStreamHandler.writeUTF(input.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //SETTERS/GETTERS


    public GameModelListener getListener() {
        return listener;
    }

    public void setListener(GameModelListener listener) {
        this.listener = listener;
    }


    //EVENTS

    public void lobbyJoined(){}
    public void lobbyClosed(){}
    public int[][] nextFrame(int[][] i){
        return i;
    }

    public int[][] getGameMatrix() {
        return gameMatrix;
    }
}
