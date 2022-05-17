 //@blame Joshua3212
package com.github.q11hackermans.slakeoverflow_client.view;

import javax.swing.*;

import com.github.q11hackermans.slakeoverflow_client.listeners.GameModelListener;
import com.github.q11hackermans.slakeoverflow_client.listeners.LoginPanelListener;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.panels.*;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;

import java.awt.event.*;

public class GameView extends JFrame implements GameModelListener, LoginPanelListener, View {

    private GamePanel gamePanel = null;
    private GameModel gameModel;
    private LoginPanel loginPanel = null;

    public GameView(GameModel model) {
        this.gameModel = model;
        this.createWindow();
        //this.displayLoginPanel();

        Logger.debug("manually rendering gamePanel -- todo: automatically check for port + host");
        this.displayGamePanel();
    }

    // FRAME

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
        //this.setVisible(false);
    }

    // PANELS

    /*
     * GamePanel
     * displays game field
     */

    public void displayGamePanel() {
        this.gamePanel = new GamePanel();
        this.add(this.gamePanel);
    }

    public void removeGamePanel(){
        this.remove(this.gamePanel);
    }


    /*
    (Re)render gameField
     */
    public void renderGame(int[][] fields) {
        this.gamePanel.render(fields);
    }

    /*
     * LoginPanel
     * handles server host, port (later: username)
     */
    public void displayLoginPanel() {
        this.loginPanel = new LoginPanel();
        this.add(this.loginPanel);


        // (re)register listeners
        this.registerListeners();
    }
    public void removeLoginPanel(){
        this.remove(this.loginPanel);
    }

    // OTHER

    public void createKeyListener(KeyListener listener) {
        this.addKeyListener(listener);
    }


    @Override
    public void updateMatrix() {
        this.renderGame(gameModel.getGameMatrix());
    }

    @Override
    public void stopGame() {

    }

    @Override
    public void startGame() {

    }


    // LISTENERS register
    public void registerListeners() {
        Logger.info("registering listeners");
        loginPanel.setLoginPanelListener(this);
    }

    // LISTENERS use

    /*
    Check for changes on the login screen & update send host + port to model if changed
     */
    @Override
    public void onLogin(String host, int port) {

    }

    @Override
    public void lobbyJoined() {

    }

    @Override
    public void lobbyClosed() {

    }
}
