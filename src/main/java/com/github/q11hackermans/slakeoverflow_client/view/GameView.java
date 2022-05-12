//@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.view;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.observe.Observer;
import com.github.q11hackermans.slakeoverflow_client.panels.*;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;

import java.awt.event.*;

public class GameView extends JFrame implements Observer {

    private String host = null;
    private int port = 0;

    private GamePanel gamePanel = null;
    private LoginPanel loginPanel = null;
    private GameModel gameModel = null;

    public GameView() {
        this.createWindow();
        this.displayLoginPanel();
    }

    // WINDOWS

    /*
     * Create a proper window for the game and login panel
     */

    public void createWindow() {
        Logger.info("creating game window");

        this.setTitle("Slakeoverflow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(500, 500);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // PANELS + MODELS

    /*
     * GamePanel
     * displays game field
     */

    public void displayGamePanel() {
        if (this.host != null && this.port != 0) {
            this.gameModel = new GameModel(this.host, this.port);
            this.gamePanel = new GamePanel();
        } else {
            Logger.error("port and host must be specified.");
        }

    }

    public void renderGamePanel(int[][] fields) {
        if (this.gameModel != null) {
            this.gamePanel.render(fields);
        } else {
            Logger.error("port and host must be specified.");
        }
    }

    /*
     * LoginPanel
     * handles server host, port (later: username)
     */
    public void displayLoginPanel() {
        this.loginPanel = new LoginPanel();
        this.add(this.loginPanel);
    }

    // OTHER

    public void createKeyListener(KeyListener listener) {
        this.addKeyListener(listener);
    }

    @Override
    public void updateGameField(int[][] i) {
        this.renderGamePanel(i);
    }
}
