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
                this.gameView = new GameView();
                this.gameModel = new GameModel("", 0);

                this.gameModel.add(gameView);

                gameModel.setData();

                this.gameView.addKeyListener(this);
        }

        @Override
        public void keyPressed(KeyEvent e) {
                this.gameModel.getKey(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

}
