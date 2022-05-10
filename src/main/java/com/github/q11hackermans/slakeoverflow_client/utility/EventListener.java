package com.github.q11hackermans.slakeoverflow_client.utility;

import com.github.q11hackermans.slakeoverflow_client.controller.GameController;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import net.jandie1505.connectionmanager.CMListenerAdapter;
import net.jandie1505.connectionmanager.events.CMClientClosedEvent;
import net.jandie1505.connectionmanager.events.CMClientCreatedEvent;
import net.jandie1505.connectionmanager.server.CMSClient;

import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.DataIOStreamHandler;
import net.jandie1505.connectionmanager.utilities.dataiostreamhandler.events.DataIOUTFReceivedEvent;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class EventListener extends CMListenerAdapter{



    EventListener(){}

    // CLIENT EVENTS
    @Override
    public void onClientCreated(CMClientCreatedEvent event) {
        System.out.println(event);
        GameModel.setState(1);


    }

    @Override
    public void onClientClosed(CMClientClosedEvent event) {
        CMSClient cmsClient = (CMSClient) event.getClient();

        SlakeoverflowServer.getServer().getLogger().info("CONNECTION", "Client " + cmsClient.getUniqueId() + " (" + cmsClient.getIP() + ") disconnected with reason " + event.getReason());
    }

    // DATA IO EVENTS
    @Override
    public void onUTFReceived(DataIOUTFReceivedEvent event) {
        CMSClient cmsClient = (CMSClient) event.getClient();

        try {
            JSONObject data = new JSONObject(event.getData());

            if(data.has("cmd")) {
                switch(data.getString("cmd")) {
                    case "auth":
                        if(data.has("type")) {
                            if(data.getString("type").equalsIgnoreCase("player")) {
                                if(data.has("username")) {
                                    // REGISTER PLAYER
                                }
                            } else if(data.getString("type").equalsIgnoreCase("spectator")) {
                                cmsClient.close();
                            }
                        }
                        break;
                }
            }
        } catch(JSONException e) {
            SlakeoverflowServer.getServer().getLogger().warning("CONNECTION", "Received wrong package format from " + cmsClient.getUniqueId());
            cmsClient.close();
        }
    }
}