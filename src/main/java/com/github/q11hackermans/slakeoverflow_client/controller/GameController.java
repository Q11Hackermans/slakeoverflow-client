package com.github.q11hackermans.slakeoverflow_client.controller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.github.q11hackermans.slakeoverflow_client.panels.GamePanel;
import com.github.q11hackermans.slakeoverflow_client.panels.LobbyPanel;
import com.github.q11hackermans.slakeoverflow_client.panels.LoginPanel;
import com.github.q11hackermans.slakeoverflow_client.utility.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.utility.KeyBinds;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import com.github.q11hackermans.slakeoverflow_client.view.View;
import org.json.JSONObject;

import javax.swing.*;

public class GameController extends JFrame implements KeyListener, ActionListener {
        private View view;
        private GameModel model;


        public static void main(String[] args) throws IOException {
                new GameController();
                //GameController.testGamePanel(); // Test GamePanel
                //GameController.testLobbyPanel(); // Test LobbyPanel

        }

        public GameController() {
                this.updateView(new LoginPanel(this), BorderLayout.CENTER);

                Logger.info("creating game window");
                configureJFrame();
        }

        private void configureJFrame() {
                this.setTitle("Slakeoverflow");
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //this.setLayout(new BorderLayout());
                this.setResizable(true);
                this.setSize(500, 500);
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.addKeyListener(this);

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

        private void updateView(View view, Object layout){
                this.getContentPane().removeAll();
                this.repaint();
                this.add(view, layout);
                this.view = view;
                SwingUtilities.updateComponentTreeUI(this);
        }

        /**
         * Register keypresses and send them to the server
         * @param e - KeyEvent
         */
        private void handleKeyInput(KeyEvent e) {
                int nextKey;
                switch (e.getKeyCode()) {
                        // up
                        case 87:
                                nextKey = KeyBinds.PLAYER_UP;
                                break;

                        // down
                        case 83:
                                nextKey = KeyBinds.PLAYER_DOWN;
                                break;

                        // left
                        case 65:
                                nextKey = KeyBinds.PLAYER_LEFT;
                                break;

                        // right
                        case 68:
                                nextKey = KeyBinds.PLAYER_RIGHT;
                                break;

                        default:
                                Logger.info("key not found");
                                nextKey = -1;
                                break;
                }

                if (nextKey != -1) {
                        Logger.info("key "+ nextKey + " pressed");
                        JSONObject s = new JSONObject();
                        s.put("cmd","game_direction_change");
                        s.put("direction",nextKey);
                        model.sendKeyInput(s);
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//                System.out.println("action");
                switch (e.getActionCommand()){
                        case ActionCommands.connectButtonPressed:
                                Logger.info("Connecting to Server");
                                try {
                                        System.out.println(this.view.getHost());
                                        System.out.println(this.view.getPort());
                                        this.model = new GameModel(this.view.getHost(), Integer.parseInt(this.view.getPort()));
                                        this.updateView(new LobbyPanel(this), BorderLayout.CENTER);

                                } catch (IOException ex) {
                                        Logger.error("Connecting to server failed!");
                                        ex.printStackTrace();
                                        this.model = null;
                                        this.updateView(new LoginPanel(this), BorderLayout.CENTER);
                                        JOptionPane.showMessageDialog(this, "Please enter a valid host and port");
                                }
                                break;

                        case ActionCommands.disconnectButtonPressed:
                                System.out.println("Disconnecting from Server");
                                this.model = null;
                                this.updateView(new LoginPanel(this), BorderLayout.CENTER);
                                break;
                }
        }

        private static void testGamePanel(){
                JFrame j = new JFrame();
                j.setTitle("Slakeoverflow");
                j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                j.setResizable(true);
                j.setSize(500, 500);
                j.setExtendedState(JFrame.MAXIMIZED_BOTH);
                j.setVisible(true);
                GamePanel g = new GamePanel();
                j.add(g);
                g.render(new int[][] {{101},{101}});
                try {
                        TimeUnit.SECONDS.sleep(2);
                        g.render(new int[][] {{101, 101},{101,101}});
                } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                }
        }
        private static void testLobbyPanel(){
                JFrame j = new JFrame();
                j.setTitle("Slakeoverflow");
                j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                j.setResizable(true);
                j.setSize(500, 500);
                j.setExtendedState(JFrame.MAXIMIZED_BOTH);
                j.setVisible(true);
                LobbyPanel g = new LobbyPanel(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                System.out.println(e.getActionCommand());

                        }
                });
                j.add(g);
        }
}
