package com.github.q11hackermans.slakeoverflow_client;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    GamePanel() {
        this.setBackground(Color.BLUE);
        JPanel a = new JPanel();
        a.setBackground(Color.GREEN);
        a.setMinimumSize(100,100);
        this.add(a);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}
