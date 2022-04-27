package com.github.q11hackermans.slakeoverflow_client.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SnakeHead extends JPanel {

    public SnakeHead() {
        JLabel label = new JLabel(new ImageIcon("../assets/snake_head.jpg"));
        this.add(label);
    }

}
