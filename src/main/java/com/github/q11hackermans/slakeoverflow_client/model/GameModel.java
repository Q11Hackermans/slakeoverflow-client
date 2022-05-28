package com.github.q11hackermans.slakeoverflow_client.model;

import java.io.IOException;
import java.util.List;

import com.github.q11hackermans.slakeoverflow_client.constants.Direction;
import com.github.q11hackermans.slakeoverflow_client.controller.GameController;
import com.github.q11hackermans.slakeoverflow_client.listeners.ModelEventListener;
import com.github.q11hackermans.slakeoverflow_client.panels.GamePanel;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import net.jandie1505.connectionmanager.client.CMCClient;

import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamHandler;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamType;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOType;
import org.json.JSONObject;

public class GameModel {

    private int[][] gameMatrix;

    private int gameStatus;
    private long accountId;
    private int lastSentKeyInput;

    private GamePanel gamePanel;
    private GameController gameController;

    private CMCClient cmcClient;
    private DataIOStreamHandler dataIOStreamHandler;


    public GameModel(String host, int port, GamePanel gamePanel, GameController gameController) throws IOException {
        this.gameStatus = 0;
        this.accountId = -1;
        this.lastSentKeyInput = -1;
        gameMatrix = new int[][]{{0}, {0}};
        this.gamePanel = gamePanel;
        this.gameController = gameController;
        this.cmcClient = new CMCClient(host, port, List.of(new ModelEventListener(this)));
        cmcClient.getInputStream().setTimeInterval(2);
        this.dataIOStreamHandler = new DataIOStreamHandler(cmcClient, DataIOType.UTF, DataIOStreamType.MULTI_STREAM_HANDLER_CONSUMING);
        this.dataIOStreamHandler.addEventListener(new ModelEventListener(this));
    }

    /**
     * Receive data from the server
     */
    public void setGameMatrix(int[][] gridData) {
        //Logger.info("matrix data set");
        gameMatrix = gridData;
        this.gamePanel.render(gameMatrix);
        this.lastSentKeyInput = -1;
    }

    /**
     * Send key presses to the server
     *
     * @param nextKey
     */
    public void sendKeyInput(int nextKey) {
        try {
            if(nextKey != lastSentKeyInput) {
                //Logger.info("key " + nextKey + " pressed");
                JSONObject keyObj = new JSONObject();
                keyObj.put("cmd", "game_direction_change");
                keyObj.put("direction", nextKey);
                if (nextKey == Direction.SPEED){
                    keyObj = new JSONObject();
                    keyObj.put("cmd", "game_snake_speed_boost");
                }
                dataIOStreamHandler.writeUTF(keyObj.toString());
                lastSentKeyInput = nextKey;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void authPlayer(int type) {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "auth");
            output.put("type", type);
            dataIOStreamHandler.writeUTF(output.toString());
            Logger.debug("Auth: " + type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void login(String username, String password) {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "login");
            output.put("username", username);
            output.put("password", password);
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void logout() {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "logout");
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void registerAccount(String username, String password) {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "register");
            output.put("username", username);
            output.put("password", password);
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void getUserInfo() {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "get_user_info");
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void getServerInfo() {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "get_server_info");
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    //SETTERS/GETTERS-
    public int[][] getGameMatrix() {
        return gameMatrix;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public boolean isLoggedIn() {
        return accountId > -1;
    }

    public void gameControllerDisconnect() {
        this.gameController.disconnectFromServerError();
    }

    public void gameControllerSwitchToGamePanel() {
        this.gameController.switchToGamePanel();
    }

    public void gameControllerSwitchToUnAuthPanel() {
        this.gameController.switchToUnAuthPanel();
    }

    public void gameControllerSwitchToLobbyPanel() {
        this.gameController.switchToLobbyPanel();
    }

    public void gameControllerSwitchToLoginPanel() {
        this.gameController.switchToLoginPanel();
    }

    public void disconnect() {
        this.cmcClient.close();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //EVENTS

    public void lobbyJoined() {
    }

    public void lobbyClosed() {
    }

    public int[][] nextFrame(int[][] i) {
        return i;
    }
}
