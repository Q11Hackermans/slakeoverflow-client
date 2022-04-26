package com.github.q11hackermans.slakeoverflow_client;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Slakeoverflow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // clo
        this.setVisible(true); //otherwise the window will be hidden.
        this.setSize(400,600);   
    }
}
