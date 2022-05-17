package com.github.q11hackermans.slakeoverflow_client.controller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import com.github.q11hackermans.slakeoverflow_client.panels.LoginPanel;
import com.github.q11hackermans.slakeoverflow_client.utility.ActionCommands;
import com.github.q11hackermans.slakeoverflow_client.utility.KeyBinds;
import com.github.q11hackermans.slakeoverflow_client.utility.Logger;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;
import org.json.JSONObject;

import javax.swing.*;

public class GameController extends JFrame implements KeyListener, ActionListener {
        private JPanel view;
        private GameModel model;


        public static void main(String[] args) throws IOException {
                new GameController();
        }

        public GameController() throws IOException {
                this.view = new LoginPanel(this);
                this.add(view, BorderLayout.CENTER);

                Logger.info("creating game window");
                configureJFrame();

                //this.gameModel = new GameModel("127.0.0.1", 26677);
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

        /**
         * Register keypresses and send them to the server
         * @param e
         */
        public void handleKeyInput(KeyEvent e) {
                int nextKey = 0;
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
                if(e.getActionCommand().equals(ActionCommands.playButtonPressed)){
                        System.out.println("New GamePanel");
                }
        }
}
