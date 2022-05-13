package com.github.q11hackermans.slakeoverflow_client.listeners;

import net.jandie1505.connectionmanager.CMListenerAdapter;
import net.jandie1505.connectionmanager.events.CMClientClosedEvent;
import net.jandie1505.connectionmanager.events.CMClientCreatedEvent;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.events.DataIOUTFReceivedEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventListener extends CMListenerAdapter {
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
    }

    // DATA IO EVENTS
    @Override
    public void onUTFReceived(DataIOUTFReceivedEvent event) {
        try {
            JSONObject data = new JSONObject(event.getData());

            // WENN VOM SERVER NE NACHRICHT KOMMT, BEKOMMST DU DIE HIER

            String baseCommand = data.getString("cmd"); // SO HOLST DU DIR DEN STRING CMD AUS DEM JSONOBJECT RAUS

            switch (baseCommand){
                case "playerdata":
                    JSONArray j = data.getJSONArray("fields");

                case "":
                    System.out.println("");

                default:
                    break;
            }
        } catch(JSONException e) {
            System.out.println("Received data in wrong format. Disconnecting...");
            event.getClient().close();
        }
    }
}
