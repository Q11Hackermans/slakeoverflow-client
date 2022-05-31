package com.github.q11hackermans.slakeoverflow_client.controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Dimension2D;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.github.q11hackermans.slakeoverflow_client.panels.*;
import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.constants.Direction;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.view.View;

import javax.swing.*;

public class GameController extends JFrame implements KeyListener, ActionListener {
    private View view;
    private GameModel model;

    private boolean disconnecting;


    public static void main(String[] args) throws IOException {
        //new GameController();
        GameController.testGamePanel(); // Test GamePanel
        //GameController.testLobbyPanel(); // Test LobbyPanel
        //GameController.testStorePanel(); // Test LobbyPanel
        //GameController.testLoginPanel(); // Test LoginPanel

    }

    public GameController() {
        Logger.info("creating game window");
        this.disconnecting = false;
        configureJFrame();

        this.switchToStartPanel();
    }

    private void configureJFrame() {
        this.setTitle("Slakeoverflow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 1));
        this.setResizable(false);
        //this.setResizable(true);
        this.setSize(1210, 865);
        this.setExtendedState(JFrame.NORMAL);
        this.addKeyListener(this);
        this.setFocusable(true);

        this.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.handleKeyInput(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void updateView(View view) {
        this.getContentPane().removeAll();
        this.repaint();
        this.view = view;
        this.add(view);
        SwingUtilities.updateComponentTreeUI(this);
        this.repaint();
    }

    /**
     * Register keypresses and send them to the server
     *
     * @param e - KeyEvent
     */
    private void handleKeyInput(KeyEvent e) {
        //System.out.println(e);
        int nextKey = -1;
        switch (e.getKeyCode()) {
            case 10:
                if (view instanceof StartPanel) {
                    try {
                        this.connectToSever();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (view instanceof LobbyPanel) {
                    this.playButtonPressed();
                }
                break;

            // up
            case 87:
                nextKey = Direction.NORTH;
                break;

            // down
            case 83:
                nextKey = Direction.SOUTH;
                break;

            // left
            case 65:
                nextKey = Direction.WEST;
                break;

            // right
            case 68:
                nextKey = Direction.EAST;
                break;

            case 32:
                nextKey = Direction.SPEED;
                break;

            default:
                Logger.info("key not found");
                //System.out.println(e);
                break;
        }

        if (nextKey != -1 && view instanceof GamePanel) {
            model.sendKeyInput(nextKey);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("action");
        switch (e.getActionCommand()) {
            //redirects
            case ActionCommands.backToLobbyButton:
                this.backToLobbyPressed();
                break;

            case ActionCommands.backToStoreButton:
                this.backToStorePressed();
                break;

            case ActionCommands.playButtonPressed:
                this.playButtonPressed();
                break;

            case ActionCommands.connectButtonPressed:
                this.connectButtonPressed();
                break;

            case ActionCommands.disconnectButtonPressed:
                System.out.println("Disconnecting from Server");
                this.disconnectFromServer();
                break;

            case ActionCommands.spectatorButtonPressed:
                System.out.println("Spectating game Server");
                this.disconnectFromServer();
                JOptionPane.showMessageDialog(this, "Ghosting is not allowed on this server!");
                break;

            case ActionCommands.playAsGuestButton:
                System.out.println("play as guest");
                break;

            case ActionCommands.loginButton:
                this.loginButtonPressed();
                System.out.println("login Server");
                break;

            case ActionCommands.registerButton:
                this.registerButtonPressed();
                System.out.println("register Server");
                break;

            case ActionCommands.backToLobbyFromLoginButton:
                this.backToLobbyFromLoginButtonPressed();
                break;

            case ActionCommands.toLoginViewButton:
                this.switchToLoginPanel();
                break;

            case ActionCommands.logoutButton:
                this.logoutButtonPressed();
                System.out.println("Logout");
                break;
        }
    }


    public void resizeJFrame(int xSize, int ySize) {
        this.setSize(xSize, ySize);
    }

    public Dimension2D getJFrameDimensions() {
        return this.getSize();
    }

    private void backToLobbyFromLoginButtonPressed() {
        this.switchToLobbyPanel();
    }

    private void registerButtonPressed(){
        this.model.registerAccount(this.view.getUsername(), this.view.getPasswordHash());
    }

    private void loginButtonPressed(){
        this.model.login(this.view.getUsername(), this.view.getPasswordHash());
    }

    private void logoutButtonPressed(){
        this.model.logout();
    }

    private void connectButtonPressed() {
        Logger.info("Connecting to Server");
        try {
            this.connectToSever();

        } catch (IOException ex) {
            Logger.error("Connecting to server failed!");
            ex.printStackTrace();
            this.disconnectFromServer();
            JOptionPane.showMessageDialog(this, "Please enter a valid host and port");
        }
    }

    private void playButtonPressed() {
        this.model.authPlayer(1);
    }

    private void backToStorePressed() {
        this.switchToStorePanel();
    }

    public void switchToGamePanel() {
        if (!(this.view instanceof GamePanel)) {
            Logger.debug("switching to game panel");
            this.view = new GamePanel(this);
            this.model.setGamePanel((GamePanel) this.view);
            this.updateView(this.view);
        }
    }

    public void switchToUnAuthPanel() {
        if(!(this.view instanceof  LobbyPanel || this.view instanceof  LoginPanel) || (this.view instanceof  LobbyPanel && ((LobbyPanel) this.view).isLoginButtonVisible() == this.model.isLoggedIn())){
            Logger.debug("switching to lobby panel");
            this.updateView(new LobbyPanel(this, this.model.isLoggedIn()));
        }
    }

    public void switchToStorePanel() {
        if (!(this.view instanceof StorePanel)) {
            Logger.debug("switching to game panel");
            this.updateView(new StorePanel(this));
        }
    }

    public void switchToLobbyPanel() {
        if (!(this.view instanceof LobbyPanel) || ((LobbyPanel) this.view).isLoginButtonVisible() == this.model.isLoggedIn()) {
            Logger.debug("switching to lobby panel");
            this.updateView(new LobbyPanel(this, this.model.isLoggedIn()));
        }
    }

    public void switchToLoginPanel() {
        if (!(this.view instanceof LoginPanel)) {
            Logger.debug("switching to login panel");
            this.updateView(new LoginPanel(this, this.model.getServerName()));
        }
    }

    public void switchToStartPanel() {
        if (!(this.view instanceof StartPanel)) {
            Logger.debug("switching to start panel");
            this.updateView(new StartPanel(this));
        }
    }

    private void backToLobbyPressed() {
        this.model.authPlayer(0);
    }

    private void connectToSever() throws IOException {
        //System.out.println(this.view.getHost());
        //System.out.println(this.view.getPort());
        this.model = new GameModel(this.view.getHost(), Integer.parseInt(this.view.getPort()), new GamePanelDummy(), this);
        //this.switchToLoginPanel();
    }

    public void disconnectFromServerError() {
        if (!disconnecting) {
            JOptionPane.showMessageDialog(this, "Your were disconnected from the server!", "Connection Error", JOptionPane.ERROR_MESSAGE);
            this.disconnectFromServer();
        }
    }

    private void disconnectFromServer() {
        if(!disconnecting) {
            this.disconnecting = true;
            try {
                this.model.disconnect();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            this.model = null;
            this.disconnecting = false;
            this.switchToStartPanel();
        }
    }

    private static void testGamePanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.setSize(500, 500);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
        j.setLayout(new BorderLayout());
        GamePanel g = new GamePanel(null);
        j.add(g);
        g.render(new int[][]{{101}, {101}});
        g.applyNextMessage(
                "Message 1"
        );

        g.applyNextMessage(
                "message 2  "
        );
        try {
            TimeUnit.SECONDS.sleep(2);
            g.render(new int[][]{{102, 101}, {102, 101}});
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testLobbyPanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.setSize(500, 500);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
        LobbyPanel g = new LobbyPanel(null,false);
        j.add(g);
    }


    private static void testStorePanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.setSize(500, 500);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
        StorePanel g = new StorePanel(null);
        j.add(g);
    }

    private static void testLoginPanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.setSize(500, 500);
        j.setVisible(true);
        LoginPanel g = new LoginPanel(null, "taest");
        j.add(g);
    }
}
