//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.view;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.observe.Observer;
import com.github.q11hackermans.slakeoverflow_client.panels.*;

import java.awt.event.*;

public class GameView extends JFrame implements Observer {

    private GamePanel gamePanel;
    private LoginPanel loginPanel;

    private GameModel gameModel;

    public GameView() {
        this.loginPanel = new LoginPanel();
        this.gamePanel = new GamePanel();
        this.gameModel = new GameModel();

        // this.add(loginPanel);
        this.add(gamePanel);

        this.setTitle("Slakeoverflow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);

        // if fullscreening doesn't work (as usual), set default size
        this.setSize(500, 500);

        // fullscreen jframe
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void render(int[][] fields) {
        this.gamePanel.render(fields);
    }

    public void createKeyListener(KeyListener listener) {
        this.addKeyListener(listener);
    }

    @Override
    public void update(int[][] i) {
        this.render(i) ; 
    }
}
