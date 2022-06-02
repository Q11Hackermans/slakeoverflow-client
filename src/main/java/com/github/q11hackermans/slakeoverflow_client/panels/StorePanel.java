package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StorePanel extends UnauthenticatedPanel {

    private JButton backToLobby;


    public StorePanel(ActionListener actionListener) {
        backToLobby = new JButton("Back to Lobby");


        this.backToLobby.addActionListener(actionListener);
        backToLobby.setActionCommand(ActionCommands.backToLobbyButton);

        this.add(backToLobby);

    }


    private void configureJPanel() {

    }
}
