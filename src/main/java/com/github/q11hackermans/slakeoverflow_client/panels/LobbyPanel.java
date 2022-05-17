package com.github.q11hackermans.slakeoverflow_client.panels;

import com.github.q11hackermans.slakeoverflow_client.utility.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.view.View;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends View {

    private JButton playerMode;
    private JButton spectatorMode;
    private JButton disconnect;


    public LobbyPanel(ActionListener actionListener){
        playerMode = new JButton("Join as Player");
        spectatorMode = new JButton("Join as Spectator");
        disconnect = new JButton("Disconnect");


        this.playerMode.addActionListener(actionListener);
        this.playerMode.setActionCommand(ActionCommands.playButtonPressed);
        

        this.spectatorMode.addActionListener(actionListener);
        this.spectatorMode.setActionCommand(ActionCommands.spectatorButtonPressed);

        this.disconnect.addActionListener(actionListener);
        this.disconnect.setActionCommand(ActionCommands.disconnectButtonPressed);

    }



    public void configureJPanel() {
        this.setVisible(true);

        this.setPreferredSize(
                new Dimension(300, 700)
        );
        this.setLayout(new GridLayout(10, 1, 0, 10));
    }
}
