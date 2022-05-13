package com.github.q11hackermans.slakeoverflow_client.panels;

import javax.swing.*;
import java.awt.* ;
import java.awt.event.* ;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        this.setBackground(ground1);

        // JButton mit Text "Drück mich" wird erstellt
        JButton button = new JButton("Play");

        // JButton wird dem Panel hinzugefügt
        panel.add(button);

        this.button.setBounds(50,100,100,30)

        meinJFrame.setVisible(true);
    }
}