package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends UnauthenticatedPanel {

    private JButton playerMode;
    private JButton spectatorMode;
    private JButton disconnect;
    private JButton loginPanelButton;
    private JButton storePanelButton;
    private boolean loginButtonVisible;

    public LobbyPanel(ActionListener actionListener, boolean isLoggedIn) {
        playerMode = new JButton("Join as Player");
        spectatorMode = new JButton("Join as Spectator");
        disconnect = new JButton("Disconnect");
        loginPanelButton = new JButton();
        storePanelButton = new JButton("Store");


        this.playerMode.addActionListener(actionListener);
        this.playerMode.setActionCommand(ActionCommands.playButtonPressed);


        this.spectatorMode.addActionListener(actionListener);
        this.spectatorMode.setActionCommand(ActionCommands.spectatorButtonPressed);

        this.disconnect.addActionListener(actionListener);
        this.disconnect.setActionCommand(ActionCommands.disconnectButtonPressed);

        this.loginPanelButton.addActionListener(actionListener);
        this.storePanelButton.addActionListener(actionListener);

        this.add(playerMode);
        this.add(spectatorMode);
        this.add(disconnect);
        this.add(loginPanelButton);

        if (isLoggedIn){
            this.loginPanelButton.setText("Logout");
            this.loginPanelButton.setActionCommand(ActionCommands.logoutButton);
            this.loginButtonVisible = false;

            this.storePanelButton.setActionCommand(ActionCommands.toStoreViewButton);
            this.add(this.storePanelButton);
        } else {
            this.loginPanelButton.setText("Login");
            this.loginPanelButton.setActionCommand(ActionCommands.toLoginViewButton);
            this.loginButtonVisible = true;
        }

    }

    public boolean isLoginButtonVisible() {
        return loginButtonVisible;
    }

    private void configureJPanel() {
        this.setVisible(true);

        this.setPreferredSize(
                new Dimension(300, 700)
        );
        this.setLayout(new GridLayout(10, 1, 0, 10));
    }
}
