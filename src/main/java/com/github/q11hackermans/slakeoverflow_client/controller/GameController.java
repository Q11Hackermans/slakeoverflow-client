package com.github.q11hackermans.slakeoverflow_client.controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Dimension2D;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.github.q11hackermans.slakeoverflow_client.panels.GamePanel;
import com.github.q11hackermans.slakeoverflow_client.panels.GamePanelDummy;
import com.github.q11hackermans.slakeoverflow_client.panels.LobbyPanel;
import com.github.q11hackermans.slakeoverflow_client.panels.LoginPanel;
import com.github.q11hackermans.slakeoverflow_client.utility.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.utility.Direction;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.view.View;

import javax.swing.*;

public class GameController extends JFrame implements KeyListener, ActionListener {
    private View view;
    private GameModel model;

    private boolean disconnecting;


    public static void main(String[] args) throws IOException {
        new GameController();
        //GameController.testGamePanel(); // Test GamePanel
        //GameController.testLobbyPanel(); // Test LobbyPanel

    }

    public GameController() {
        Logger.info("creating game window");
        this.disconnecting = false;
        configureJFrame();

        this.switchToLoginPanel();
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
                if (view instanceof LoginPanel) {
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

            default:
                Logger.info("key not found");
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
            case ActionCommands.backToLobbyButton:
                this.backToLobbyPressed();
                break;

            case ActionCommands.playButtonPressed:
                this.playButtonPressed();
                break;

            case ActionCommands.connectButtonPressed:
                Logger.info("Connecting to Server");
                try {
                    this.connectToSever();

                } catch (IOException ex) {
                    Logger.error("Connecting to server failed!");
                    ex.printStackTrace();
                    this.disconnectFromServer();
                    JOptionPane.showMessageDialog(this, "Please enter a valid host and port");
                }
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
        }
    }

    public void resizeJFrame(int xSize, int ySize) {
        this.setSize(xSize, ySize);
    }

    public Dimension2D getJFrameDimensions() {
        return this.getSize();
    }

    private void playButtonPressed() {
        this.model.authPlayer(1);
    }

    public void switchToGamePanel() {
        if (!(this.view instanceof GamePanel)) {
            Logger.debug("switching to game panel");
            this.view = new GamePanel(this);
            this.model.setGamePanel((GamePanel) this.view);
            this.updateView(this.view);
        }
    }

    public void switchToLobbyPanel() {
        if (!(this.view instanceof LobbyPanel)) {
            Logger.debug("switching to lobby panel");
            this.updateView(new LobbyPanel(this));
        }
    }

    public void switchToLoginPanel() {
        if (!(this.view instanceof LoginPanel)) {
            Logger.debug("switching to login panel");
            this.updateView(new LoginPanel(this));
        }
    }

    private void backToLobbyPressed() {
        this.model.authPlayer(0);
    }

    private void connectToSever() throws IOException {
        System.out.println(this.view.getHost());
        System.out.println(this.view.getPort());
        this.model = new GameModel(this.view.getHost(), Integer.parseInt(this.view.getPort()), new GamePanelDummy(), this);
        this.switchToLobbyPanel();
    }

    public void disconnectFromServerError() {
        if (!disconnecting) {
            JOptionPane.showMessageDialog(this, "Your were disconnected from the server!", "Connection Error", JOptionPane.ERROR_MESSAGE);
            this.disconnectFromServer();
        }
    }

    private void disconnectFromServer() {
        this.disconnecting = true;
        try {
            this.model.disconnect();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        this.model = null;
        this.disconnecting = false;
        this.switchToLoginPanel();

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
        LobbyPanel g = new LobbyPanel(null);
        j.add(g);
    }
}
