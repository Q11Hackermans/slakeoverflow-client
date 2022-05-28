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
    private JButton loginViewButton;
    private boolean loginButtonVisible;

    public LobbyPanel(ActionListener actionListener, boolean isLoggedIn) {
        playerMode = new JButton("Join as Player");
        spectatorMode = new JButton("Join as Spectator");
        disconnect = new JButton("Disconnect");
        loginViewButton = new JButton();


        this.playerMode.addActionListener(actionListener);
        this.playerMode.setActionCommand(ActionCommands.playButtonPressed);


        this.spectatorMode.addActionListener(actionListener);
        this.spectatorMode.setActionCommand(ActionCommands.spectatorButtonPressed);

        this.disconnect.addActionListener(actionListener);
        this.disconnect.setActionCommand(ActionCommands.disconnectButtonPressed);

        this.loginViewButton.addActionListener(actionListener);

        if (isLoggedIn){
            this.loginViewButton.setText("Logout");
            this.loginViewButton.setActionCommand(ActionCommands.logoutButton);
            this.loginButtonVisible = false;
        } else {
            this.loginViewButton.setText("Login");
            this.loginViewButton.setActionCommand(ActionCommands.toLoginViewButton);
            this.loginButtonVisible = true;
        }

        this.add(playerMode);
        this.add(spectatorMode);
        this.add(disconnect);
        this.add(loginViewButton);

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
