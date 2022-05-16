package com.github.q11hackermans.slakeoverflow_client.controller;

import java.awt.event.*;
import java.io.IOException;

import com.github.q11hackermans.slakeoverflow_client.view.GameView;
import com.github.q11hackermans.slakeoverflow_client.model.GameModel;

public class GameController implements KeyListener {
        private final GameView gameView;
        private final GameModel gameModel;


        public static void main(String[] args) throws IOException {
                new GameController();
        }

        public GameController() throws IOException {
                this.gameModel = new GameModel("127.0.0.1", 26677);
                this.gameView = new GameView(gameModel);

                this.gameModel.setListener(gameView);

                this.gameView.addKeyListener(this);
        }

        @Override
        public void keyPressed(KeyEvent e) {
                this.gameModel.handleKeyInput(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

}
