package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends UnauthenticatedPanel {

    private JButton playerMode;
    private JButton spectatorMode;
    private JButton disconnect;
    private JButton loginViewButton;
    private JButton storeViewButton;
    private boolean loginButtonVisible;

    public LobbyPanel(ActionListener actionListener, boolean isLoggedIn) {
        playerMode = new JButton("Join as Player");
        spectatorMode = new JButton("Join as Spectator");
        disconnect = new JButton("Disconnect");
        loginViewButton = new JButton();
        storeViewButton = new JButton("Store");


        this.playerMode.addActionListener(actionListener);
        this.playerMode.setActionCommand(ActionCommands.playButtonPressed);


        this.spectatorMode.addActionListener(actionListener);
        this.spectatorMode.setActionCommand(ActionCommands.spectatorButtonPressed);

        this.disconnect.addActionListener(actionListener);
        this.disconnect.setActionCommand(ActionCommands.disconnectButtonPressed);

        this.loginViewButton.addActionListener(actionListener);
        this.storeViewButton.addActionListener(actionListener);

        this.add(playerMode);
        this.add(spectatorMode);
        this.add(disconnect);
        this.add(loginViewButton);

        if (isLoggedIn){
            this.loginViewButton.setText("Logout");
            this.loginViewButton.setActionCommand(ActionCommands.logoutButton);
            this.loginButtonVisible = false;

            this.storeViewButton.setActionCommand(ActionCommands.toStoreViewButton);
            this.add(this.storeViewButton);
        } else {
            this.loginViewButton.setText("Login");
            this.loginViewButton.setActionCommand(ActionCommands.toLoginViewButton);
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
