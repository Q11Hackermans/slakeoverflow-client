package com.github.q11hackermans.slakeoverflow_client.listeners;

import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import net.jandie1505.connectionmanager.CMListenerAdapter;
import net.jandie1505.connectionmanager.events.CMClientClosedEvent;
import net.jandie1505.connectionmanager.events.CMClientCreatedEvent;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.events.DataIOUTFReceivedEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ModelEventListener extends CMListenerAdapter {
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
        System.exit(101);
    }

    // DATA IO EVENTS
    @Override
    public void onUTFReceived(DataIOUTFReceivedEvent event) {
        try {
            JSONObject data = new JSONObject(event.getData());

            Logger.info("Model Event listener-data: " + data);

            // WENN VOM SERVER NE NACHRICHT KOMMT, BEKOMMST DU DIE HIER

            if(data.has("cmd")) {
                String baseCommand = data.getString("cmd"); // SO HOLST DU DIR DEN STRING CMD AUS DEM JSONOBJECT RAUS

                switch (baseCommand) {
                    case "playerdata":
                        JSONArray rawData = data.getJSONArray("fields");

                        int[][] gridData = new int[rawData.length()][];

                        for (int i = 0; i < rawData.length(); i++) {
                            gridData[i] = new int[rawData.getJSONArray(i).length()];
                            for (int j = 0; j < rawData.getJSONArray(i).length(); j++) {
                                gridData[i][j] = rawData.getJSONArray(i).getInt(j);
                            }
                        }
                        //model.setMatrixData(gridData);
                        break;

                    case "status":
                        int gameStatus = data.getInt("status");
                        int authStatus = data.getInt("auth");
                        break;

                    case "ready":
                        System.out.println("FERERERERETIG");
                        break;

                    default:
                        break;
                }
            }
        } catch(JSONException e) {
            System.out.println("Received data in wrong format. Disconnecting...");
            event.getClient().close();
        } catch (NumberFormatException e){
            System.out.println("NumberFormatException in data receive Event listener: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
