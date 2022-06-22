package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LobbyPanel extends UnauthenticatedPanel {

    private JButton playerMode;
    private JButton spectatorMode;
    private JButton disconnect;
    private JButton logInOutButton;
    private JButton storePanelButton;
    private JLabel usernameLabel;
    private GameModel gameModel;

    private String usernameLabelText;
    private boolean loginButtonVisible;

    public LobbyPanel(ActionListener actionListener, GameModel gameModel) {

        this.gameModel = gameModel;
        this.gameModel.requestUserInfo();
        this.getGameModelData();

        playerMode = new JButton("Join as Player");
        spectatorMode = new JButton("Join as Spectator");
        disconnect = new JButton("Disconnect");
        storePanelButton = new JButton("Store");
        logInOutButton = new JButton();
        usernameLabel = new JLabel(this.usernameLabelText);


        this.playerMode.addActionListener(actionListener);
        this.playerMode.setActionCommand(ActionCommands.playButtonPressed);


        this.spectatorMode.addActionListener(actionListener);
        this.spectatorMode.setActionCommand(ActionCommands.spectatorButtonPressed);

        this.disconnect.addActionListener(actionListener);
        this.disconnect.setActionCommand(ActionCommands.disconnectButtonPressed);

        this.logInOutButton.addActionListener(actionListener);
        this.storePanelButton.addActionListener(actionListener);

        this.add(playerMode);
        this.add(spectatorMode);
        this.add(disconnect);

        if (this.loginButtonVisible){
            this.logInOutButton.setText("Logout");
            this.logInOutButton.setActionCommand(ActionCommands.logoutButton);

            this.storePanelButton.setActionCommand(ActionCommands.toStoreViewButton);
            this.add(this.storePanelButton);
            this.add(usernameLabel);
        } else {
            this.logInOutButton.setText("Login");
            this.logInOutButton.setActionCommand(ActionCommands.toLoginViewButton);
        }

        this.add(logInOutButton);

    }

    public boolean isLoginButtonVisible() {
        return loginButtonVisible;
    }

    /*private void configureJPanel() {
        this.setVisible(true);

        this.setPreferredSize(
                new Dimension(300, 700)
        );
        this.setLayout(new GridLayout(10, 1, 0, 10));
    }*/

    private void getGameModelData(){
        this.usernameLabelText = ("Logged in as: " + this.gameModel.getUsername());
        this.loginButtonVisible = this.gameModel.isLoggedIn();
    }

    private String username(){
        return this.usernameLabelText.split("Logged in as: ")[1];
    }

    @Override
    public boolean isUpToDate(Panel panel) {
        if (panel.getClass() == this.getClass()){
            return Objects.equals(((LobbyPanel) panel).username(), this.gameModel.getUsername()) && Objects.equals(((LobbyPanel) panel).isLoginButtonVisible(), this.gameModel.isLoggedIn());
        }
        return false;
    }
}
