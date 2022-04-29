//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private GamePanel mainPanel;
    private LoginPanel loginPanel;

    GameFrame() {



        this.loginPanel = new LoginPanel();
        this.mainPanel = new GamePanel();

        //this.add(loginPanel);
        // this.getContentPane().setLayout(new BorderLayout());
        this.add(mainPanel); // add the mainPanel / GamePanel to the window

        this.setTitle("Slakeoverflow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true); // non resizable windows don't render properly
        this.pack();

    }

    public void render(int[][] fields) {
        int width = (fields.length) * 60;
        this.setSize(width, width); // set initial size for the window
        this.mainPanel.render(fields);
    }
}
