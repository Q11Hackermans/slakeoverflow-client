package com.github.q11hackermans.slakeoverflow_client.controller;

import com.github.q11hackermans.slakeoverflow_client.constants.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.constants.Direction;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.panels.Panel;
import com.github.q11hackermans.slakeoverflow_client.panels.*;
import de.d3rhase.interfaces.Logger;
import de.d3rhase.txtlogger.TxtLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Dimension2D;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GameController extends JFrame implements KeyListener, ActionListener {
    public static Logger logger;
    private Panel panel;
    private GameModel model;
    private boolean disconnecting;


    public GameController() {
        logger = new TxtLogger("GameController", true);
        logger.info("CONSTRUCTOR", "creating game window");
        this.disconnecting = false;
        configureJFrame();

        this.switchToStartPanel();
    }

    public static void main(String[] args) { // mvn clean install -U - resolve dependencies
        new GameController();
        //GameController.testGamePanel(); // Test GamePanel
        //GameController.testLobbyPanel(); // Test LobbyPanel
        //GameController.testStorePanel(); // Test StorePanel
        //GameController.testLoginPanel(); // Test LoginPanel
    }

    private static void testGamePanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.setSize(1210, 865);
        j.setVisible(true);
        j.setLayout(new BorderLayout());
        GamePanel g = new GamePanel(null,0);
        j.add(g);
        g.render(new int[][]{{101}, {101}}); //uses first skinpack
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
        j.repaint();
    }

    private static void testLobbyPanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.setSize(500, 500);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
        LobbyPanel g = new LobbyPanel(null, null);
        j.add(g);
        j.repaint();
    }

    private static void testStorePanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(false);
        j.setSize(1210, 865);
        j.setVisible(true);
        StorePanel g = new StorePanel(null, null);
        j.add(g);
        j.repaint();
    }

    private static void testLoginPanel() {
        JFrame j = new JFrame();
        j.setTitle("Slakeoverflow");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(false);
        j.setSize(1210, 865);
        j.setVisible(true);
        LoginPanel g = new LoginPanel(null, "taest", null);
        j.add(g);
        j.repaint();
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
                if (panel instanceof StartPanel) {
                    try {
                        this.connectToSever();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else if (panel instanceof LobbyPanel) {
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

            case 84:
                // show chat message popup
                String msg;
                msg = JOptionPane.showInputDialog("Enter a chat message");
                model.sendChatMessage(msg);

            default:

                logger.info("HANDLE-KEY-INPUT", "key not found");
                //System.out.println(e);
                break;
        }

        if (nextKey != -1 && panel instanceof GamePanel) {
            model.sendKeyInput(nextKey);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("action");
        switch (e.getActionCommand()) {
            //redirects
            case ActionCommands.toLobbyButton:
                this.toLobbyButtonPressed();
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

            case ActionCommands.toLoginViewButton:
                this.switchToLoginPanel();
                break;

            case ActionCommands.logoutButton:
                this.logoutButtonPressed();
                System.out.println("Logout");
                break;

            case ActionCommands.toStoreViewButton:
                this.switchToStorePanel();
                System.out.println("Store");
                break;

            case ActionCommands.unAuthPlayer:
                this.unAuthPlayerPressed();
                break;

            default:
                if (!this.handleShopActionCommand(e.getActionCommand(), e)) {
                    System.out.println(e.getActionCommand());
                }
        }
    }

    private boolean handleShopActionCommand(String actionCommand, ActionEvent e) {
        if (actionCommand.contains("shopItemButton")) {
            String[] splitCommand = actionCommand.split("-");
            System.out.println(splitCommand[0] + " " + splitCommand[1] + " " + splitCommand[2]);
            if (Objects.equals(splitCommand[2], "1")) {
                this.model.setActiveItem(Integer.parseInt(splitCommand[1]));
            } else if (Objects.equals(splitCommand[2], "0")) {
                this.model.buyItem(Integer.parseInt(splitCommand[1]));
                JButton b = (JButton) e.getSource();
                b.setText("LOADING");
                this.model.requestUserInfo();
            }
            return true;
        }
        return false;
    }

    public void resizeJFrame(int xSize, int ySize) {
        this.setSize(xSize, ySize);
    }

    public Dimension2D getJFrameDimensions() {
        return this.getSize();
    }

    private void updateView(Panel panel) {
        this.getContentPane().removeAll();
        this.repaint();
        this.panel = panel;
        this.add(panel);
        SwingUtilities.updateComponentTreeUI(this);
        this.repaint();
    }

    private void backToLobbyFromLoginButtonPressed() {
        this.switchToLobbyPanel();
    }

    private void registerButtonPressed() {
        this.model.registerAccount(this.panel.getUsername(), this.panel.getPasswordHash());
    }

    private void toLobbyButtonPressed() {
        this.switchToLobbyPanel();
    }

    private void loginButtonPressed() {
        this.model.login(this.panel.getUsername(), this.panel.getPasswordHash());
    }

    private void logoutButtonPressed() {
        this.model.logout();
    }

    private void connectButtonPressed() {
        logger.info("CONNECT-BUTTON-PRESSED", "Connecting to Server");
        try {
            this.connectToSever();

        } catch (IOException ex) {
            logger.error("CONNECT-BUTTON-PRESSED", "Connecting to server failed!");
            ex.printStackTrace();
            this.disconnectFromServer();
            JOptionPane.showMessageDialog(this, "Please enter a valid host and port");
        }
    }

    private void playButtonPressed() {
        this.model.authPlayer();
    }

    private void unAuthPlayerPressed() {
        this.model.unauthPlayer();
    }

    private void backToStorePressed() {
        this.switchToStorePanel();
    }

    public void switchToGamePanel() {
        if (!(this.panel instanceof GamePanel)) {
            logger.debug("SWITCH-TO-GAMEPANEL", "switching to game panel");
            this.panel = new GamePanel(this, model.getActiveItem());
            this.updateView(this.panel);
            this.model.setActivePanel(this.panel);
        }
    }

    public void switchToUnauthPanel() {
        if (!(this.panel instanceof UnauthenticatedPanel) || (!this.panel.isUpToDate(this.panel))) {
            logger.debug("SWITCH-TO-UNAUTHPANEL", "switching to panel");
            if (this.panel instanceof StorePanel) {
                this.switchToStorePanel();
                //this.updateView(new StorePanel(this, this.model));
            } else {
                this.switchToLobbyPanel();
                //this.updateView(new LobbyPanel(this, this.model));
            }
        }
    }

    public void switchToStorePanel() {
        if (!(this.panel instanceof StorePanel)) {
            logger.debug("SWITCH-TO-STOREPANEL", "switching to store panel");
            this.updateView(new StorePanel(this, this.model));
        } else {
            logger.debug("SWITCH-TO-STOREPANEL", "updating to store panel");
            this.updateView(new StorePanel(this, this.model));
        }
    }

    public void switchToLobbyPanel() {
        if (!(this.panel instanceof LobbyPanel) || !this.panel.isUpToDate(this.panel)) {
            logger.debug("SWITCH-TO-LOBBYPANEL", "switching to lobby panel");
            this.updateView(new LobbyPanel(this, this.model));
            this.model.setActivePanel(this.panel);
        }
    }

    public void switchToLoginPanel() {
        if (!(this.panel instanceof LoginPanel)) {
            logger.debug("SWITCH-TO-LOGINPANEL", "switching to login panel");
            this.updateView(new LoginPanel(this, this.model.getServerName(), this.model));
        }
    }

    public void switchToStartPanel() {
        if (!(this.panel instanceof StartPanel)) {
            logger.debug("SWITCH-TO-STARTPANEL", "switching to start panel");
            this.updateView(new StartPanel(this));
        }
    }

    private void connectToSever() throws IOException {
        //System.out.println(this.panel.getHost());
        //System.out.println(this.panel.getPort());
        this.model = new GameModel(this.panel.getHost(), Integer.parseInt(this.panel.getPort()), new GamePanelDummy(), this);
        //this.switchToLoginPanel();
    }

    public void disconnectFromServerError() {
        if (!disconnecting && this.model != null) {
            JOptionPane.showMessageDialog(this, "Your were disconnected from the server!", "Connection Error", JOptionPane.ERROR_MESSAGE);
            this.disconnectFromServer();
        }
    }

    private void disconnectFromServer() {
        if (!disconnecting) {
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
}
