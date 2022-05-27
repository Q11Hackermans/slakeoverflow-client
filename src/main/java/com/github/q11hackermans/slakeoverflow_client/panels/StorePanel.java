package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StorePanel extends View {

    private JButton backToLobby;


    public StorePanel(ActionListener actionListener) {
        backToLobby = new JButton("Back to Lobby");


        this.backToLobby.addActionListener(actionListener);
        backToLobby.setActionCommand(ActionCommands.backToLobbyButton);

        this.add(backToLobby);

    }


    public void configureJPanel() {
        this.setVisible(true);

        this.setPreferredSize(
                new Dimension(300, 700)
        );
        this.setLayout(new GridLayout(10, 1, 0, 10));
    }
}
