package com.github.q11hackermans.slakeoverflow_client.model;

import com.github.q11hackermans.slakeoverflow_client.constants.Direction;
import com.github.q11hackermans.slakeoverflow_client.controller.GameController;
import com.github.q11hackermans.slakeoverflow_client.listeners.ModelEventListener;
import com.github.q11hackermans.slakeoverflow_client.panels.GamePanel;
import com.github.q11hackermans.slakeoverflow_client.utility.OldLogger;
import net.jandie1505.connectionmanager.client.CMCClient;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamHandler;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamType;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOType;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameModel {


    private final Map<Integer, ShopItem> items;
    private final int gameStatus;
    private final GameController gameController;
    private final CMCClient cmcClient;
    private final DataIOStreamHandler dataIOStreamHandler;
    private long accountId;
    private String serverName;
    private int coinBalance;
    private int activeItem;
    private String username;
    private int lastSentKeyInput;
    private int[][] gameMatrix;
    private GamePanel gamePanel;

    public GameModel(String host, int port, GamePanel gamePanel, GameController gameController) throws IOException {
        this.gameStatus = 0;
        this.accountId = -1;
        this.serverName = "Slakomania";
        this.coinBalance = 0;
        this.username = "LOADING";
        this.items = new HashMap<>();
        this.items.put(0, new ShopItem(0, 0, true));
        this.activeItem = 0;

        this.lastSentKeyInput = -1;
        gameMatrix = new int[][]{{0}, {0}};
        this.gamePanel = gamePanel;
        this.gameController = gameController;

        this.cmcClient = new CMCClient(host, port, List.of(new ModelEventListener(this)));
        cmcClient.getInputStream().setTimeInterval(2);
        this.dataIOStreamHandler = new DataIOStreamHandler(cmcClient, DataIOType.UTF, DataIOStreamType.MULTI_STREAM_HANDLER_CONSUMING);
        this.dataIOStreamHandler.addEventListener(new ModelEventListener(this));
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

    public void disconnect() {
        this.cmcClient.close();
    }

    /**
     * Send key presses to the server
     *
     * @param nextKey
     */
    public void sendKeyInput(int nextKey) {
        try {
            if (nextKey != lastSentKeyInput) {
                //Logger.info("key " + nextKey + " pressed");
                JSONObject keyObj = new JSONObject();
                keyObj.put("cmd", "game_direction_change");
                keyObj.put("direction", nextKey);
                if (nextKey == Direction.SPEED) {
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

    private void authPlayer(int type) {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "auth");
            output.put("type", type);
            dataIOStreamHandler.writeUTF(output.toString());
            OldLogger.debug("Auth: " + type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void unauthPlayer() {
        this.authPlayer(0);
    }

    public void authPlayer() {
        this.authPlayer(1);
    }

    /**
     * Send chat message to the server
     *
     * @param message
     */
    public void sendChatMessage(String message) {
        System.out.println(message);
        try {
            JSONObject chatObj = new JSONObject();
            chatObj.put("cmd", "chat");
            chatObj.put("msg", message);
            dataIOStreamHandler.writeUTF(chatObj.toString());
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

    public void requestUserInfo() {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "get_user_info");
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void requestServerInfo() {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "get_server_info");
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void buyItem(int id) {
        try {
            JSONObject output = new JSONObject();
            output.put("cmd", "shop_purchase");
            output.put("item", id);
            dataIOStreamHandler.writeUTF(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void gameControllerDisconnectError() {
        this.gameController.disconnectFromServerError();
    }

    public void gameControllerSwitchToGamePanel() {
        this.gameController.switchToGamePanel();
    }

    public void gameControllerSwitchToUnAuthPanel() {
        this.gameController.switchToUnauthPanel();
    }

    public void gameControllerSwitchToLobbyPanel() {
        this.gameController.switchToLobbyPanel();
    }

    public void gameControllerSwitchToLoginPanel() {
        this.gameController.switchToLoginPanel();
    }

    public void addChatMessage(String msg) {
        if (this.gamePanel != null) {
            this.gamePanel.applyNextMessage(msg);
        }
    }


    // Getter - Setter

    public int[][] getGameMatrix() {
        return gameMatrix;
    }

    /**
     * Receive data from the server
     */
    public void setGameMatrix(int[][] gridData) {
        //Logger.info("matrix data set");
        gameMatrix = gridData;
        this.gamePanel.render(gameMatrix, getActiveItem());
        this.lastSentKeyInput = -1;
    }

    public boolean isLoggedIn() {
        return accountId > -1;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public GameController getGameController() {
        return gameController;
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.equals("")) {
            this.username = "LOADING";
        } else {
            this.username = username;
        }
    }

    public int getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(int coinBalance) {
        this.coinBalance = coinBalance;
    }

    public int getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(int activeItem) {
        this.activeItem = activeItem;
    }

    public Map<Integer, ShopItem> getItems() {
        return items;
    }

    public void setItemPrices(Map<Integer, Integer> itemPrices) {
        itemPrices.forEach((id, price) -> {
            this.items.put(id, new ShopItem(price, id));
        });
        System.out.println(items.toString());
    }

    public void setOwnedItems(ArrayList<Integer> ownedItems) {
        this.items.forEach((k, v) -> {
            if (k > 0) {
                v.setOwned(false);
            }
        });
        ownedItems.forEach(i -> {
            this.items.get(i).setOwned(true);
        });
        System.out.println(this.items);
    }
}
