package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends View {

    private JButton playerMode;
    private JButton spectatorMode;
    private JButton disconnect;
    private JButton login;

    public LobbyPanel(ActionListener actionListener) {
        playerMode = new JButton("Join as Player");
        spectatorMode = new JButton("Join as Spectator");
        disconnect = new JButton("Disconnect");
        login = new JButton("Login");


        this.playerMode.addActionListener(actionListener);
        this.playerMode.setActionCommand(ActionCommands.playButtonPressed);


        this.spectatorMode.addActionListener(actionListener);
        this.spectatorMode.setActionCommand(ActionCommands.spectatorButtonPressed);

        this.disconnect.addActionListener(actionListener);
        this.disconnect.setActionCommand(ActionCommands.disconnectButtonPressed);

        this.login.addActionListener(actionListener);
        this.login.setActionCommand(ActionCommands.toLoginViewButton);

        this.add(playerMode);
        this.add(spectatorMode);
        this.add(disconnect);
        this.add(login);

    }


    public void configureJPanel() {
        this.setVisible(true);

        this.setPreferredSize(
                new Dimension(300, 700)
        );
        this.setLayout(new GridLayout(10, 1, 0, 10));
    }
}
