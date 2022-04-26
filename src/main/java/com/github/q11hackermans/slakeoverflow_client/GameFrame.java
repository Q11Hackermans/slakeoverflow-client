//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private GamePanel mainPanel;

    GameFrame() {
        this.mainPanel = new GamePanel();

        this.add(mainPanel);
        this.setTitle("Slakeoverflow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the enitre window instance
        this.setVisible(true); // otherwise the window will be hidden.
        this.setResizable(false);
        this.setSize(0, 0); // we'll change the size of the window based on the field count

    }

    public void render(int[][] fields) {

        this.setSize((fields.length + 1) * 50, (fields.length + 1) * 50); // every box will be 50*50px large
        this.mainPanel.render(fields);
    }
}
