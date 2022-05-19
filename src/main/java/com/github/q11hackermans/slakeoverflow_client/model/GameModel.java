package com.github.q11hackermans.slakeoverflow_client.model;

import java.io.IOException;
import java.util.List;

import com.github.q11hackermans.slakeoverflow_client.listeners.ModelEventListener;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import net.jandie1505.connectionmanager.client.CMCClient;

import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamHandler;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOType;
import org.json.JSONObject;

public class GameModel{

    private int[][] gameMatrix;

    private int gameStatus;

    private CMCClient  cmcClient;
    private DataIOStreamHandler dataIOStreamHandler;



    public GameModel(String host, int port) throws IOException {
        this.gameStatus = 0;
        gameMatrix = new int[][] {{0},{0}};
        this.cmcClient = new CMCClient(host, port, List.of(new ModelEventListener()));
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

    public int[][] getGameMatrix() {
        return gameMatrix;
    }


    //EVENTS
    public void lobbyJoined(){}
    public void lobbyClosed(){}

    public int[][] nextFrame(int[][] i){
        return i;
    }
}
