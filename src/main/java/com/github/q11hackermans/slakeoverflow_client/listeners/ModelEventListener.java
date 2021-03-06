package com.github.q11hackermans.slakeoverflow_client.listeners;

import com.github.q11hackermans.slakeoverflow_client.constants.ConnectionType;
import com.github.q11hackermans.slakeoverflow_client.constants.GameState;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.utility.OldLogger;
import net.jandie1505.connectionmanager.CMListenerAdapter;
import net.jandie1505.connectionmanager.events.CMClientClosedEvent;
import net.jandie1505.connectionmanager.events.CMClientCreatedEvent;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.events.DataIOUTFReceivedEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class ModelEventListener extends CMListenerAdapter {

    private GameModel gameModel;

    public ModelEventListener(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    // CLIENT EVENTS
    @Override
    public void onClientCreated(CMClientCreatedEvent event) {
        // WAS PASSIEREN SOLL WENN DIE VERBINDUNG ERFOLGREICH WAR SCHREIBST DU HIER REIN
        event.getClient(); // SO BEKOMMST DU DEN CLIENT

    }

    @Override
    public void onClientClosed(CMClientClosedEvent event) {
        // WAS PASSIEREN SOLL WENN DIE VERBINDUNG ABBRICHT SCHREIBST DU HIER REIN
        event.getClient(); // SO BEKOMMST DU (MAL WIEDER) DEN CLIENT)
        event.getReason(); // SO BEKOMMST DU DEN GRUND WARUM DIE VERBINDUNG GETRENNT WURDE
        gameModel.gameControllerDisconnectError();
    }

    // DATA IO EVENTS
    @Override
    public void onUTFReceived(DataIOUTFReceivedEvent event) {
        try {
            JSONObject data = new JSONObject(event.getData());

            // Logger.info("Model Event listener-data: " + data.toString());

            // WENN VOM SERVER NE NACHRICHT KOMMT, BEKOMMST DU DIE HIER

            if (data.has("cmd")) {
                String baseCommand = data.getString("cmd"); // SO HOLST DU DIR DEN STRING CMD AUS DEM JSONOBJECT RAUS

                switch (baseCommand) {
                    case "playerdata":
                        if (data.has("fields")) {
                            JSONArray rawData = data.getJSONArray("fields");
                            //System.out.println(rawData.toString());

                            int[][] gridData = new int[60][40];
                            for (Object jo : rawData) {
                                JSONArray jj = (JSONArray) jo;
                                try {
                                    gridData[jj.getInt(0)][jj.getInt(1)] = jj.getInt(2);
                                } catch (IndexOutOfBoundsException ignored) {
                                }
                            }
                            this.gameModel.setGameMatrix(gridData);
                        }
                        break;

                    case "status":
                        int gameStatus = data.getInt("status");
                        int authStatus = data.getInt("auth");
                        long accountId = data.getLong("account");
                        //System.out.println("Game status:" + gameStatus + ", auth status:" + authStatus + ", account: " + accountId + ", Message: " + data);
                        this.handleStatusMessage(data);
                        break;

                    case "server_ready":
                        this.gameModel.requestServerInfo();
                        break;

                    case "server_info":
                        this.handleServerInfo(data);
                        break;

                    case "user_info":
                        this.handleUserInfo(data);
                        break;

                    case "message":
                        this.gameModel.addChatMessage(data.getString("msg"));
                        break;

                    default:
                        System.out.println(data);
                        break;
                }
            }
        } catch (JSONException e) {
            OldLogger.error("Received data in wrong format. Disconnecting...");
            e.printStackTrace();
            this.gameModel.gameControllerDisconnectError();
        } catch (NumberFormatException e) {
            OldLogger.error("NumberFormatException in data receive Event listener: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void handleServerInfo(JSONObject data) {
        this.gameModel.setServerName(data.getJSONObject("server_settings").getString("server_name"));

        JSONObject shopItems = data.getJSONObject("shop_items");
        HashMap<Integer, Integer> itemPrices = new HashMap<>();
        Set<String> items = shopItems.keySet();
        items.forEach(it -> {
            itemPrices.put(Integer.valueOf(it), shopItems.getJSONObject(it).getInt("price"));
        });
        this.gameModel.setItemPrices(itemPrices);

        System.out.println(data);
    }

    private void handleUserInfo(JSONObject data) {
        System.out.println(data);
        this.gameModel.setUsername(data.getString("account_name"));
        this.gameModel.setCoinBalance(data.getInt("account_balance"));
        JSONArray jsonHasItems = data.getJSONArray("shopData");
        ArrayList<Integer> hasItems = new ArrayList<>();
        for(int i = 0; i < jsonHasItems.length(); i ++){
            hasItems.add(Integer.valueOf(jsonHasItems.get(i).toString()));
        }
        this.gameModel.setOwnedItems(hasItems);
    }

    private void handleStatusMessage(JSONObject data) {
        int gameStatus = data.getInt("status");
        int authStatus = data.getInt("auth");
        long accountId = data.getLong("account");

        this.gameModel.setAccountId(accountId);
        this.gameModel.setGameStatus(gameStatus);
        this.gameModel.setAuthStatus(authStatus);

        if ((gameStatus == GameState.RUNNING || gameStatus == GameState.PAUSED) && authStatus == ConnectionType.PLAYER) {
            this.gameModel.gameControllerSwitchToGamePanel();
        } else if ((!(gameStatus == GameState.RUNNING || gameStatus == GameState.PAUSED) || authStatus != ConnectionType.PLAYER)) {
            this.gameModel.gameControllerSwitchToUnAuthPanel();
        }

    }
}
